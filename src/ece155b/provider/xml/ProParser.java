package ece155b.provider.xml;

import ece155b.provider.data.Purchase;
import ece155b.provider.data.Provider;
import ece155b.distributor.DistributorApplet;
//import ece155b.distributor.data.NeedSupply;
import ece155b.distributor.data.SellSupply;
import ece155b.distributor.xml.XMLParser;
import ece155b.provider.ProviderApplet;
import ece155b.provider.data.Distributor;

import java.io.File;
import java.io.IOException;
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
    
    private void presentInCustomerTable() //get item from sellSupply
    {
    	ProviderApplet.provTable.setRowCount(0);  //set original 0 row 
  		//problem: can't print all items
  		System.out.println(Provider.sellItems.size() + "*items");
  		int itemCount = Provider.sellItems.size();
  		int index = 0;
  		
  		for(SellSupply item : Provider.sellItems) {
  	        System.out.println("ID:"+item.ID);
  	        DistributorApplet.custTable.addRow(new Vector()); // new a blank row
  	    }
  		//DistributorApplet.custTable.removeRow(0); // remove the original first row 
  		
  		int custRow = DistributorApplet.customerTable.getRowCount(); //get Row Count = 5
        int custCol = DistributorApplet.customerTable.getColumnCount();  //get Column Count = 5
  		
        for(int i = 0; custRow > i; i++){   //from first row to last row
        	for(int j = 0; custCol > j; j++){  //from first column to last column  		
        		if(index < itemCount) {
	        		switch(j) {
		    			case 0:
		    				DistributorApplet.customerTable.setValueAt(Provider.sellItems.get(index).ID,i, j);
		    				break;
		    				
		    			case 1:
		    				DistributorApplet.customerTable.setValueAt(Provider.sellItems.get(index).name,i, j);
		    				break;
		    				
		    			case 2:
		    				DistributorApplet.customerTable.setValueAt(Provider.sellItems.get(index).brand,i, j);
		    				break;
		    				
		    			case 3:
		    				DistributorApplet.customerTable.setValueAt(Provider.sellItems.get(index).price,i, j);
		    				//DistributorApplet.customerTable.setValueAt(String.valueOf(Distributor.sellItems.get(index).price),i, j);
		    				break;
		    				
		    			case 4:
		    				DistributorApplet.customerTable.setValueAt(Provider.sellItems.get(index).amountAvailable,i, j);
		    				//DistributorApplet.customerTable.setValueAt(String.valueOf(Distributor.sellItems.get(index).amountAvailable),i, j);
		    				break;
		    				
	        		} //end switch
        		}// end if
        	}//end column
        	index++;
        }//end row
    	
    }
    
    
       
}
