
package ece155b.common;

public class Message {
    public String type;
    public String from;
    public String content;
    
    public Message(String xml)
    {
        new MessageParser(xml, this);
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
        return "Message:\nType:\t"+type+
                "\nFrom:\t"+from+
                "\nContent:\t"+content;
    }
}
