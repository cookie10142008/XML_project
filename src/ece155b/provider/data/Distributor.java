
package ece155b.provider.data;

import java.util.Vector;

public class Distributor {
    public String ssn;
    public String name;
    public String surname;
    public String birthdate;
    public String address;
    public String phone;
    public String sex;
        
    public Distributor()
    {
    
    }
        
    public String toXML() {
        String xml;
        xml ="<Distributor>";
        
        return xml+"</Distributor>";
    }
    
}
