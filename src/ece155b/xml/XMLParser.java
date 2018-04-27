package ece155b.xml;

import ece155b.data.Distributor;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.data.Supply;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class XMLParser extends DefaultHandler {
    public Distributor distributor;
    private SellSupply ssupply;
    private NeedSupply nsupply;
    private Supply supply;
    private StringBuffer accumulator = new StringBuffer();  

    
    public XMLParser() /*throws ParserConfigurationException, SAXException*/{
      distributor = new Distributor();
      
/*		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();  */
    }
    public void characters(char[] buffer, int start, int length) {
        accumulator.append(buffer, start, length);
    }
    
    // - - - - - - - - - - - - - - - - - - - - -
    //	Each time a new tag is opened,
    //		this method is called
  	public void startElement(String uri,String lname, String name, Attributes attributes)
  	{
  		// - - - - - - - - - - - - - - - - - - - - -
  		//	New SellSupply starts
  		if(name.equals ("SellSupply"))
  		{
  			ssupply = new SellSupply();
			supply = new Supply();
			ssupply.supply = supply;
			
/*			String tag = name;
			System.out.println(tag); */
		}
		// - - - - - - - - - - - - - - - - - - - - -
		//	New NeedSupply starts
		else if(name.equals ("NeedSupply"))
  		{
  			nsupply = new NeedSupply();
  			supply = new Supply();
			nsupply.supply = supply;
  		}
  		// - - - - - - - - - - - - - - - - - - - - -
  		// Reset accumulator
  		accumulator.setLength(0);
  	}
    
  	public void endElement(String uri,String lname,String name)
	{
		String value = accumulator.toString();
		// - - - - - - - - - - - - - - - - - - - - -
		// 	Company informations
		if(name.equals ("CompanyName"))
  			distributor.name = value;
  		else if(name.equals ("CompanyAddress"))
  			distributor.address = value;
  		// - - - - - - - - - - - - - - - - - - - - -
  		// 	Add sell supply and need supply objects
  		else if(name.equals ("SellSupply"))
  			distributor.addSellItem(ssupply);
  		else if(name.equals ("NeedSupply"))
  			distributor.addNeedItem(nsupply);
  		// - - - - - - - - - - - - - - - - - - - - -
  		// 	Set parameters of Supply Object
  		else if(name.equals ("SupplyID"))
  			supply.ID = value;
	}
	
/*	File savefile = new File("hello.xml"); 
	saxParser.parse(savefile, handler); */
}