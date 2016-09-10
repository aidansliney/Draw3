package com.learn.howtodraw.draw;

import android.content.ActivityNotFoundException;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;

import com.google.firebase.crash.FirebaseCrash;
import com.learn.howtodraw.draw.util.IabHelper;
import com.learn.howtodraw.draw.util.IabResult;
import com.learn.howtodraw.draw.util.Inventory;
import com.learn.howtodraw.draw.util.Purchase;

import static com.learn.howtodraw.draw.Constants.*;


import layout.BrowseFragment;
import layout.ThirdFragment;

public class MainActivity extends IabActivity implements BrowseFragment.OnFragmentInteractionListener {


    public int imageCounter = 0;
    //Fire Analytics
    private FirebaseAnalytics mFirebaseAnalytics;


    private SectionsPagerAdapter mSectionsPagerAdapter;
    //full page adverts
    private static InterstitialAd mInterstitialAd;

    public static String menuSelected;
    //The {@link ViewPager} that will host the section contents.
    private ViewPager mViewPager;

    // Debug tag, for logging
    static final String TAG = Constants.LOG_IAB;
    // Helper object for in-app billing.
    IabHelper mHelper = null;


    public void onFragmentInteraction(Uri uri) {
    }

    public boolean isSubscribed() {
        return mSubscribed;
    }

    public  void consumeBook(final String skuName) {

        mHelper.queryInventoryAsync(true, new IabHelper.QueryInventoryFinishedListener() {
            @Override
            public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                if (inventory.getSkuDetails(skuName) != null) {
                    mHelper.consumeAsync(inventory.getPurchase(skuName), null);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       // FirebaseCrash.report(new Exception("My first Android non-fatal error"));


        // Start setup of in-app billing.
        // (Note that the work is done using methods in superclass
        // IabActivity. The original code had all the code here.)
        setupIabHelper(true, true);

        // Set a variable for convenient access
        // to the iab helper object.
        mHelper = getIabHelper();


        // enable debug logging
        // (For a production application, you would set this to false).
        mHelper.enableDebugLogging(true);

        //play full screen advert
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();

                //  We need to continue opening the page here once the add is X'ed
            }
        });
        // requestNewInterstitial();  Turn off the full page advert for now

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        String mFavoriteFood = "Potatoes";
        mFirebaseAnalytics.setUserProperty("favorite_food", mFavoriteFood);

        // Change the title on the main screen
        setTitle("");
        setContentView(R.layout.fragment_main); // this is needed but could be a better way





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("subscribed2?" + mSubscribed, "" + mSubscribed);


        // Create the adapter that will return a fragment for each of the three primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(com.learn.howtodraw.draw.R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(com.learn.howtodraw.draw.R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



        TextView tv = (TextView) findViewById(R.id.removeBook);
        assert tv != null;
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                consumeBook(SKU_BOOK_ARRAY[1]);
                mPurchasedBooksArray[1] = false;

            }
        });

        TextView tv2 = (TextView) findViewById(R.id.removeBook2);
        assert tv != null;
        tv2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                consumeBook("subscription");
                mSubscribed= false;

            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    consumeBook(SKU_BOOK_ARRAY[1]);
                    mPurchasedBooksArray[1] = false;


                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share));
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                    String id1 = "Share";
                    String name = "from Home";
                    Bundle bundle = new Bundle();
                    bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id1);
                    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
                    bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                }
            });
        }






/*        //Add mobile ads
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-7832891006427470~2392466545");
        AdView mAdView = (AdView) findViewById(com.learn.howtodraw.draw.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.learn.howtodraw.draw.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.share_settings) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share));
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
        }
        if (id == R.id.rate_settings) {
            rateApp();
            return true;
        }



/*        if (id == R.id.subscribe_settings) {
            FragmentManager fm = getSupportFragmentManager();
            MyDialogFragment dialogFragment = new MyDialogFragment();
            dialogFragment.show(fm, getString(R.string.menu_subscribe));
            menuSelected = "subscribe";
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }


    //  add full screen adverts
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("DBFB9795D39C49D52EAFBA8E58ACA288")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }


    // A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a firstFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    Log.d("case 0", "Entering News");
                    return firstFragment.newInstance(0);
                case 1:
                    Log.d("case 1", "Entering Books");
                    return BrowseFragment.newInstance("hello1", "hello2");

                case 2:
                    Log.d("case 2", "Entering Pages");
                    return ThirdFragment.newInstance(R.array.pagesAllHeadings, R.array.pagesAllIds, R.array.pagesAllImages, R.array.pagesAllbooks, R.array.pagesAllLevels);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.pagetitlenew);
                case 1:
                    return getString(R.string.pagetitlebooks);
                case 2:
                    return getString(R.string.pagetitlepages);
            }
            return null;
        }
    }


    /**
     * Called when something fails in the purchase flow before the part where the item is consumed.
     * <p/>
     * <p> This is the place to reset the UI if something was done to indicate that a purchase has started.
     *
     * @param h        IabHelper
     * @param errorNum int - error number from Constants
     * @return void
     */

    @Override
    void onIabPurchaseFailed(IabHelper h, int errorNum) {
        // We did set up in such a way so that error messages have already been display (with complain method).
        // So all we have to do is remove the "waiting" indicator.
        if (errorNum != 0) setWaitScreen(false);
        updateUi();

    }


    /**
     * User clicked the "Upgrade to Premium" button.
     */

    public void onBookPurchaseButtonClicked(View arg0, String book) {
        Log.d(TAG, "Purchase button clicked; launching purchase flow for a book.");
        setWaitScreen(true);

        launchInAppPurchaseFlow(this, book);

        //launchInAppPurchaseFlow(this, SKU_PREMIUM);

    /* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
/*
    String payload = "";

    mHelper.launchPurchaseFlow(this, SKU_PREMIUM, RC_PURCHASE_REQUEST,
            mPurchaseFinishedListener, payload);
*/
    }


    // "Subscribe to infinite gas" button clicked. Explain to user, then start purchase
