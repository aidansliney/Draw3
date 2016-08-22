package com.learn.howtodraw.draw;



import android.os.Bundle;

import android.support.v4.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aidansliney on 21/08/2016.
 */
public class MyDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       // String gmi = getMenuItem();
        //if (gmi == "about")
        View rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        getDialog().setTitle("Simple Dialog");
        return rootView;
    }
}


