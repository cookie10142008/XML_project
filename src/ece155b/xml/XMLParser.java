package ece155b.xml;

import ece155b.DistributorApplet;
import ece155b.data.Distributor;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.data.Supply;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class XMLParser extends DefaultHandler {
    public Distributor distributor;
    private SellSupply sSupply;
    private NeedSupply nSupply;
    private Supply supply;
    private StringBuffer accumulator = new StringBuffer(); 
    private String value;
    
    //private ArrayList<SellSupply> sellItems = new ArrayList<SellSupply>(); 
    
    public XMLParser(){	
    	distributor = new Distributor(); // read in loaded info & write into new item list
      //this.distributor = dist; 

//      SAXParserFactory sparserfactory = SAXParserFactory.newInstance();//Create a "parser factory" for creating SAX parsers
//      SAXParser sparser = sparserfactory.newSAXParser(); //Now use the parser factory to create a SAXParser object
//      XMLParser handler = new XMLParser(); //Create an instance of this class; it defines all the handler methods

      //Finally, tell the parser to parse the input and notify the handler
      
      //String fileurl = System.getProperty("user.dir") + File.separator + "hello.xml";
      
//      sparser.parse(fileurl, handler);
//     
//      handler.readList();     
      
    }
    
	  public void readXmlFile(String fileurl) throws SAXException, IOException, ParserConfigurationException
	  {
	  		XMLParser myparser = new XMLParser();
			try
		    {
		    	SAXParserFactory factory = SAXParserFactory.newInstance( );
				SAXParser parser = factory.newSAXParser();
				
				XMLParser handler = new XMLParser(); 
				
				
				
				parser.parse(fileurl,myparser);
				
				
		    }
		    catch (Exception ex)
		    {
		    	ex.printStackTrace();
		    }
		    //return myparser.distributor;
	  }
    
    
    public void characters(char[] buffer, int start, int length) {
        //accumulator.append(buffer, start, length);
        
        value = new String(buffer, start, length); // store the plain text to temp

    }
    
   
    //	Each time a new tag is opened, this method is called
  	public void startElement(String uri,String lname, String name, Attributes attributes)
  	{
  		value = "";
  		//	New SellSupply starts
  		if(name.equals ("SellSupply"))
  		{
  			sSupply = new SellSupply();
			supply = new Supply();
			sSupply.supply = supply;
			
/*			String tag = name;
			System.out.println(tag); */
		}

		//	New NeedSupply starts
		else if(name.equals ("NeedSupply"))
  		{
  			nSupply = new NeedSupply();
  			supply = new Supply();
			nSupply.supply = supply;
  		}
  	
  		// Reset accumulator
  		accumulator.setLength(0);
  	}
    
  	public void endElement(String uri,String lname,String name)
	{
		
  	 	//Company informations
		if(name.equals ("CompanyName")) {
  			System.out.println(value);
			DistributorApplet.textField_companyName.setText(value);
		}	
  		else if(name.equals ("CompanyAddress")) {
  			System.out.println(value);
  			DistributorApplet.textField_contactMe.setText(value);
  		
  		}
		else if(name.equals ("CompanyContact")) {
			System.out.println(value);
			DistributorApplet.textField_address.setText(value);
		
		}
  			
  		
  		
  		
    	//String value = accumulator.toString();
//		// 	Company informations
//		if(name.equals ("CompanyName"))
//  			distributor.name = value;
//  		else if(name.equals ("CompanyAddress"))
//  			distributor.address = value;
//  
//  		// 	Add sell supply and need supply objects
//  		else if(name.equals ("SellSupply"))
//  			distributor.addSellItem(sSupply);
//  		else if(name.equals ("NeedSupply"))
//  			distributor.addNeedItem(nSupply);
//  		// 	Set parameters of Supply Object
//  		else if(name.equals ("SupplyID"))
//  			supply.ID = value;
	}
  	
    private void readList() {
/*        System.out.println("No of  the accounts in bank '" + accList.size()  + "'.");
        Iterator<Account> it = accList.iterator();
        while (it.hasNext()) {
               System.out.println(it.next().toString()); 
        } */
 }

}