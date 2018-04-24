package ece155b.data;

public class SellSupply // item sold to customers
{
	public Supply supply;
	public int amountAvailable;

	public SellSupply(int amountAvai)
	{
		this.amountAvailable = amountAvai;
		
		
	}
	
	public String toXML()
	{
		String returnstr="";
		returnstr += "<SupplyAvailable>"+amountAvailable+"</SupplyAvailable>";
		return returnstr;
	}

	public String toString()
	{
		String returnstr="";
		return returnstr+"\n";
	}
}