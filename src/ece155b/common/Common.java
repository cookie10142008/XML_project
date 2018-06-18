
package ece155b.common;

/**
 *  You might have Message class similar to XMLParser class,
 *  When you receive a message you will parse it and assign
 *  necessary values to Message object
 */
public class Common {
	
	
	
	
	
	
    public static String //message type
            BROADCAST                   = "Broadcast",
            AUTHENTICATE_DISTRIBUTOR        = "Authenticate",
            AUTHENTICATE_DISTRIBUTOR_REPLY  = "Authenticate_Reply",
            REQUEST_SUPPLY_LIST                = "Request_Supply",
            REQUEST_SUPPLY_LIST_REPLY          = "Request_Supply_Reply",
            REQUEST_PURCHASE         = "Request_Purchase",
            REQUEST_PURCHASE_REPLY   = "Request_Purchase_Reply",
            TERMINATE                   = "Terminate";
}