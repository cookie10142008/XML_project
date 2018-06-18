package ece155b.provider.data;

//import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
//import java.io.FileWriter;
//import java.util.Date;
import java.util.Vector;
//import javax.swing.JFileChooser;

import ece155b.distributor.data.Company;
import ece155b.distributor.data.SellSupply;

public class Provider extends Company{
	
	public static Vector <SellSupply> sellItems;	// Vector of sell items (store many items' info)
    
    public String name ;
    //public String lastname = "MyLastName";
    
    public File file;
    //public static ArrayList<String> dist_list = new ArrayList<String>(Arrays.asList("CCU", "NTU", "Bob","dist name: MAIN()"));
    Vector <Distributor> distributors; // not yet to use
    
    public Provider()
    {
    	sellItems = new Vector<SellSupply>();
        distributors = new Vector<Distributor>();
    }
    //新增addSellItem
	public void addSellItem(SellSupply item)
	{
		sellItems.add(item);

	}
    
    public void addDistributor(Distributor distributor)
    {
        
    }
    
    public void save()
    {
        
    }
    
    public String toXML()
    {
        String xml = "<Provider>";
        
        xml += "<CompanyInfo>";
        xml += "<CompanyName>"+name+"</CompanyName>";
        xml += "<CompanyAddress>"+address+"</CompanyAddress>";
        xml += "<CompanyContact>"+contact+"</CompanyContact>";
        xml += "</CompanyInfo>";

        xml += "<ItemsSold>";
		for (int i = 0; i < sellItems.size(); i++) {
			xml += (sellItems.get(i)).toXML();		    
		}
		xml += "</ItemsSold>";
        
        xml += "</Provider>";
        
        return xml;
    }
    
        
    public boolean loginCheck(String ssn)
    {
        // Check if distributor exists
    	System.out.print("provider.java");
        return true;
    }
    
}