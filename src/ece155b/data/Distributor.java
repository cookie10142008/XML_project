package ece155b.data;

import java.util.Vector;

public class Distributor extends Company
{
	Vector sellitems;	// Vector of sell items
	Vector needitems;	// Vector of items needed

	public Distributor()
	{
		sellitems = new Vector ();
		needitems = new Vector ();
	}

	public void addSellItem(SellSupply ss)
	{
	}

	public void addNeedItem(NeedSupply ns)
	{
	}

	public String toXML()
	{
		String returnstr="";
		returnstr += "<Distributor>";
			returnstr += "<CompanyInfo>";
				returnstr += "<CompanyName>"+name+"</CompanyName>";
				returnstr += "<CompanyAddress>"+address+"</CompanyAddress>";
				returnstr += "<CompanyContact>"+contact+"</CompanyContact>";
			returnstr += "</CompanyInfo>";

			returnstr += "<ItemsSold>";
				for (int i = 0; i<sellitems.size(); i++)
				returnstr += ((SellSupply)sellitems.elementAt(i)).toXML();
			returnstr += "</ItemsSold>";

		returnstr += "</Distributor>";
		return returnstr;
	}

	public String toString()
	{
		String returnstr="";
			returnstr += "| CompanyInfo\n";
				returnstr += "|| CompanyName: "+name+"\n";
				returnstr += "|| CompanyAddress: "+address+"\n";
				returnstr += "|| CompanyContact: "+contact+"\n";
			returnstr += "\n";

			returnstr += "| ItemsSold\n";
				for (int i = 0; i<sellitems.size(); i++)
				returnstr += ((SellSupply)sellitems.elementAt(i)).toString();
			returnstr += "\n";

		return returnstr;
	}
}
