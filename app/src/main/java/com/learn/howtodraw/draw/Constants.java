package com.learn.howtodraw.draw;

/**
 * A class that defines constants used throughout the app.
 */
public class Constants {
    // Does the user have an active subscription to the infinite gas plan?
    public static boolean mSubscribed = false;

    // SKUs for our products:
    public static final String[] SKU_BOOK_NAME_ARRAY = {"book00","book01", "book02", "book03", "book04", "book05"};
    public static final Integer[] SKU_BOOK_ARRAY_ARRAY = {R.array.book1Build,R.array.book1Build,R.array.book2Build,R.array.book3Build,R.array.book4Build,R.array.book5Build};
    public static final Integer[] SKU_BOOK_NAME_POINTER_ARRAY = {R.string.book01,R.string.book01,R.string.book02,R.string.book03,R.string.book04,R.string.book05};
    public static final Integer[] SKU_BOOK_COVER_ARRAY = {R.drawable.book1cover,R.drawable.book1cover,R.drawable.book2cover,R.drawable.book3cover,R.drawable.book4cover,R.drawable.book5cover};
    public static boolean[] mPurchasedBooksArray = {false, false, false, false, false, false};
    // need to also add books to bookActicty + Third Fragment
    public static final String SKU_SUBSCRIPTION = "subscription";

    public static final int RC_PURCHASE_REQUEST = 10001;
    public static final int IAB_PURCHASE_FAILED = 101;
    public static final int IAB_PURCHASE_FAILED_PAYLOAD_PROBLEM = 102;
    public static final String LOG_IAB = "IAB";

}

