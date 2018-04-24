package ece155b.test;


import ece155b.data.Distributor;
import ece155b.data.Supply;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.xml.XMLParser;
import java.io.*;
import javax.xml.parsers.*;

public class DemoXML {
    
//    public static void main(String [] args) {
//       // new DemoXML(args);
//    	new DemoXML(System.getProperty("user.dir") + File.separator + "test.xml"); // the same directory with src (under java project)
//    }
    
    public DemoXML(String  args) {
    	String fileUrl = "hello.xml";

    	Distributor dist = new Distributor();
    	dist.name = "The company";
    	dist.address = "Address";
    	dist.contact = "Contact me at 9 night a.m.";

    	//Supply supply = new Supply();
    	//supply.ID 	= "ID";

    	System.out.println (dist);
    	toXmlFile (dist,fileUrl);
    	System.out.println (readXmlFile(fileUrl));
    }


    
    public void toXmlFile(Distributor dist, String url)
    {
    	try
	    {
	    	File xmlfile = new File(url);
    		BufferedWriter br = new BufferedWriter(new FileWriter(xmlfile));
    		br.write("<?xml version='1.0' ?>");
    		br.write(dist.toXML());
    		br.close();
	    }
	    catch (Exception ex)
	    {
	    	System.out.println ("Exception:"+ex);
	    }
    }

    public Distributor readXmlFile(String url)
    {
    	XMLParser myparser = new XMLParser();
		try
	    {
	    	SAXParserFactory factory = SAXParserFactory.newInstance(  );
			SAXParser parser = factory.newSAXParser();
			parser.parse(url,myparser);
	    }
	    catch (Exception ex)
	    {
	    	ex.printStackTrace();
	    }
	    return myparser.distributor;
    }

    /**/
}