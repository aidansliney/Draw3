package com.learn.howtodraw.draw;



import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;


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

    public boolean hasBook1(){
        return getMainActivity().hasBook1();
    }

    public boolean hasBook2(){
        return getMainActivity().hasBook2();
    }
    public boolean hasBook3(){
        return getMainActivity().hasBook3();
    }
    public boolean hasBook4(){return getMainActivity().hasBook4();}
    public boolean hasBook5(){return getMainActivity().hasBook5();}



    public MainActivity getMainActivity(){

        return (MainActivity) getActivity();
    }




}
