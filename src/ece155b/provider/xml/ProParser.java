package ece155b.provider.xml;

import ece155b.provider.data.Purchase;
import ece155b.provider.data.Provider;
import ece155b.provider.ProviderApplet;
import ece155b.distributor.DistributorApplet;
import ece155b.distributor.data.ProviderContact;
//import ece155b.distributor.data.NeedSupply;
import ece155b.distributor.data.SellSupply;
import ece155b.provider.ProviderApplet;
import ece155b.provider.data.Distributor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ProParser extends DefaultHandler
{
    public Provider provider;
    private SellSupply sellSupply;
    //private StringBuffer accumulator = new StringBuffer();
    private String value,supplyType;
    public  static ArrayList<String> distributorList;
    private Distributor distributor;
    private Purchase purchase;
    //新增ProParser() & readXmlFile(String fileurl)
    public ProParser(){
    	provider = new Provider();
    }
    
    public void readXmlFile(String fileurl) throws SAXException, IOException, ParserConfigurationException
    {
    	ProParser handler = new ProParser();
    	try
    	{
    		SAXParserFactory factory = SAXParserFactory.newInstance( );
    		SAXParser parser = factory.newSAXParser();
    		parser.parse(fileurl,handler);


    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	//return myparser.distributor;
    }
    //新增結束  
    public ProParser(File file) //delete this function??????
    {

    }

    public void characters(char[] buffer, int start, int length)
    {
        value = new String(buffer, start, length); // store the plain text to temp

    }

    public void startElement(String uri,String lname, String tagName, Attributes attributes)
    {
    	value = "";
    	//	New SellSupply starts
    	if(tagName.equals ("SellSupply")){
    		
    		sellSupply = new SellSupply();// create sellSupply object & start to write in item info
    		supplyType ="sell";
    		System.out.println(tagName); 
    	}
    	else if(tagName.equals("DistributorName")) {
    		distributorList = new ArrayList();
    		
    	}

    }
    
    public void endElement(String uri,String lname,String tagName)
    {
  	 	//Company informations
		if(tagName.equals ("CompanyName")) {
  			System.out.println(value);
  			provider.name = value;
			ProviderApplet.textField_companyName.setText(value);
		}	
  		else if(tagName.equals ("CompanyAddress")) {
  			System.out.println(value);
  			provider.address = value;
			ProviderApplet.textArea_address.setText(value);

  		
  		}
		else if(tagName.equals ("CompanyContact")) {
			System.out.println(value);
			provider.contact = value;
  			ProviderApplet.textArea_contactMe.setText(value);
			
		}
		//	 	Set parameters of Supply Object
		else if(tagName.equals ("SupplyID")) {
		    
			if(supplyType.equals("sell")) {  // judge item needed or sold
				sellSupply.ID = value;
				System.out.println(value);
			}
			
		}
		else if(tagName.equals ("SupplyName")) {
		    
			if(supplyType.equals("sell")) {  // judge item needed or sold
				sellSupply.name = value;
				System.out.println(value);
			}
			
		}
		else if(tagName.equals ("SupplyBrand")) {
		    
			if(supplyType.equals("sell")) {  // judge item needed or sold
				sellSupply.brand = value;
			}
			
		}
		else if(tagName.equals ("SupplyPrice")) {
		    
			if(supplyType.equals("sell")) {  // judge item needed or sold
				sellSupply.price = Double.parseDouble(value);
			}
			
		}
		else if(tagName.equals ("SupplyAvailable")) {
		    sellSupply.amountAvailable = Integer.parseInt(value);
		}
		// 	Add sell supply and need supply objects
		else if(tagName.equals ("SellSupply")) {
			provider.addSellItem(sellSupply);
		}
		else if(tagName.equals("Name")) {
			distributorList.add(value); 
			
			
		}
		else if(tagName.equals ("Provider")) {
			
  			System.out.println(tagName);
  			presentInCustomerTable();
  			presentInDistNameTable();
 
		}
		
		        
    }
    
    private void presentInCustomerTable() //get item from sellSupply
    {
    	ProviderApplet.custTable.setRowCount(0);  //set original 0 row 
  		int itemCount = Provider.sellItems.size();
  		int index = 0;
  		
  		for(SellSupply item : Provider.sellItems) {
  	        System.out.println("ID:"+item.ID);
  	        ProviderApplet.custTable.addRow(new Vector()); // new a blank row
  	    }
  		//DistributorApplet.custTable.removeRow(0); // remove the original first row 
  		
  		int custRow = ProviderApplet.customerTable.getRowCount(); //get Row Count = 5
        int custCol = ProviderApplet.customerTable.getColumnCount();  //get Column Count = 5
  		
        for(int i = 0; custRow > i; i++){   //from first row to last row
        	for(int j = 0; custCol > j; j++){  //from first column to last column  		
        		if(index < itemCount) {
	        		switch(j) {
		    			case 0:
		    				ProviderApplet.customerTable.setValueAt(Provider.sellItems.get(index).ID,i, j);
		    				break;
		    				
		    			case 1:
		    				ProviderApplet.customerTable.setValueAt(Provider.sellItems.get(index).name,i, j);
		    				break;
		    				
		    			case 2:
		    				ProviderApplet.customerTable.setValueAt(Provider.sellItems.get(index).brand,i, j);
		    				break;
		    				
		    			case 3:
		    				ProviderApplet.customerTable.setValueAt(Provider.sellItems.get(index).price,i, j);
		    				//DistributorApplet.customerTable.setValueAt(String.valueOf(Distributor.sellItems.get(index).price),i, j);
		    				break;
		    				
		    			case 4:
		    				ProviderApplet.customerTable.setValueAt(Provider.sellItems.get(index).amountAvailable,i, j);
		    				//DistributorApplet.customerTable.setValueAt(String.valueOf(Distributor.sellItems.get(index).amountAvailable),i, j);
		    				break;
		    				
	        		} //end switch
        		}// end if
        	}//end column
        	index++;
        }//end row
    	
    }
    
    
    private void presentInDistNameTable() //get dist list from distributorList
    {
    	
    	ProviderApplet.distNameTable.setRowCount(0);  //set original 0 row 
  		System.out.println(distributorList.size() + "*items");
  		int nameCount = distributorList.size();
  		int index = 0;
  		
  		for(String distname : distributorList) {
  	        System.out.println("Name:"+distname);
  	        ProviderApplet.distNameTable.addRow(new Vector()); // new a blank row
  	    }
  		
  		int custRow = ProviderApplet.distributorNameTable.getRowCount(); //get Row Count = 5
        //int custCol = ProviderApplet.distributorNameTable.getColumnCount();  //get Column Count = 5
  		
        for(int i = 0; custRow > i; i++){   //from first row to last row
        	if(nameCount > index) {
        		ProviderApplet.distributorNameTable.setValueAt(distributorList.get(index),i,0);
        	}
        	index++;
        }//end row
    	
    }
    
    
}
