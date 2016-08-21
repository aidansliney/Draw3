package com.learn.howtodraw.draw;

import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.TypedArray;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;

import com.learn.howtodraw.draw.util.IabHelper;
import com.learn.howtodraw.draw.util.IabResult;
import com.learn.howtodraw.draw.util.Inventory;
import com.learn.howtodraw.draw.util.Purchase;

import static com.learn.howtodraw.draw.Constants.*;

import layout.BrowseFragment;

public class MainActivity extends IabActivity implements BrowseFragment.OnFragmentInteractionListener {

    //Fire Analytics
    private FirebaseAnalytics mFirebaseAnalytics;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    //full page adverts
    private static InterstitialAd mInterstitialAd;
    //The {@link ViewPager} that will host the section contents.
    private ViewPager mViewPager;

    /**
     */
// Variables

// Debug tag, for logging
    static final String TAG = Constants.LOG_IAB;
    // Helper object for in-app billing.
    IabHelper mHelper = null;
    // Current amount of gas in tank, in units
    protected int mTank;


    private String menuItem; // keeps track of what menu item is selected

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String someVariable) {
        this.menuItem = someVariable;
    }



    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


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
        requestNewInterstitial(); // preload interstitial advert

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        String mFavoriteFood = "Potatoes";
        mFirebaseAnalytics.setUserProperty("favorite_food", mFavoriteFood);

        // Change the title on the main screen
        setTitle("");
        setContentView(com.learn.howtodraw.draw.R.layout.fragment_main); // this is needed but could be a better way

        Log.d("subscribed?" + mSubscribed, "" + mSubscribed);

        if (!mSubscribed) {
            TextView textView = (TextView) findViewById(R.id.subscribedQuestion);
            textView.setText("You have not subscribed");
        } else {
            TextView textView = (TextView) findViewById(R.id.subscribedQuestion);
            textView.setText("You have subscribed you LEGEND!!");
        }


        super.onCreate(savedInstanceState);
        setContentView(com.learn.howtodraw.draw.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.learn.howtodraw.draw.R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("subscribed2?" + mSubscribed, "" + mSubscribed);


        // Create the adapter that will return a fragment for each of the Two primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(com.learn.howtodraw.draw.R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(com.learn.howtodraw.draw.R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This message has been sent from the Android app Draw. Download the app from http://www.google.com");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);


                String id1 =  "Share";
                String name = "from Home";
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id1);
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            }
        });

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == com.learn.howtodraw.draw.R.id.share_settings) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This message has been sent from the Android app Draw. Download the app from http://www.google.com");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
        }

        if (id == com.learn.howtodraw.draw.R.id.rate_settings) {
           rateApp();
            return true;
        }

        if (id == com.learn.howtodraw.draw.R.id.about_settings) {
            FragmentManager fm = getSupportFragmentManager();
            MyDialogFragment dialogFragment = new MyDialogFragment ();
            dialogFragment.show(fm, "About Settings");
            setMenuItem("about"); // telling myDF which menu item was selected
            return true;
        }

        if (id == com.learn.howtodraw.draw.R.id.subscribe_settings) {
            FragmentManager fm = getSupportFragmentManager();
            MyDialogFragment dialogFragment = new MyDialogFragment ();
            dialogFragment.show(fm, "Subscribe Settings");
            setMenuItem("subscribe");
            return true;
        }


        return super.onOptionsItemSelected(item);
    }





    //A placeholder fragment containing a simple view.
    public static class PlaceholderFragment extends Fragment {
        //The fragment argument representing the section number for this fragment.
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        // Returns a new instance of this fragment for the given section number
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        // a method to setup the right content for = book thumbnails
        public void linkBook(int layout, final int bookContent, View rootView, final boolean advert) {

            View Layout = rootView.findViewById(layout);
            Layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (advert && mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        TypedArray bookBuild = getResources().obtainTypedArray(bookContent);
                        final int[] bookCollected = new int[bookBuild.length()];
                        for (int i = 0; i < bookBuild.length(); i++)
                            bookCollected[i] = bookBuild.getResourceId(i, 0);
                        bookBuild.recycle();
                        Intent intent = new Intent(getActivity(), BookActivity.class);
                        intent.putExtra("bookCoverH1Id", bookCollected[0]);
                        intent.putExtra("bookCoverH2Id", bookCollected[1]);
                        intent.putExtra("bookCoverImageId", bookCollected[2]);
                        intent.putExtra("cardText1Id", bookCollected[3]);
                        intent.putExtra("cardText2Id", bookCollected[4]);
                        intent.putExtra("cardImageId", bookCollected[5]);
                        intent.putExtra("bookPageIds", bookCollected[6]);
                        intent.putExtra("bookCoverImageInsideId", bookCollected[7]);

                        startActivity(intent);
                    }
                }
            });
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(com.learn.howtodraw.draw.R.layout.fragment_main, container, false);

            linkBook(com.learn.howtodraw.draw.R.id.layout_1, com.learn.howtodraw.draw.R.array.book1Build, rootView, false);
            linkBook(com.learn.howtodraw.draw.R.id.layout_2, com.learn.howtodraw.draw.R.array.book2Build, rootView, false);


            View homeCard1 = rootView.findViewById(com.learn.howtodraw.draw.R.id.home_card_1);
            homeCard1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), PageActivity.class);
                    intent.putExtra("bookSlides", com.learn.howtodraw.draw.R.array.book2Page2Slides);
                    startActivity(intent);
                }
            });

            View homeCard2 = rootView.findViewById(com.learn.howtodraw.draw.R.id.home_card_2);
            homeCard2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), PageActivity.class);
                    intent.putExtra("bookSlides", com.learn.howtodraw.draw.R.array.book2Page3Slides);
                    startActivity(intent);
                }
            });

            View homeCard3 = rootView.findViewById(com.learn.howtodraw.draw.R.id.home_card_3);
            homeCard3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), PageActivity.class);
                    intent.putExtra("bookSlides", com.learn.howtodraw.draw.R.array.book1Page1Slides);
                    startActivity(intent);
                }
            });

            View homeCard4 = rootView.findViewById(com.learn.howtodraw.draw.R.id.home_card_4);
            homeCard4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), PageActivity.class);
                    intent.putExtra("bookSlides", com.learn.howtodraw.draw.R.array.book3Page1Slides);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }

    // attempt to add full screen adverts
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("DBFB9795D39C49D52EAFBA8E58ACA288")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    Log.d("case 0", "BF");
                    return PlaceholderFragment.newInstance(0);
                case 1:
                    Log.d("case 1", "BF");
                    return BrowseFragment.newInstance("hello1", "hello2");

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Discover";
                case 1:
                    return "Search";
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
     * User clicked the "Buy Gas" button
     */

    public void onBuyGasButtonClicked(View arg0) {
        Log.d(TAG, "Buy gas button clicked.");

        if (mSubscribed) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }

        if (mTank >= TANK_MAX) {
            complain("Your tank is full. Drive around a bit!");
            return;
        }

        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        Log.d(TAG, "Launching purchase flow for gas.");

        // The steps needed to complete a purchase are done in code in the IabActivity superclass.
        // IabActivity provides standard handling and calls back to this class.
        launchInAppPurchaseFlow(this, SKU_GAS);


    /* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */

    /*
    String payload = "";

    mHelper.launchPurchaseFlow(this, SKU_GAS, RC_PURCHASE_REQUEST,
            mPurchaseFinishedListener, payload);
    */
    }

    /**
     * User clicked the "Upgrade to Premium" button.
     */

    public void onUpgradeAppButtonClicked(View arg0) {
        Log.d(TAG, "Upgrade button clicked; launching purchase flow for upgrade.");
        setWaitScreen(true);

        launchInAppPurchaseFlow(this, SKU_PREMIUM);

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
            complain("Subscriptions not supported on your device yet. Sorry!");
            return;
        }

    /* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";

        setWaitScreen(true);

        launchSubscriptionPurchaseFlow(this, SKU_INFINITE_GAS);
    /* (original code)
    Log.d(TAG, "Launching purchase flow for infinite gas subscription.");
    mHelper.launchPurchaseFlow(this,
            SKU_INFINITE_GAS, IabHelper.ITEM_TYPE_SUBS,
            RC_PURCHASE_REQUEST, mPurchaseFinishedListener, payload);
    */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);
        if (mHelper == null) return;

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

