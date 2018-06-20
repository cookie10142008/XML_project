
package ece155b.common;

import java.io.StringReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

public class MessageParser extends DefaultHandler 
{
    public Message message;
    private StringBuffer accumulator = new StringBuffer();
    boolean supplyListRead = false;
    String supplyList = null;
    
    /** Creates a new instance of MessageParser */
    // extract info from xml into message(Message object contain type, from, content)
    public MessageParser(String xml, Message msg) { 
        try {
            message = msg;
            SAXParserFactory factory = SAXParserFactory.newInstance(  );
            SAXParser parser = factory.newSAXParser();
            parser.parse(new InputSource(new StringReader(xml)), this);
        } catch (Exception ex) {
            System.out.println("Parsing error: "+ex);
        }
    }
    
    public void characters(char[] buffer, int start, int length)
    {
        accumulator.append(buffer, start, length);
    }
    
    public void startElement(String uri,String lname, String name, Attributes attributes)
    {
        accumulator.setLength(0);
    }
    
    public void endElement(String uri,String lname,String name)
    {
        
    	String value = accumulator.toString();
        if(name.equals("MessageType"))
            message.type = value;
        else if(name.equals("MessageFrom"))
            message.from = value;
        else if(name.equals("supplyName"))
        	supplyList += value;
        else if(name.equals("supplyAmount")) {
        	supplyList += ":";
        	supplyList += value;
        	supplyList += ",";
        }
        
        if(name.equals("SupplyList")) {
        	supplyListRead = true;
        	//supply 
        	
        }
        else if(name.equals("MessageContent")) {
        	if(supplyListRead) {
        		
        		
        		message.content = supplyList;
        		
        	}
        	else
        		message.content = value;
        }
        else if(name.equals("supplyName")) {
        	//Supplylist.name = value; // build supplylist class?
        	
        }
        
    }
    
    public String toSupplyList() {
    	
    	String list = null;
    	
    	
    	
    	
    	
    	return list;
    }
    
    
    
    
}
