package com.learn.howtodraw.draw;

import com.learn.howtodraw.draw.util.Book;

/**
 * A class that defines constants used throughout the app.
 */
public class Constants {
    public static boolean mSubscribed = false;
    // Step 0 - add cover ISO and end tutorial images
    //Step 1 - fill in the below
    public static final String[] SKU_BOOK_NAME_ARRAY = {"book00","book01", "book02", "book03", "book04", "book05", "book06", "book07", "book08", "book10", "book11"}; //Step1   //Step2 the 3 lines below
    public static final Integer[] SKU_BOOK_ARRAY_ARRAY = {R.array.book1Build,R.array.book1Build,R.array.book2Build,R.array.book3Build,R.array.book4Build,R.array.book5Build,R.array.book6Build,R.array.book7Build,R.array.book8Build, R.array.book10Build, R.array.book11Build};
    public static final Integer[] SKU_BOOK_NAME_POINTER_ARRAY = {R.string.book00,R.string.book01,R.string.book02,R.string.book03,R.string.book04,R.string.book05,R.string.book06,R.string.book07,R.string.book08, R.string.book10, R.string.book11};
    public static final Integer[] SKU_BOOK_COVER_ARRAY = {R.drawable.book1cover,R.drawable.book1cover,R.drawable.book2cover,R.drawable.book3cover,R.drawable.book4cover,R.drawable.book5cover,R.drawable.book6cover,R.drawable.book7cover,R.drawable.book8cover, R.drawable.book10cover, R.drawable.book11cover};
    public static boolean[] mPurchasedBooksArray = {false, false, false, false, false, false, false, false, false, true, false};
    //Step 3 - Deep link mPurchasedBooksArray (to make free)
    //Step 4 - Books.xml from bottom
    //Step 5 - Strings.xml
    public static final String SKU_SUBSCRIPTION = "subscription";
    public static final int RC_PURCHASE_REQUEST = 10001;
    public static final int IAB_PURCHASE_FAILED = 101;
    public static final int IAB_PURCHASE_FAILED_PAYLOAD_PROBLEM = 102;
    public static final String LOG_IAB = "IAB";


    // New content
    public static final int frontBookLink = R.array.book3Build;
    public static final int frontBookImage = R.drawable.book3cover;
    public static final int frontBookH1 = R.string.book3heading1;

    // early access Content
    public static final int subBookLink = R.array.book10Build;
    public static final int subBookImage = R.drawable.book10cover;
    public static final int subBookH1 = R.string.book10heading1;

    // free tutorial
    public static final int freeImageA = R.drawable.b10t04p20;
    public static final int freeH1A = R.string.book10heading1;
    public static final String freeIDA = "b10t04";
    public static final String freeBookA = "book10";
    public static final int freeHelpA = R.array.b10t04Help;







    Book[] arr = new Book[100];  // new stands for create an array object



}
