
package ece155b.common;

/**
 *  You might have Message class similar to XMLParser class,
 *  When you receive a message you will parse it and assign
 *  necesary values to Message object
 */
public class Common {
    public static String //message type
            BROADCAST                   = "Broadcast",
            AUTHENTICATE_DISTRIBUTOR        = "Authenticate",
            AUTHENTICATE_DISTRIBUTOR_REPLY  = "AuthenticateR",
            REQUEST_SUPPLY_LIST                = "RequestSupply",
            REQUEST_SUPPLY_LIST_REPLY          = "RequestSupplyR",
            REQUEST_PURCHASE         = "RequestPurchase",
            REQUEST_PURCHASE_REPLY   = "RequestPurchaseR",
            TERMINATE                   = "Terminate";
}