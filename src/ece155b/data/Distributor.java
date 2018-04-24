package ece155b.data;

import java.util.Vector;

public class Distributor extends Company
{
	
	Vector <Supply> sellItems;	// Vector of sell items
	Vector needItems;	// Vector of items needed
	
	
	
	public Distributor()
	{
		sellItems = new Vector<Supply>();
		needItems = new Vector ();
		
		Supply item1 = new Supply("12","cookie","cosmed",100);
		Supply item2 = new Supply("11","chocolate","cosmed",20);
		sellItems.add(item1);
		sellItems.add(item2);
		
		
		
	}

	public void addSellItem(SellSupply ss)
	{
		Supply item1 = new Supply("12","cookie","cosmed",100);
		
		sellItems.add(item1);
		
		
		
	}

	public void addNeedItem(NeedSupply ns)
	{
		
		
		
		
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
		
//		for (int i = 0; i < sellItems.size(); i++)
//		returnStr += ((SellSupply)sellItems.elementAt(i)).toXML();
		
		for (int i = 0; i < sellItems.size(); i++) {
			returnStr += (sellItems.get(i)).toXML();
		    //returnStr += amountAvaiList.get(i).toXML();
		}
		
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
		
//		for (int i = 0; i < sellItems.size(); i++) {
//			returnStr += ((SellSupply)sellItems.elementAt(i)).toString();
//		}
		
		returnStr += "\n";

		return returnStr;
	}
}
