package ece155b.xml;

import ece155b.DistributorApplet;
import ece155b.data.Distributor;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.data.Supply;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
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
    
  	public void endElement(String uri,String lname,String tagName)
	{
		
  	 	//Company informations
		if(tagName.equals ("CompanyName")) {
  			System.out.println(value);
			DistributorApplet.textField_companyName.setText(value);
		}	
  		else if(tagName.equals ("CompanyAddress")) {
  			System.out.println(value);
  			DistributorApplet.textField_contactMe.setText(value);
  		
  		}
		else if(tagName.equals ("CompanyContact")) {
			System.out.println(value);
			DistributorApplet.textField_address.setText(value);
		
		}
  			
		String id="", name="", brand="";
        double price = 0;
        int count = 0;
//        System.out.println("col欄位數:"+col+"; row列數:"+row);
       
        // !!! present info on gui !!!
        for(int i = 0; 5 > i; i++){   //from first row to last row
        	for(int j = 0; 5 > j; j++){  //row1: from first column to last column  		
        		switch(j) {
    			case 0:
    				id = (String) DistributorApplet.customerTable.getValueAt(i, j);
    				break;
    				
    			case 1:
    				name = (String) DistributorApplet.customerTable.getValueAt(i, j);
    				break;
    				
    			case 2:
    				brand = (String) DistributorApplet.customerTable.getValueAt(i, j);
    				break;
    			
    			case 3:
    				try
    				{
    					String tempPrice = (String) DistributorApplet.customerTable.getValueAt(i, j);
    					price = Double.parseDouble(tempPrice); 		        					
    				}
    				catch (NumberFormatException ex)
    				{
    					JOptionPane.showMessageDialog(null, "價格輸入不正確，請輸入數字");		        					
    				}
    				break;
    			
    			case 4:
    				try
    				{
    					String tempCount = (String) DistributorApplet.customerTable.getValueAt(i, j);
    					count = Integer.parseInt(tempCount);
    				}
    				catch (NumberFormatException ex)
    				{
    					JOptionPane.showMessageDialog(null, "數量輸入不正確，請輸入數字");
    				}
//    				count = Integer.parseInt((String) customerTable.getValueAt(i, j));
//    				break;
    			
        		}
        	}
        			
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