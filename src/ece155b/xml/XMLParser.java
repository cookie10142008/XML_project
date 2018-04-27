package ece155b.xml;

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
    
    private ArrayList<SellSupply> sellItems = new ArrayList<SellSupply>();
    
    public XMLParser() 
    		throws SAXException, IOException, ParserConfigurationException{	

    	
      distributor = new Distributor();
      
      //Create a "parser factory" for creating SAX parsers
      SAXParserFactory sparserfactory = SAXParserFactory.newInstance();

      //Now use the parser factory to create a SAXParser object
      SAXParser sparser = sparserfactory.newSAXParser();

      //Create an instance of this class; it defines all the handler methods
      XMLParser handler = new XMLParser(); //原本是XMLParser()

      //Finally, tell the parser to parse the input and notify the handler
      String fileurl = System.getProperty("user.dir") + File.separator + "hello.xml";
      sparser.parse(fileurl, handler);
     
      handler.readList();     
      
    }
    
    public XMLParser(Distributor dist){
    	this.distributor = dist; 
    }
    
    
    public void characters(char[] buffer, int start, int length) {
        accumulator.append(buffer, start, length);
    }
    
   
    //	Each time a new tag is opened, this method is called
  	public void startElement(String uri,String lname, String name, Attributes attributes)
  	{
  	
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
		String value = accumulator.toString();
	
		// 	Company informations
		if(name.equals ("CompanyName"))
  			distributor.name = value;
  		else if(name.equals ("CompanyAddress"))
  			distributor.address = value;
  
  		// 	Add sell supply and need supply objects
  		else if(name.equals ("SellSupply"))
  			distributor.addSellItem(sSupply);
  		else if(name.equals ("NeedSupply"))
  			distributor.addNeedItem(nSupply);
  		// 	Set parameters of Supply Object
  		else if(name.equals ("SupplyID"))
  			supply.ID = value;
	}
  	
    private void readList() {
/*        System.out.println("No of  the accounts in bank '" + accList.size()  + "'.");
        Iterator<Account> it = accList.iterator();
        while (it.hasNext()) {
               System.out.println(it.next().toString()); 
        } */
 }

}