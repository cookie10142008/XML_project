package ece155b.data;

public class SellSupply extends Supply// item sold to customers
{
	public Supply supply;
	public int amountAvailable;

	public SellSupply(){
		
	}
	
	public SellSupply(String id, String name, String brand, double price, int amountAvai)
	{
		this.ID = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.amountAvailable = amountAvai;
		
	}
	
	public String toXML()
	{
		String returnstr="";
		
		returnstr += "<Supply>";
		returnstr += "<SupplyID>"+ID+"</SupplyID>";
		returnstr += "<SupplyName>"+name+"</SupplyName>";
		returnstr += "<SupplyBrand>"+brand+"</SupplyBrand>";
		returnstr += "<SupplyPrice>"+price+"</SupplyPrice>";
		//returnstr += SellSupply.toXML();
//		for (int i = 0; i < amountAvaiList.size(); i++) {
//			returnstr += amountAvaiList.get(i).toXML();
//		    
//		}
		
		returnstr += "<SupplyAvailable>"+amountAvailable+"</SupplyAvailable>";
		returnstr += "</Supply>";
		
		
		
		return returnstr;
	}

	public String toString()
	{
		String returnstr="";
		return returnstr+"\n";
	}
}