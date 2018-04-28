package ece155b.data;

public class NeedSupply extends Supply// item needed from providers (provider give us)
{
	public Supply supply;
	public int amountNeeded;
	//test
	public NeedSupply(){
		
	}
	
	public NeedSupply(String id, String name, String brand, double price, int amountNeeded)
	{
		this.ID = id;
		this.name = name;
		this.brand = brand; 
		this.price = price;
		this.amountNeeded = amountNeeded;
		
	}
	
	public String toXML()
	{
		String returnstr="";
		
		returnstr += "<NeedSupply>";
		returnstr += "<Supply>";
		returnstr += "<SupplyID>"+ID+"</SupplyID>";
		returnstr += "<SupplyName>"+name+"</SupplyName>";
		returnstr += "<SupplyBrand>"+brand+"</SupplyBrand>";
		returnstr += "<SupplyPrice>"+price+"</SupplyPrice>";
		returnstr += "<SupplyNeeded>"+amountNeeded+"</SupplyNeeded>";
		returnstr += "</Supply>";
		returnstr += "</NeedSupply>";
		
		return returnstr;
	}

	public String toString()
	{
		String returnstr="";
		return returnstr;
	}
}