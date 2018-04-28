package ece155b.xml;

import ece155b.DistributorApplet;
import ece155b.data.Distributor;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.data.Supply;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class XMLParser extends DefaultHandler {
    public Distributor distributor;
    private SellSupply sellSupply;
    private NeedSupply needSupply;
    //private Supply supply;
    private StringBuffer accumulator = new StringBuffer(); 
    private String value,supplyType;
    
    public XMLParser(){	
    	distributor = new Distributor(); // read in loaded info & write into new item list
      //this.distributor = dist; 

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
    
    
    public void characters(char[] buffer, int start, int length) {
        //accumulator.append(buffer, start, length);
        
        value = new String(buffer, start, length); // store the plain text to temp

    }
    
   
    //	Each time a new tag is opened, this method is called
  	public void startElement(String uri,String lname, String tagName, Attributes attributes)
  	{
  		value = "";
  		//	New SellSupply starts
  		if(tagName.equals ("SellSupply"))
  		{
  			sellSupply = new SellSupply();// create sellSupply object & start to write in item info
  			supplyType ="sell";
//			supply = new Supply();
//			sSupply.supply = supply;
			
			System.out.println(tagName); 
		}

		//	New NeedSupply starts
		else if(tagName.equals ("NeedSupply"))
  		{
  			needSupply = new NeedSupply();
  			supplyType ="need";
  			
//  			supply = new Supply();
//			nSupply.supply = supply;
  		}
  	
  		// Reset accumulator
  		accumulator.setLength(0);
  	}
    
  	public void endElement(String uri,String lname,String tagName)
	{
		
  	 	//Company informations
		if(tagName.equals ("CompanyName")) {
  			System.out.println(value);
  			distributor.name = value;
			DistributorApplet.textField_companyName.setText(value);
		}	
  		else if(tagName.equals ("CompanyAddress")) {
  			System.out.println(value);
  			distributor.address = value;
  			DistributorApplet.textField_contactMe.setText(value);
  		
  		}
		else if(tagName.equals ("CompanyContact")) {
			System.out.println(value);
			distributor.contact = value;
			DistributorApplet.textField_address.setText(value);
		
		}
		//	 	Set parameters of Supply Object
		else if(tagName.equals ("SupplyID")) {
		    
			if(supplyType.equals("sell")) {  // judge item needed or sold
				sellSupply.ID = value;
				//System.out.println("add id:"+value);
			}
			else if(supplyType.equals("need")) {
				needSupply.ID = value;
				//System.out.println("add id:"+value);
				
			}
			
		}
		// 	Add sell supply and need supply objects
		else if(tagName.equals ("SellSupply")) {
			distributor.addSellItem(sellSupply);
		}
		else if(tagName.equals ("NeedSupply")) {
			distributor.addNeedItem(needSupply);
		}
		else if(tagName.equals ("Distributor")) {
			
  			System.out.println(tagName);
  			presentInTable();
		}
		

		
    	//String value = accumulator.toString();

	}
  	
  	private void presentInTable() //get item from sellSupply
  	{
  		
  		//problem: can't print all items
  		System.out.println(Distributor.sellItems.size() + "*items");
  		 
  		for(SellSupply item : Distributor.sellItems) {
  	        System.out.println("ID:"+item.ID);
  	        
  	    }
  		 
//  		for (int i = 0; i < Distributor.sellItems.size(); i++) {
//  			System.out.println(Distributor.sellItems.get(i).ID);
//		    
//		} 
  		
  		/*
  		int custRow = DistributorApplet.customerTable.getRowCount(); //get Row Count = 5
        int custCol = DistributorApplet.customerTable.getColumnCount();  //get Column Count = 5
		String id="", name="", brand="";
       
  	// !!! present info on gui !!!
        for(int i = 0; custRow > i; i++){   //from first row to last row
        	for(int j = 0; custCol > j; j++){  //from first column to last column  		
        		switch(j) {
    			case 0:
    				DistributorApplet.customerTable.setValueAt(id,i, j);
    				break;
    				
//    			case 1:
//    				name = (String) DistributorApplet.customerTable.getValueAt(i, j);
//    				break;
//    				
//    			case 2:
//    				brand = (String) DistributorApplet.customerTable.getValueAt(i, j);
//    				break;
//    			
//    			
    			
        		}
        	}
        			
        }
  		
  		
  		
  		*/
  		
  	}// end presentInTable function
  	
  	
  	
  	
  	
    private void readList() {
/*        System.out.println("No of  the accounts in bank '" + accList.size()  + "'.");
        Iterator<Account> it = accList.iterator();
        while (it.hasNext()) {
               System.out.println(it.next().toString()); 
        } */
 }

}