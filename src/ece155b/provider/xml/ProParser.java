package ece155b.provider.xml;

import ece155b.provider.data.Purchase;
import ece155b.provider.data.Provider;
import ece155b.distributor.xml.XMLParser;
import ece155b.provider.data.Distributor;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ProParser extends DefaultHandler
{
    private StringBuffer accumulator = new StringBuffer();
    
    public Provider provider;
    private Distributor distributor;
    private Purchase purchase;
    //新增ProParser() & readXmlFile(String fileurl)
    public ProParser(){
    	provider = new Provider();
    }
    
    public void readXmlFile(String fileurl) throws SAXException, IOException, ParserConfigurationException
	  {
	  		XMLParser handler = new XMLParser();
			try
		    {
		    	SAXParserFactory factory = SAXParserFactory.newInstance( );
				SAXParser parser = factory.newSAXParser();
				parser.parse(fileurl,handler);
				
				
		    }
		    catch (Exception ex)
		    {
		    	ex.printStackTrace();
		    }
		    //return myparser.distributor;
	  }
      //新增結束  
    public ProParser(File file) 
    {
        
    }
    
    public void characters(char[] buffer, int start, int length)
    {
        accumulator.append(buffer, start, length);
    }
    
    public void startElement(String uri,String lname, String name, Attributes attributes)
    {
        
    }
    
    public void endElement(String uri,String lname,String name)
    {
        String value = accumulator.toString();
        
    }
    
}
