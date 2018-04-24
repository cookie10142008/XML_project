package ece155b.data;

import java.util.Vector;

public class Supply // an item with id, name, price, brand
{
	public String ID;
	public String name;
	public String brand;
	public double price;
	
	//Vector <SellSupply> amountAvaiList;
	
	public Supply(){
		
	}
	
	public Supply(String id, String name, String brand, double price)
	{
		this.ID = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
		
		//System.out.println("track");
		
	}
	
	/*
	public String toXML()
	{
		
//		amountAvaiList = new Vector<SellSupply>();
//		SellSupply amountAvai1 = new SellSupply(20);
//		SellSupply amountAvai2 = new SellSupply(50);
//		amountAvaiList.add(amountAvai1);
//		amountAvaiList.add(amountAvai2);
		
		

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
		
		returnstr += "</Supply>";
		return returnstr;
	}*/

	public String toString()
	{
		String returnstr="";
		returnstr += "||| Supply\n";
			returnstr += "|||| SupplyID:"+ID+"\n";
		return returnstr;
	}
}