// flow for subscription.
    public void onSubscribedButtonClicked(View arg0) {
        if (!mHelper.subscriptionsSupported()) {
            complain(getString(R.string.subscriptionerror));
            return;
        }

    /* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";

        setWaitScreen(true);

        launchSubscriptionPurchaseFlow(this, SKU_SUBSCRIPTION);
    /* (original code)
    Log.d(TAG, "Launching purchase flow for infinite gas subscription.");
    mHelper.launchPurchaseFlow(this,
            SKU_SUBSCRIPTION, IabHelper.ITEM_TYPE_SUBS,
            RC_PURCHASE_REQUEST, mPurchaseFinishedListener, payload);
    */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);
        if (mHelper == null) return;

        Log.d("We are back", "from the purchase");


        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd
            // perform any handling of activity results not related to in-app
            // billing...
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.d(TAG, "onActivityResult handled by IABUtil.");
        }
    }

    // We're being destroyed. It's important to dispose of the helper here!
    @Override
    public void onDestroy() {
        super.onDestroy();

        // very important:
/*        Log.d(TAG, "Destroying helper.");
        if (mHelper != null) {
            mHelper.dispose();
            mHelper = null;
        }*/
    }

    // updates UI to reflect model
    public void updateUi() {

    }

    // Enables or disables the "please wait" screen.
    void setWaitScreen(boolean set) {
       /* findViewById(R.id.screen_main).setVisibility(set ? View.GONE : View.VISIBLE);
        findViewById(R.id.screen_wait).setVisibility(set ? View.VISIBLE : View.GONE);*/
    }


/**
 */
// Methods for IabHelperListener.
// Subclasses should call the superclass method if they override any of these methods.

    /**
     * Called when consumption of a purchase item fails.
     * <p/>
     * <p> If this class was set up to issue messages upon failure, there is probably
     * nothing else to be done.
     */

    void onIabConsumeItemFailed(IabHelper h) {
        super.onIabConsumeItemFailed(h);

        // Do whatever you need to in the ui to indicate that consuming a purchase failed.


    }

    /**
     * Called when consumption of a purchase item succeeds.
     * <p/>
     * SKU_CONSUMABLE is the only consumable ite,. When it is purchased, this method gets called.
     * So this is the place where the tank is filled.
     *
     * @param h        IabHelper - helper object
     * @param purchase Purchase
     * @param result   IabResult
     */

    void onIabConsumeItemSucceeded(IabHelper h, Purchase purchase, IabResult result) {
        super.onIabConsumeItemSucceeded(h, purchase, result);
    }

    /**
     * Called when setup fails and the inventory of items is not available.
     * <p/>
     * <p> If this class was set up to issue messages upon failure, there is probably
     * nothing else to be done.
     */

    void onIabSetupFailed(IabHelper h) {
        super.onIabSetupFailed(h);

        // This would be where to change the ui in the event of a set up error.
    }


    /**
     * Called when setup succeeds and the inventory of items is available.
     *
     * @param h         IabHelper - helper object
     * @param result    IabResult
     * @param inventory Inventory
     */
    void onIabSetupSucceeded(IabHelper h, IabResult result, Inventory inventory) {
        super.onIabSetupSucceeded(h, result, inventory);

        // The superclass setup method checks to see what has been purchased and what has been subscribed to.
        // Premium and infinite gas are handled here. If there was a regular gas purchase, steps to consume
        // it (via an async call to consume) were started in the superclass.


       /* if (mPurchasedBook1) {
            toast("welcome book 1 owner");
            // FIX THIS
        }
*/


    }

    //Send users to rate the app
    public void rateApp() {
        try {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            Intent rateIntent = rateIntentForUrl("http://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    //Send users to rate the app
    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }




} // end class MainActivity

