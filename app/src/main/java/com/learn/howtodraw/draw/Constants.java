package com.learn.howtodraw.draw;


/**
 * A class that defines constants used throughout the app.
 *
 */
public class Constants {

    /**
     */
// In-app billing constants

// SKUs for our products: the premium upgrade (non-consumable)
// and gas (consumable)
    public static final String SKU_PREMIUM = "book";
    public static final String SKU_GAS = "page";

    // SKU for our subscription (infinite gas)
    public static final String SKU_INFINITE_GAS = "subscription";

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
    public static final String LOG_IAB = "Trivial Drive IAB";
    /**
     */
// Graphics for the gas gauge

    // How many units (1/4 tank is our unit) fill in the tank.
    public static final int TANK_MAX = 4;

}

