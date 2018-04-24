package ece155b.data;

public class Supply
{
	public String ID;
	public String name;
	public String brand;
	public double price;

	public String toXML()
	{
		String returnstr="";
		returnstr += "<Supply>";
			returnstr += "<SupplyID>"+ID+"</SupplyID>";
		returnstr += "</Supply>";
		return returnstr;
	}

	public String toString()
	{
		String returnstr="";
		returnstr += "||| Supply\n";
			returnstr += "|||| SupplyID:"+ID+"\n";
		return returnstr;
	}
}