package com.learn.howtodraw.draw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//A placeholder fragment containing a simple view.
public class PlaceholderFragment extends BaseFragment {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        LinkBooks.linkBook(R.id.layout_1, R.array.book1Build, rootView, false, getActivity());
        LinkBooks.linkBook(R.id.layout_2, R.array.book2Build, rootView, false,getActivity());

        Log.d("subscribed?", "" + isSubscribed());

        View homeCard1 = rootView.findViewById(com.learn.howtodraw.draw.R.id.home_card_1);
        homeCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageActivity.class);
                intent.putExtra("bookSlides", com.learn.howtodraw.draw.R.array.book3Page4Slides);
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

        View artist = rootView.findViewById(com.learn.howtodraw.draw.R.id.layout_4);
        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return rootView;
    }
}