// (wgl, May 2015)
// The next two variables are the original listener objects.
// They are no longer used. See superclass IabActivity for the current ones.

    // Callback for when a purchase is finished
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListenerOLD = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            Log.d(TAG, "Purchase finished: " + result + ", purchase: " + purchase);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            if (result.isFailure()) {
                complain("Error purchasing: " + result);
                updateUi();
                setWaitScreen(false);
                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                complain("Error purchasing. Authenticity verification failed.");
                updateUi();
                setWaitScreen(false);
                return;
            }

            Log.d(TAG, "Purchase successful.");

            if (purchase.getSku().equals(SKU_GAS)) {
                // bought 1/4 tank of gas. So consume it.
                Log.d(TAG, "Purchase is gas. Starting gas consumption.");
                mHelper.consumeAsync(purchase, mConsumeFinishedListener);
            } else if (purchase.getSku().equals(SKU_PREMIUM)) {
                // bought the premium upgrade!
                Log.d(TAG, "Purchase is premium upgrade. Congratulating user.");
                alert("Thank you for upgrading to premium!");
                mIsPremium = true;
                updateUi();
                setWaitScreen(false);
            } else if (purchase.getSku().equals(SKU_INFINITE_GAS)) {
                // bought the infinite gas subscription
                Log.d(TAG, "Infinite gas subscription purchased.");
                alert("Thank you for subscribing to infinite gas!");
                mSubscribed = true;
                mTank = TANK_MAX;
                updateUi();
                setWaitScreen(false);
            }
        }
    };

    // Called when consumption is complete
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListenerOLD = new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            Log.d(TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            // We know this is the "gas" sku because it's the only one we consume,
            // so we don't check which sku was consumed. If you have more than one
            // sku, you probably should check...
            if (result.isSuccess()) {
                // successfully consumed, so we apply the effects of the item in our
                // game world's logic, which in our case means filling the gas tank a bit
                Log.d(TAG, "Consumption successful. Provisioning.");
                mTank = mTank == TANK_MAX ? TANK_MAX : mTank + 1;
                //  saveData();
                alert("You filled 1/4 tank. Your tank is now " + String.valueOf(mTank) + "/4 full!");
            } else {
                complain("Error while consuming: " + result);
            }
            updateUi();
            setWaitScreen(false);
            Log.d(TAG, "End consumption flow.");
        }
    };

