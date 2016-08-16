package com.draw.aidansliney.draw3;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import layout.BrowseFragment;

public class MainActivity extends AppCompatActivity implements BrowseFragment.OnFragmentInteractionListener {

    //Fire Analytics
    private FirebaseAnalytics mFirebaseAnalytics;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    //full page adverts
    private static InterstitialAd mInterstitialAd;
    //The {@link ViewPager} that will host the section contents.
    private ViewPager mViewPager;

    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
        setContentView(R.layout.fragment_main); // this is needed but could be a better way

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the Two primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

/* hiding the FAB as it covers the adverts
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/html");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is where we will share the app</p>"));
                startActivity(Intent.createChooser(sharingIntent, "Share using"));

                String id1 =  "Share";
                String name = "from Home";
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id1);
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            }
        });
*/
        //Add mobile ads
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-7832891006427470~2392466545");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
                        startActivity(intent);
                    }
                }
            });
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            linkBook(R.id.layout_1, R.array.book1Build, rootView, false);
            linkBook(R.id.layout_2, R.array.book2Build, rootView, false);
            linkBook(R.id.layout_3, R.array.book3Build, rootView, true);


            View homeCard1 = rootView.findViewById(R.id.home_card_1);
            homeCard1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), PageActivity.class);
                    intent.putExtra("bookSlides", R.array.book1Page1Slides);
                    startActivity(intent);
                }
            });

            View homeCard2 = rootView.findViewById(R.id.home_card_2);
            homeCard2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), PageActivity.class);
                    intent.putExtra("bookSlides", R.array.book1Page1Slides);
                    startActivity(intent);
                }
            });

            View homeCard3 = rootView.findViewById(R.id.home_card_3);
            homeCard3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), PageActivity.class);
                    intent.putExtra("bookSlides", R.array.book1Page1Slides);
                    startActivity(intent);
                }
            });

            View homeCard4 = rootView.findViewById(R.id.home_card_4);
            homeCard4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), PageActivity.class);
                    intent.putExtra("bookSlides", R.array.book1Page1Slides);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }

    // attempt to add full screen pages
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

}
