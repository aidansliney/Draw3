package layout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.learn.howtodraw.draw.BaseFragment;
import com.learn.howtodraw.draw.LinkBooks;
import com.learn.howtodraw.draw.MainActivity;
import com.learn.howtodraw.draw.MyDialogFragment;
import com.learn.howtodraw.draw.PageActivity;
import com.learn.howtodraw.draw.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static android.R.id.message;
import static com.google.android.gms.internal.zzs.TAG;
import static com.learn.howtodraw.draw.Constants.SKU_BOOK_NAME_ARRAY;
import static com.learn.howtodraw.draw.Constants.freeBookA;
import static com.learn.howtodraw.draw.Constants.freeH1A;
import static com.learn.howtodraw.draw.Constants.freeHelpA;
import static com.learn.howtodraw.draw.Constants.freeIDA;
import static com.learn.howtodraw.draw.Constants.freeImageA;
import static com.learn.howtodraw.draw.Constants.frontBookH1;
import static com.learn.howtodraw.draw.Constants.frontBookImage;
import static com.learn.howtodraw.draw.Constants.frontBookLink;
import static com.learn.howtodraw.draw.Constants.mPurchasedBooksArray;
import static com.learn.howtodraw.draw.Constants.mSubscribed;
import static com.learn.howtodraw.draw.Constants.subBookH1;
import static com.learn.howtodraw.draw.Constants.subBookImage;
import static com.learn.howtodraw.draw.Constants.subBookLink;


//A placeholder fragment containing a simple view.
public class firstFragment extends BaseFragment {
    //The fragment argument representing the section number for this fragment.
    private static final String ARG_SECTION_NUMBER = "section_number";
    public int willyCounter;
    public  View videoCover;
    public  View videoContainer;
    public boolean playing = false;


    public firstFragment() {
    }

