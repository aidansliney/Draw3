package com.learn.howtodraw.draw;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn.howtodraw.draw.util.IabHelper;

import static com.learn.howtodraw.draw.R.id.textabout;

/**
 * Created by aidansliney on 21/08/2016.
 */
public class MyDialogFragment extends BaseFragment {

    public Integer bookThumb;
    public Integer bookName;
    public Integer earlyAccess;
    public String bookNameString;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bookThumb = getArguments().getInt("bookThumb");
        bookName = getArguments().getInt("bookName");
        earlyAccess = getArguments().getInt("early");



        //create Subscription fragment
        View rootView = inflater.inflate(R.layout.fragment_dialog_subscribe, container, false);

        //set the book thumb from the bundle
        ImageView imageView = (ImageView) rootView.findViewById(R.id.bookThumb);
        imageView.setImageResource(bookThumb);


        bookNameString = getString(bookName);
        Button buyBookbtn = (Button) rootView.findViewById(R.id.buyBookButton);
        buyBookbtn.setText(IabHelper.map.get(bookNameString));
        buyBookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainActivity().onBookPurchaseButtonClicked(getView(), bookNameString);
            }
        });


        Button subscribebtn = (Button) rootView.findViewById(R.id.subscribeButton);
        subscribebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainActivity().onSubscribedButtonClicked(getView());
            }
        });

        if (earlyAccess == 1)
        {
            final TextView textabout = (TextView) rootView.findViewById(R.id.textabout);
            textabout.setVisibility(View.GONE);
            buyBookbtn.setVisibility(View.GONE);

            final TextView earlyText = (TextView) rootView.findViewById(R.id.earlyText);
            earlyText.setText("To have early access to this book you can subscribe. Subscribing also gives you access to all the back catalogue and all upcoming new content");

        }


 /*     TextView textView = (TextView) rootView.findViewById(R.id.subscribedQuestion);
        if (isSubscribed()) {
            textView.setText("You have subscribed you LEGEND!!");
        } else
            textView.setText("No subscription");
             */

        return rootView;

    }


}


