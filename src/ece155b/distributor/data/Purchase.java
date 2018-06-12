/*
 * Appointment.java
 *
 */

package ece155b.distributor.data;

public class Purchase {
    
    String providerName;
    String dateTime;
    
    /** Creates a new instance of purchase item */
    public Purchase() {
    }
    
    public String toXML()
    {
        return "<Purchase>"+
                "</Purchase>";
    }
    
}
