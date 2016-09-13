package com.learn.howtodraw.draw;


/**
 * A class that defines constants used throughout the app.
 */
public class Constants {


// In-app billing constants

    // Does the user have an active subscription to the infinite gas plan?
    public static boolean mSubscribed = false;

    // SKUs for our products:
    public static final String[] SKU_BOOK_NAME_ARRAY = {"book1", "book2", "book3", "book4", "book5"};
    public static final Integer[] SKU_BOOK_ARRAY_ARRAY = {R.array.book1Build,R.array.book2Build,R.array.book3Build,R.array.book4Build,R.array.book5Build};
    public static boolean[] mPurchasedBooksArray = {false, false, false, false, false};

    public static final String SKU_SUBSCRIPTION = "subscription";

    public static final int RC_PURCHASE_REQUEST = 10001;
    public static final int IAB_PURCHASE_FAILED = 101;
    public static final int IAB_PURCHASE_FAILED_PAYLOAD_PROBLEM = 102;
    public static final String LOG_IAB = "IAB";


}

