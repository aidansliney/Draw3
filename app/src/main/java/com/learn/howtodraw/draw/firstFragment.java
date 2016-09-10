package com.learn.howtodraw.draw;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import static com.learn.howtodraw.draw.Constants.SKU_BOOK_ARRAY;
import static com.learn.howtodraw.draw.Constants.mPurchasedBooksArray;

//A placeholder fragment containing a simple view.
public class firstFragment extends BaseFragment  {
    //The fragment argument representing the section number for this fragment.
    private static final String ARG_SECTION_NUMBER = "section_number";
    public int willyCounter;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        LinkBooks.linkBook(R.id.layout_1, R.array.book1Build, rootView, false, getActivity());



/*        final VideoView vidView = (VideoView) rootView.findViewById(R.id.myVideoA);
        String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
        Uri vidUri = Uri.parse(vidAddress);
        vidView.setVideoURI(vidUri);

        final View vV =  rootView.findViewById(R.id.video);

        vV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("log taht","as");
                vidView.start();
            }
        });*/




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
                    Log.d("Clicked", "Willy");
                }
            }
        });

        Log.d("subscribed?", "" + isSubscribed());

        View homeCard1 = rootView.findViewById(R.id.home_card_1);
        homeCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageActivity.class);
                intent.putExtra("bookSlides", R.array.book3Tutorial4Pages);
                intent.putExtra("bookHelp", R.array.book3Tutorial4Help);
                startActivity(intent);
            }
        });

        View homeCard2 = rootView.findViewById(com.learn.howtodraw.draw.R.id.home_card_2);
        homeCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageActivity.class);
                intent.putExtra("bookSlides", R.array.book2Tutorial3Pages);
                intent.putExtra("bookHelp", R.array.book2Tutorial3Help);
                startActivity(intent);
            }
        });

        View artist = rootView.findViewById(R.id.layout_4);
        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return rootView;
    }



}