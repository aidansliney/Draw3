package com.learn.howtodraw.draw;


/**
 * A class that defines constants used throughout the app.
 */
public class Constants {


// In-app billing constants

    // Does the user have an active subscription to the infinite gas plan?
    public static boolean mSubscribed = false;

    // SKUs for our products:
    public static final String[] SKU_BOOK_ARRAY = {"book1", "book2", "book3", "book4", "book5"};
    public static boolean[] mPurchasedBooksArray = {false, false, false, false, false};


    // SKU for our subscription (infinite gas)
    public static final String SKU_SUBSCRIPTION = "subscription";


// Activity request code

    public static final int RC_PURCHASE_REQUEST = 10001;

    /**
     */
// IAB error

    public static final int IAB_PURCHASE_FAILED = 101;
    public static final int IAB_PURCHASE_FAILED_PAYLOAD_PROBLEM = 102;

    /**
     */
    public static final String LOG_IAB = "IAB";


}

