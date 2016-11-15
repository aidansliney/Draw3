package com.learn.howtodraw.draw;

import com.learn.howtodraw.draw.util.Book;

/**
 * A class that defines constants used throughout the app.
 */
public class Constants {
    public static boolean mSubscribed = false;
    // Step 0 - add cover ISO and end tutorial images
    //Step 1 - fill in the below
    public static final String[] SKU_BOOK_NAME_ARRAY = {"book00","book01", "book02", "book03", "book04", "book05", "book06", "book07", "book08", "book10"}; //Step1   //Step2 the 3 lines below
    public static final Integer[] SKU_BOOK_ARRAY_ARRAY = {R.array.book1Build,R.array.book1Build,R.array.book2Build,R.array.book3Build,R.array.book4Build,R.array.book5Build,R.array.book6Build,R.array.book7Build,R.array.book8Build, R.array.book10Build};
    public static final Integer[] SKU_BOOK_NAME_POINTER_ARRAY = {R.string.book00,R.string.book01,R.string.book02,R.string.book03,R.string.book04,R.string.book05,R.string.book06,R.string.book07,R.string.book08, R.string.book10};
    public static final Integer[] SKU_BOOK_COVER_ARRAY = {R.drawable.book1cover,R.drawable.book1cover,R.drawable.book2cover,R.drawable.book3cover,R.drawable.book4cover,R.drawable.book5cover,R.drawable.book6cover,R.drawable.book7cover,R.drawable.book8cover, R.drawable.book10cover};
    public static boolean[] mPurchasedBooksArray = {false, false, false, false, false, false, false, false, true, true};
    //Step 3 - Deep link mPurchasedBooksArray
    //Step 4 - Books.xml from bottom
    //Step 5 - Strings.xml
    public static final String SKU_SUBSCRIPTION = "subscription";
    public static final int RC_PURCHASE_REQUEST = 10001;
    public static final int IAB_PURCHASE_FAILED = 101;
    public static final int IAB_PURCHASE_FAILED_PAYLOAD_PROBLEM = 102;
    public static final String LOG_IAB = "IAB";


    // New content
    public static final int frontBookLink = R.array.book10Build;
    public static final int frontBookImage = R.drawable.book10coverinside;
    public static final int frontBookH1 = R.string.book10heading1;



    Book[] arr = new Book[100];  // new stands for create an array object



}
