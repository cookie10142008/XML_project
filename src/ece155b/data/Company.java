package ece155b.data;

public class Company
{
	public String name;
	public String address;
	public String contact;
	
	public Company(){
		System.out.println("succeed to transform data to company");
	}
	
	public Company(String name, String address, String contact){
        this.name = name;
        this.address = address;
        this.contact = contact;
    }
	// return company name
	public String getName() {
        return name;
    }
	
	public String getAddress() {
        return address;
    }
	
	public String getContact() {
        return contact;
    }
	// set company name
	public void setName(String name) {
        this.name = name;
    }
	
	public void setAddress(String address) {
        this.address = address;
    }
	
	public void setContact(String contact) {
        this.contact = contact;
    }
	
	
	
	
	
	
	
	
	
	
}