package ece155b.data;

import java.util.Vector;

public class Distributor extends Company
{
	
	public static Vector <SellSupply> sellItems;	// Vector of sell items (store many items' info)
	public static Vector <NeedSupply> needItems;	// Vector of items needed
	
	
	
	public Distributor()
	{
		sellItems = new Vector<SellSupply>();
		needItems = new Vector<NeedSupply>();
		
		//SellSupply item1 = new SellSupply("12","cookie","cosmed",100,3000);
//		SellSupply item2 = new SellSupply("11","chocolate","cosmed",20,5000);
		//sellItems.add(item1);
//		sellItems.add(item2);
		
//		NeedSupply item3 = new NeedSupply("12","cookie","7-11",100,3000);
//		NeedSupply item4 = new NeedSupply("11","chocolate","7-11",20,5000);
//		needItems.add(item3);
//		needItems.add(item4);
		
		
	}

	public void addSellItem(SellSupply item)
	{
		sellItems.add(item);
//		System.out.println("get id"+item.ID);

//		for(SellSupply Item : sellItems) {
//  	        System.out.println("ID:"+Item.ID);
//  	        
//  	    }
	}


	public void addNeedItem(NeedSupply item)
	{
		needItems.add(item);     //新增	
		//System.out.println("get id"+item.ID);
	}

	public String toXML()
	{
		System.out.println((sellItems.get(0)).toXML());
		String returnStr="";
		returnStr += "<Distributor>";
		
		returnStr += "<CompanyInfo>";
		returnStr += "<CompanyName>"+name+"</CompanyName>";
		returnStr += "<CompanyAddress>"+address+"</CompanyAddress>";
		returnStr += "<CompanyContact>"+contact+"</CompanyContact>";
		returnStr += "</CompanyInfo>";

		returnStr += "<ItemsSold>";
		// template
//		for (int i = 0; i < sellItems.size(); i++) 
//		returnStr += ((SellSupply)sellItems.elementAt(i)).toXML();
		for (int i = 0; i < sellItems.size(); i++) {
			returnStr += (sellItems.get(i)).toXML();
		    
		}
		returnStr += "</ItemsSold>";

		
		returnStr += "<ItemsNeeded>";
		for (int i = 0; i < needItems.size(); i++) {
			returnStr += (needItems.get(i)).toXML();
		    
		}
		returnStr += "</ItemsNeeded>";
		
		
		returnStr += "</Distributor>";
		return returnStr;
	}

	public String toString()
	{
		String returnStr="";
		returnStr += "| CompanyInfo\n";
		returnStr += "|| CompanyName: "+name+"\n";
		returnStr += "|| CompanyAddress: "+address+"\n";
		returnStr += "|| CompanyContact: "+contact+"\n";
		returnStr += "\n";

		returnStr += "| ItemsSold\n";
		
//		for (int i = 0; i < sellItems.size(); i++) {
//			returnStr += ((SellSupply)sellItems.elementAt(i)).toString();
//		}
		
		returnStr += "\n";

		return returnStr;
	}
}
