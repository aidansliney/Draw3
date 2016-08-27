package com.learn.howtodraw.draw;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends DialogFragment {

    public BaseFragment() {
        // Required empty public constructor
    }

    public boolean isSubscribed(){

        return getMainActivity().isSubscribed();
    }

    public MainActivity getMainActivity(){

        return (MainActivity) getActivity();
    }




}