    // Returns a new instance of this fragment for the given section number
    public static firstFragment newInstance(int sectionNumber) {
        firstFragment fragment = new firstFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onResume() {
        super.onResume();
        videoCover.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //Setting new book on new page
        LinkBooks.linkBook(R.id.layout_1, frontBookLink, rootView, false, getActivity());
        final ImageView frontBook = (ImageView) rootView.findViewById(R.id.firstImage);
        final TextView h1 = (TextView) rootView.findViewById(R.id.h1);
        frontBook.setImageResource(frontBookImage);
        h1.setText(frontBookH1);

        //Setting the sub book on new page
        LinkBooks.linkBook(R.id.layout_sub, subBookLink, rootView, false, getActivity());
        final ImageView subBook = (ImageView) rootView.findViewById(R.id.subImage);
        final TextView h1sub = (TextView) rootView.findViewById(R.id.h1sub);
        subBook.setImageResource(subBookImage);
        h1sub.setText(subBookH1);


        if (mSubscribed)
        {
            Log.d("You", "are subscribed");
        }

        else
        {
            subBook.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                     bundle.putInt("bookThumb", subBookImage);
                    bundle.putInt("bookName", subBookH1);
                    bundle.putInt("early", 1);
                    FragmentManager fm = getFragmentManager();
                    MyDialogFragment dialogFragment = new MyDialogFragment();
                    dialogFragment.setArguments(bundle);
                    dialogFragment.show(fm, getString(R.string.menu_subscribe));
                }
            });
        }




        final TextView rb1 = (TextView) rootView.findViewById(R.id.removeBook1);
        final TextView rb2 = (TextView) rootView.findViewById(R.id.removeBook2);
        final TextView rb3 = (TextView) rootView.findViewById(R.id.removeBook3);
        final TextView rb4 = (TextView) rootView.findViewById(R.id.removeBook4);
        final TextView rb5 = (TextView) rootView.findViewById(R.id.removeBook5);
        final TextView rb6 = (TextView) rootView.findViewById(R.id.removeBook6);
        final TextView rb7 = (TextView) rootView.findViewById(R.id.removeBook7);
        rb1.setVisibility(View.GONE);
        rb2.setVisibility(View.GONE);
        rb3.setVisibility(View.GONE);
        rb4.setVisibility(View.GONE);
        rb5.setVisibility(View.GONE);
        rb6.setVisibility(View.GONE);
        rb7.setVisibility(View.GONE);

        rb1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[1]);
                mPurchasedBooksArray[1] = false;
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[2]);
                mPurchasedBooksArray[2] = false;
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[3]);
                mPurchasedBooksArray[3] = false;
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[4]);
                mPurchasedBooksArray[4] = false;
            }
        });
        rb5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[5]);
                mPurchasedBooksArray[5] = false;
            }
        });

        rb6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[6]);
                mPurchasedBooksArray[6] = false;
            }
        });

        rb7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[7]);
                mPurchasedBooksArray[7] = false;
            }
        });

        final ImageView iV = (ImageView) rootView.findViewById(R.id.willy);
        final TextView tV = (TextView) rootView.findViewById(R.id.will_header);
        final TextView tV2 = (TextView) rootView.findViewById(R.id.will_text);

        iV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                willyCounter ++;
                if (willyCounter == 5)
                {
                    iV.setImageResource(R.drawable.mario);
                    tV.setText("Aidan Sliney is a legend");
                    tV2.setText("What a legend");
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.VISIBLE);
                    rb4.setVisibility(View.VISIBLE);
                    rb5.setVisibility(View.VISIBLE);
                    rb6.setVisibility(View.VISIBLE);
                    rb7.setVisibility(View.VISIBLE);

                    Log.d("Clicked", "Willy");
                }
            }
        });



        View homeCard1 = rootView.findViewById(R.id.home_card_1);
        final TextView fh1 = (TextView) rootView.findViewById(R.id.freeH1);
        fh1.setText(freeH1A);
        final ImageView freeImage1 = (ImageView) rootView.findViewById(R.id.freeImage1);
        freeImage1.setImageResource(freeImageA);
        homeCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageActivity.class);
                intent.putExtra("tutorialId", freeIDA);
                intent.putExtra("bookHelp", freeHelpA);
                intent.putExtra("bookName", freeBookA);
                startActivity(intent);
            }
        });

        View homeCard2 = rootView.findViewById(com.learn.howtodraw.draw.R.id.home_card_2);


        homeCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageActivity.class);
                intent.putExtra("tutorialId", "b02t03");
                intent.putExtra("bookHelp", R.array.b02t03Help);
                intent.putExtra("bookName", "book02");
                startActivity(intent);
            }
        });

        View helpCard3 = rootView.findViewById(com.learn.howtodraw.draw.R.id.layout_0);
        helpCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageActivity.class);
                intent.putExtra("bookHelp", R.array.b00t01Help);
                intent.putExtra("tutorialId", "b00t01");
                intent.putExtra("bookName", "book00");
                startActivity(intent);
            }
        });


        View artist = rootView.findViewById(R.id.layout_4);
        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        View feedback = rootView.findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });


        View tweet = rootView.findViewById(R.id.tweet);
        tweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTweet();
            }
        });



        final VideoView vidView = (VideoView) rootView.findViewById(R.id.myVideo);
        String vidAddress = "https://firebasestorage.googleapis.com/v0/b/draw-891c7.appspot.com/o/LearnhowtoDraw_reduced.mp4?alt=media";
        Uri vidUri = Uri.parse(vidAddress);
       // vidView.setVideoURI(vidUri);
        vidView.setVideoPath("android.resource://" + getMainActivity().getPackageName() + "/" + R.raw.video);
       // vidView.start();
        Log.d("video", "video");
        MediaController vidControl = new MediaController(getActivity());
        vidControl.setAnchorView(vidView);
        //vidView.setMediaController(vidControl);

        videoCover = rootView.findViewById(R.id.videoCover);
        videoCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoCover.setVisibility(View.INVISIBLE);
                vidView.start();
                playing = true;
            }
        });

        videoContainer = rootView.findViewById(R.id.videoContainer);
        videoContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("qwerty","touched");
                if (playing == true)
                {
                    vidView.pause();
                    Log.d("Stop", "vidView");
                    playing = false;
                }

                else
                {
                    vidView.start();
                    Log.d("Start", "vidView");
                    playing = true;
                }
            }
        });


        vidView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return rootView;
    }


    public void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {"aidansliney@gmail.com"};
        String[] CC = {"wsliney@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Draw Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Please write your feedback here. The more info the better");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //finish();
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }



    public void sendTweet() {
        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.putExtra(Intent.EXTRA_TEXT, "Hey @WillSliney, Check out the drawing i did with the help of your Android App 'Learn how to draw' https://play.google.com/store/apps/details?id=com.learn.howtodraw.draw");
        tweetIntent.setType("text/plain");

        PackageManager packManager = getActivity().getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent,  PackageManager.MATCH_DEFAULT_ONLY);

        boolean resolved = false;
        for(ResolveInfo resolveInfo: resolvedInfoList){
            if(resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")){
                tweetIntent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name );
                resolved = true;
                break;
            }
        }
        if(resolved){
            startActivity(tweetIntent);

        }else{
            Intent i = new Intent();
            String message = getString(R.string.tweetWill);
            i.putExtra(Intent.EXTRA_TEXT, message);
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://twitter.com/intent/tweet?text="+urlEncode(message)));
            startActivity(i);

        }


    }

    private String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            return "";
        }
    }




}