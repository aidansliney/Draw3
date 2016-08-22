package com.learn.howtodraw.draw;



import android.os.Bundle;

import android.support.v4.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aidansliney on 21/08/2016.
 */
public class MyDialogFragment extends DialogFragment  {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        if (MainActivity.menuSelected=="about" ) {
            View rootView = inflater.inflate(R.layout.fragment_dialog_about, container, false);
            getDialog().setTitle("Simple Dialog");
            return rootView;
        }
        else
        {
            View rootView = inflater.inflate(R.layout.fragment_dialog_subscribe, container, false);
            getDialog().setTitle("Simple Dialog");
            return rootView;
        }
    }
}


