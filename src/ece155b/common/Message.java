
package ece155b.common;

public class Message {
    public String type;
    public String from;
    public String content;
    public MessageParser msgParser;
    
    public Message(String xml)
    {
    	msgParser = new MessageParser(xml, this);
    }
    
    public Message()
    {
        
    }
    
    public String toXML()
    {
        String xml = null;
        xml = "<Message>";
            xml += "<MessageType>"+type+"</MessageType>";
            xml += "<MessageFrom>"+from+"</MessageFrom>";
            xml += "<MessageContent>"+content+"</MessageContent>";
        xml += "</Message>";
        
        return xml;
    }
    
    public String toString()
    {
        return "Message:\nType: "+type+
                "\nFrom: "+from+
                "\nContent: "+content;
    }
}
