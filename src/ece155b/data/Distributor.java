package ece155b.data;

import java.util.Vector;

public class Distributor extends Company
{
	
	Vector sellItems;	// Vector of sell items
	Vector needItems;	// Vector of items needed

	public Distributor()
	{
		sellItems = new Vector ();
		needItems = new Vector ();
	}

	public void addSellItem(SellSupply ss)
	{
	}

	public void addNeedItem(NeedSupply ns)
	{
	}

	public String toXML()
	{
		String returnStr="";
		returnStr += "<Distributor>";
		returnStr += "<CompanyInfo>";
		returnStr += "<CompanyName>"+name+"</CompanyName>";
		returnStr += "<CompanyAddress>"+address+"</CompanyAddress>";
		returnStr += "<CompanyContact>"+contact+"</CompanyContact>";
		returnStr += "</CompanyInfo>";

		returnStr += "<ItemsSold>";
		for (int i = 0; i < sellItems.size(); i++)
		returnStr += ((SellSupply)sellItems.elementAt(i)).toXML();
		returnStr += "</ItemsSold>";

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
		for (int i = 0; i < sellItems.size(); i++)
		returnStr += ((SellSupply)sellItems.elementAt(i)).toString();
		returnStr += "\n";

		return returnStr;
	}
}
