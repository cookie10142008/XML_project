package ece155b.provider.xml;

import ece155b.provider.data.Purchase;
import ece155b.provider.data.Provider;
import ece155b.provider.data.Distributor;

import java.io.File;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ProParser extends DefaultHandler
{
    private StringBuffer accumulator = new StringBuffer();
    
    public Provider provider;
    private Distributor distributor;
    private Purchase purchase;
        
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