// Methods

/*    // Drive button clicked. Burn gas!
    public void onDriveButtonClicked(View arg0) {
        Log.d(TAG, "Drive button clicked.");
        if (!mSubscribed && mTank <= 0) alert("Oh, no! You are out of gas! Try buying some!");
        else {
            if (!mSubscribed) --mTank;
           // saveData();
            alert("Vroooom, you drove a few miles.");
            updateUi();
            Log.d(TAG, "Vrooom. Tank is now " + mTank);
        }
        updateUi ();
    }*/

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
/*        // update the car color to reflect premium status or lack thereof
        ((ImageView)findViewById(R.id.free_or_premium)).setImageResource(mIsPremium ? R.drawable.premium : R.drawable.free);

        // "Upgrade" button is only visible if the user is not premium
        findViewById(R.id.upgrade_button).setVisibility(mIsPremium ? View.GONE : View.VISIBLE);

        // "Get infinite gas" button is only visible if the user is not subscribed yet
        findViewById(R.id.infinite_gas_button).setVisibility(mSubscribed ?
                View.GONE : View.VISIBLE);

        // update gas gauge to reflect tank status
        if (mSubscribed) {
            ((ImageView)findViewById(R.id.gas_gauge)).setImageResource(R.drawable.gas_inf);
        }
        else {
            int index = mTank >= TANK_RES_IDS.length ? TANK_RES_IDS.length - 1 : mTank;
            ((ImageView)findViewById(R.id.gas_gauge)).setImageResource(TANK_RES_IDS[index]);
        }*/
    }

    // Enables or disables the "please wait" screen.
    void setWaitScreen(boolean set) {
       /* findViewById(R.id.screen_main).setVisibility(set ? View.GONE : View.VISIBLE);
        findViewById(R.id.screen_wait).setVisibility(set ? View.VISIBLE : View.GONE);*/
    }

