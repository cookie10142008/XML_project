
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
    
    /** Creates a new instance of MessageParser */
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
        if(name.equals("MessageContent"))
            message.content = value;
        
    }
}
