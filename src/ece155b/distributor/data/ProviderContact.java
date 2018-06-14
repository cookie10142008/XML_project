package ece155b.distributor.data;

public class ProviderContact {
    public String URL;
    public int PORT;
    public String Name;
    
    /** Creates a new instance of DistributorContact */ // ProviderContact??
    public ProviderContact() {
    }
    
    public String toXML()
    {
        return "<ProviderContact>"+
                "</ProviderContact>";
    }
}