/*    void saveData() {

        *//*
         * WARNING: on a real application, we recommend you save data in a secure way to
         * prevent tampering. For simplicity in this sample, we simply store the data using a
         * SharedPreferences.
         *//*

        SharedPreferences.Editor spe = getPreferences(MODE_PRIVATE).edit();
        spe.putInt("tank", mTank);
        spe.commit();
        Log.d(TAG, "Saved data: tank = " + String.valueOf(mTank));
    }

    void loadData() {
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        mTank = sp.getInt("tank", 2);
        Log.d(TAG, "Loaded data: tank = " + String.valueOf(mTank));
    }*/

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
        updateUi();
        setWaitScreen(false);

    }

    /**
     * Called when consumption of a purchase item succeeds.
     * <p/>
     * SKU_GAS is the only consumable ite,. When it is purchased, this method gets called.
     * So this is the place where the tank is filled.
     *
     * @param h        IabHelper - helper object
     * @param purchase Purchase
     * @param result   IabResult
     */

    void onIabConsumeItemSucceeded(IabHelper h, Purchase purchase, IabResult result) {
        super.onIabConsumeItemSucceeded(h, purchase, result);

        // Update the state of the app and the ui to show the item we purchased and consumed.
        String purchaseSku = purchase.getSku();
        if (purchaseSku.equals(SKU_GAS)) {
            if (result.isSuccess()) {
                // successfully consumed, so we apply the effects of the item in our
                // game world's logic, which in our case means filling the gas tank a bit
                Log.d(TAG, "Consumption successful. Provisioning.");
                mTank = mTank == TANK_MAX ? TANK_MAX : mTank + 1;
                //saveData();
                alert("You filled 1/4 tank. Your tank is now " + String.valueOf(mTank) + "/4 full!");
            } else {
                complain("Error while consuming regular gas: " + result);
            }

        }

        // Original code did some processing here. I moved this to IabActivity. (wgl, May 2015)
    /*
    else if (purchase.getSku().equals(SKU_PREMIUM)) {
      // bought the premium upgrade!
      Log.d(TAG, "Purchase is premium upgrade. Congratulating user.");
      alert("Thank you for upgrading to premium!");
      mIsPremium = true;
      updateUi();
      setWaitScreen(false);
    } else if (purchase.getSku().equals(SKU_INFINITE_GAS)) {

      // bought the infinite gas subscription
      Log.d(TAG, "Infinite gas subscription purchased.");
      alert("Thank you for subscribing to infinite gas!");
      mSubscribed = true;
      mTank = TANK_MAX;
      updateUi();
      setWaitScreen(false);
    }
    */
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

        if (mSubscribed) mTank = TANK_MAX;

        if (mIsPremium) {
            toast("welcome Premium User");
            // FIX THIS
        }
    }


    public void rateApp()
    {
        try
        {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Intent rateIntent = rateIntentForUrl("http://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }
    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }








} // end class MainActivity

