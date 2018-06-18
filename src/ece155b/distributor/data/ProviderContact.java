package ece155b.distributor.data;

import java.util.ArrayList;

public class ProviderContact {
	
	public static ArrayList<ProviderContact> avai_provider;
    public String URL;
    public int PORT;
    public String Name;
    
    /** Creates a new instance of DistributorContact */ // ProviderContact??
    public ProviderContact() {
    	avai_provider = new ArrayList();
    }
    
    public String toXML()
    {
        return "<ProviderContact>"+
                "</ProviderContact>";
    }
    
    public String toString()
    {
        return "Provider contact:\nPort: "+PORT+
                "\nName: "+Name;
               
    } 
    
    
}
