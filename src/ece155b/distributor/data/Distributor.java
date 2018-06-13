package ece155b.distributor.data;

import java.util.Vector;

public class Distributor extends Company
{
	
	public static Vector <SellSupply> sellItems;	// Vector of sell items (store many items' info)
	public static Vector <NeedSupply> needItems;	// Vector of items needed
	
	
	
	public Distributor()
	{
		sellItems = new Vector<SellSupply>();
		needItems = new Vector<NeedSupply>();						
	}

	public void addSellItem(SellSupply item)
	{
		sellItems.add(item);

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
