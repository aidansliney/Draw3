package com.learn.howtodraw.draw;

/**
 * Created by aidansliney on 28/08/2016.
 */

import android.app.Application;

public class GlobalClass extends Application {

    private String name;
    private String email;
    private boolean mBook1;
    private boolean mBook2;
    private boolean mBook3;
    private boolean mBook4;


    public Boolean getbook1bought() {
        return mBook1;
    }

    public void setbook1bought(Boolean aName) {
        mBook1 = aName;
    }

    public Boolean getbook2bought() {
        return mBook2;
    }

    public void setbook2bought(Boolean aName) {
        mBook2 = aName;
    }

    public Boolean getbook3bought() {
        return mBook3;
    }

    public void setbook3bought(Boolean aName) {
        mBook3 = aName;
    }

    public Boolean getbook4bought() {
        return mBook4;
    }

    public void setbook4bought(Boolean aName) {
        mBook4 = aName;
    }


    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String aEmail) {

        email = aEmail;
    }

}