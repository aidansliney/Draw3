package com.learn.howtodraw.draw;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by aidansliney on 21/08/2016.
 */
public class MyDialogFragment extends BaseFragment {

    public Integer bookThumb;
    public Integer bookName;
    public String bookNameString;
    static TextView tv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (MainActivity.menuSelected == "about") {
            View rootView = inflater.inflate(R.layout.fragment_dialog_about, container, false);
            return rootView;
        } else

            bookThumb = getArguments().getInt("bookThumb");
        bookName = getArguments().getInt("bookName");


        Log.d("This is the book Thumb", "" + bookThumb);
        //create Subscription fragment
        View rootView = inflater.inflate(R.layout.fragment_dialog_subscribe, container, false);

        //set the book thumb from the bundle
        ImageView imageView = (ImageView) rootView.findViewById(R.id.bookThumb);
        imageView.setImageResource(bookThumb);

        //set the bookname from the bundle
        tv = (TextView) rootView.findViewById(R.id.book_name);
        tv.setText(bookName);

        Button subscribebtn = (Button) rootView.findViewById(R.id.subscribeButton);
        subscribebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainActivity().onSubscribedButtonClicked(getView());
            }
        });

        Button buyBookbtn = (Button) rootView.findViewById(R.id.buyBookButton);
        buyBookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bookNameString = getString(bookName);
                getMainActivity().onBookPurchaseButtonClicked(getView(), getString(bookName));

            }
        });

 /*       TextView textView = (TextView) rootView.findViewById(R.id.subscribedQuestion);
        if (isSubscribed()) {
            textView.setText("You have subscribed you LEGEND!!");
        } else
            textView.setText("No subscription");
             */

        return rootView;

    }


}


