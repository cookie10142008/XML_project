package ece155b.provider.xml;

import ece155b.provider.data.Purchase;
import ece155b.provider.data.Provider;
import ece155b.distributor.DistributorApplet;
import ece155b.distributor.data.NeedSupply;
import ece155b.distributor.data.SellSupply;
import ece155b.distributor.xml.XMLParser;
import ece155b.provider.data.Distributor;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ProParser extends DefaultHandler
{
    public Provider provider;
    private SellSupply sellSupply;
    private StringBuffer accumulator = new StringBuffer();
    private String value,supplyType;
    
    private Distributor distributor;
    private Purchase purchase;
    //新增ProParser() & readXmlFile(String fileurl)
    public ProParser(){
    	provider = new Provider();
    }
    
    public void readXmlFile(String fileurl) throws SAXException, IOException, ParserConfigurationException
	  {
	  		XMLParser handler = new XMLParser();
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
        accumulator.append(buffer, start, length);
    }
    
    public void startElement(String uri,String lname, String tagName, Attributes attributes)
    {
  		value = "";
  		//	New SellSupply starts
  		if(tagName.equals ("SellSupply"))
  		{
  			sellSupply = new SellSupply();// create sellSupply object & start to write in item info
  			supplyType ="sell";
			
			System.out.println(tagName); 
		}
  	
  		// Reset accumulator
  		accumulator.setLength(0);
    }
    
    public void endElement(String uri,String lname,String tagName)
    {
  	 	//Company informations
		if(tagName.equals ("CompanyName")) {
  			System.out.println(value);
  			provider.name = value;
			DistributorApplet.textField_companyName.setText(value);
		}	
  		else if(tagName.equals ("CompanyAddress")) {
  			System.out.println(value);
  			provider.address = value;
			DistributorApplet.textArea_address.setText(value);

  		
  		}
		else if(tagName.equals ("CompanyContact")) {
			System.out.println(value);
			provider.contact = value;
			//DistributorApplet.textField_address.setText(value);
  			DistributorApplet.textArea_contactMe.setText(value);
			
		}
		//	 	Set parameters of Supply Object
		else if(tagName.equals ("SupplyID")) {
		    
			if(supplyType.equals("sell")) {  // judge item needed or sold
				sellSupply.ID = value;
			}
//			else if(supplyType.equals("need")) {
//				needSupply.ID = value;
//			}
			
		}
		else if(tagName.equals ("SupplyName")) {
		    
			if(supplyType.equals("sell")) {  // judge item needed or sold
				sellSupply.name = value;
			}
//			else if(supplyType.equals("need")) {
//				needSupply.name = value;
//			}
			
		}
		else if(tagName.equals ("SupplyBrand")) {
		    
			if(supplyType.equals("sell")) {  // judge item needed or sold
				sellSupply.brand = value;
			}
//			else if(supplyType.equals("need")) {
//				needSupply.brand = value;
//			}
			
		}
		else if(tagName.equals ("SupplyPrice")) {
		    
			if(supplyType.equals("sell")) {  // judge item needed or sold
				sellSupply.price = Double.parseDouble(value);
			}
//			else if(supplyType.equals("need")) {
//				needSupply.price = Double.parseDouble(value);
//			}
			
		}
		else if(tagName.equals ("SupplyAvailable")) {
		    sellSupply.amountAvailable = Integer.parseInt(value);
		}
//		else if(tagName.equals ("SupplyNeeded")) {
//			needSupply.amountNeeded = Integer.parseInt(value);
//		}
		// 	Add sell supply and need supply objects
		else if(tagName.equals ("SellSupply")) {
			provider.addSellItem(sellSupply);
		}
		else if(tagName.equals ("Distributor")) {
			
  			System.out.println(tagName);
  			presentInCustomerTable();
 // 			presentInProviderTable(); //This is to get item from needSupply
		}
		
 //   	String value = accumulator.toString();
        
    }
    
    private void presentInCustomerTable(){
    	
    }
    
    
       
}
