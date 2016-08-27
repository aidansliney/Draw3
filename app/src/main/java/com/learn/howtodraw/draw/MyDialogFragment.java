package com.learn.howtodraw.draw;



import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by aidansliney on 21/08/2016.
 */
public class MyDialogFragment extends BaseFragment  {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        if (MainActivity.menuSelected == "about") {
            View rootView = inflater.inflate(R.layout.fragment_dialog_about, container, false);
            getDialog().setTitle("Simple Dialog");
            return rootView;
        } else {
            View rootView = inflater.inflate(R.layout.fragment_dialog_subscribe, container, false);
            getDialog().setTitle("Simple Dialog");


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
                    getMainActivity().onUpgradeAppButtonClicked(getView());
                }
            });



            TextView textView = (TextView)rootView.findViewById(R.id.subscribedQuestion);
            if (isSubscribed()) {
                textView.setText("You have subscribed you LEGEND!!");
            }
            else
                textView.setText("No subscription");
            return rootView;
        }
    }




}


