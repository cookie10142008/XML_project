package ece155b.provider.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFileChooser;

import ece155b.distributor.data.SellSupply;

public class Provider {
	
	public static Vector <SellSupply> sellItems;	// Vector of sell items (store many items' info)
    
    public String name = "MyName";
    public String lastname = "MyLastName";
    
    public File file;
    Vector <Distributor> distributors;
    
    public Provider()
    {
    	sellItems = new Vector<SellSupply>();
        distributors = new Vector<Distributor>();
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
        
	xml += "</Provider>";
        
        return xml;
    }
    
        
    public boolean loginCheck(String ssn)
    {
        // Check if distributor exists
        return true;
    }
    
}