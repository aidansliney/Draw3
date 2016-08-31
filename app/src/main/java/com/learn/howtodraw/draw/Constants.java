package com.learn.howtodraw.draw;


/**
 * A class that defines constants used throughout the app.
 *
 */
public class Constants {

    /**
     */
// In-app billing constants

    // Does the user have book1?
   public static boolean mPurchasedBook1 = false;
    // Does the user have book2?
    public static boolean mPurchasedBook2 = false;
    // Does the user have book3?
    public static boolean mPurchasedBook3 = true;
    // Does the user have book4?
    public static boolean mPurchasedBook4 = true;
    // Does the user have book5?
   public static boolean mPurchasedBook5 = true;



// SKUs for our products:

    public static final String[] SKU_BOOK_ARRAY = {"book1","book2","book3","book4","book5"};


    public static final String SKU_CONSUMABLE = "page";  // Consumable

    // SKU for our subscription (infinite gas)
    public static final String SKU_SUBSCRIPTION = "subscription";

    /**
     */
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
    /**
     */
// Graphics for the gas gauge

    // How many units (1/4 tank is our unit) fill in the tank.
    public static final int TANK_MAX = 4;

}

