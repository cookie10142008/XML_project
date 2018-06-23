package ece155b.provider.comm;

import ece155b.common.Common;
import ece155b.common.Message;
import ece155b.common.SupplyList;
import ece155b.distributor.data.Distributor;
import ece155b.distributor.data.NeedSupply;
import ece155b.distributor.data.SellSupply;
import ece155b.provider.ProviderApp;
import ece155b.provider.data.Provider;
import ece155b.provider.xml.ProParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.util.Vector;

public class ConnHandler extends Thread {
    private ServerSocket servers;
    Vector<ConnListener> handles;   // vector that keeps track of connected clients
    
    ProviderApp server;   // reference to main application 
                        // ** you need to access doctor information
    
    public ConnHandler(ProviderApp proApp, int PortNo) {
        try {
            server = proApp;
            servers     = new ServerSocket(PortNo);
            handles = new Vector <ConnListener>();
            
            start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void run() {
        try {
            while(true) {
                System.out.println("Waiting connections...");
                Socket socket = servers.accept();
                System.out.println("Got one connection :) ");
                addUser(socket);
                System.out.println("Processed..");
            }
        } catch (Exception ex) {}
    }
    
    // add new client user
    /**
     * Here you might assign unique session id to client listener
     */
    public void addUser(Socket socket) {
        handles.addElement(new ConnListener(this, socket)); // ConnListener: process socket inputStream, ouputStream
    }
    
    /*
     *  You need a find criteria. Might be patient name,
     *  something unique to that patient (like ssn). Or you might assign
     *  session_ids to each connecting client and keep this in
     *  ClientListener object. Think that session id as your
     *  search criteria
     **/
    public ConnListener findUser(String distributorName) {
        return null;
    }
    
    // Find user and remove..
    /**
     * When client disconnects, or closes the connection
     *  the client listener will catch an exception.
     *  You can use that and inform the connHandler to
     *  remove respective client listener
     */
    public void removeUser(ConnListener client) {
        handles.remove(client);
    }
    
    /**
     * Send message to all clients,
     *  you might also write function to send specific client
     *  just go over the vector, find matching ClientListener object
     *
     *  You might need to add another data field to ClientListener
     *  so that you will distingush between different clients.
     */
    public void broadcast(Message m) {
        System.out.println("BCAST #"+handles.size());
        
        if(handles.size() == 0)
            System.out.println("No peer to broadcast");
        else for (int i = 0; i<handles.size(); i++) {
            ConnListener cl = (ConnListener) handles.elementAt(i);
            cl.sendMessage(m);
        }
    }
    /**
     * YOu will need to modify this method, include all the
     * cases you might have as message type.
     *
     * This method is synchronized for the following reasons:
     * 1. Two clients might send message at the same time, we want to process
     * one of the client and then process second. Otherwise, this might cause
     * data inconsistencies
     *
     * When you make a method synchronized, only one execution of the
     * method exists, since all client handlers calls this method to
     * process received messages, we are sure, two message calls will
     * not be executed at the same time. One of them will be picked by the OS,
     * and other will be pending until executing thread finishes its job.
     *
     * This is important to understand, otherwise, you might end up
     * with inconsistent data.
     *
     * Wow.. so long :) take it serious.
     * /**/		// only one thread can access synchronized method
    public synchronized boolean processMessage(String xml, ConnListener listener) {
        boolean authenticate = false;
    	// msg: distributor's message
        Message msg = new Message(xml); // transform xml into message object
        System.out.println("process:" + xml);
        /*
            You will need to define message types,
            you might consider to have a Common.java class,
            which you keep all the constants that will be used
            by other java classes.
         
            Most time I do that way, and it is really helpful.
         
            For example: Message type will be used by both
            Doctor and Patient. Why not keep them in Common.java file.
         
            First parse the received message, which is in XML format.
            Having message properties, like type, etc.
         */
            if(msg.type.equals(Common.BROADCAST)) //receive broadcast message from dist.
            {
                server.append(msg.toString());
                return true;// continue to receive msg from dist. 
            }
            else if(msg.type.equals(Common.AUTHENTICATE_DISTRIBUTOR)) //receive authenticate message from dist.
            {
                // Access distributors information, and see if distributor exists
            	server.append(msg.toString());
            	
            	for(String distname: ProParser.distributorList) {
            		if(distname.equals(msg.from)) {
            			authenticate = true;
            			break;
            		}
            	}
            	
            	if(authenticate) {
            		server.append(msg.from + " found!");
            		
            		Message reply_msg = new Message();
        			reply_msg.type = "Authenticate_Reply";
        			reply_msg.from = server.company_Name;
        			reply_msg.content = "Authorize! You can start to purchase!";
        			
        			listener.sendMessage(reply_msg);
        			
        			return true;// continue to receive msg from dist. 
            	}else {
            		server.append("Not found in distributors list!");
            		
        			Message reply_msg = new Message();
        			reply_msg.type = "Authenticate_Reply";
        			reply_msg.from = server.company_Name;
        			reply_msg.content = "Sorry, you are not authorized! Socket closed!";
        			
        			listener.sendMessage(reply_msg);
            		
        			return false;// stop to receive msg from dist. 
            	}
            		
            }
            else if(msg.type.equals(Common.REQUEST_SUPPLY_LIST))
            {
            	server.append(msg.toString());
            	System.out.println("Prov receive: "+msg.content);
            	// get supply list item name & amount
            	String temp = msg.content.substring(11); // del "supplylist:" in string
            	Vector <SupplyList> supplylistArray = new Vector<SupplyList>();
            	String supplylistReply = "supplylistReply: ";
            	boolean found = false;
            	
            	for(String item: temp.split(",")) { // supplylist : an item w/ name and amount
            		SupplyList supplylist = new SupplyList();
            		
            		String value[] = item.split(":");
            		supplylist.name = value[0];
            		supplylist.amount = value[1];
            		supplylistArray.add(supplylist);
            	}
            	// compare with stock in provider
            	
            	for(SupplyList needItem: supplylistArray) {
            		supplylistReply += needItem.name;
            		supplylistReply += ":";
            		//System.out.println(sellItem.name +":"+sellItem.amountAvailable);
            		
            		for(SellSupply sellItem: Provider.sellItems) {
            			if(sellItem.name.equals(needItem.name)) {
            				supplylistReply += "Found, ";
            				if(sellItem.amountAvailable >= Integer.parseInt(needItem.amount)) {
            					supplylistReply += "Enough amount!";
            					
            				}else {
            					supplylistReply += "Not enough amount!";

            				}
            				found = true;
            				break;
            			}
            			
            		}
            		if(!found){
        				supplylistReply += "Not found!";
        			}
            		
            	}
            	
            	Message reply_msg = new Message();
    			reply_msg.type = Common.REQUEST_SUPPLY_LIST_REPLY;
    			reply_msg.from = server.company_Name;
    			reply_msg.content = supplylistReply;
    			
    			listener.sendMessage(reply_msg);
            	
            	return true;// continue to receive msg from dist. 
            }
            else if(msg.type.equals(Common.REQUEST_PURCHASE))
            {
            	server.append(msg.toString());
            	
            	System.out.println("Prov receive: "+msg.content);
            	// get supply list item name & amount
            	String temp = msg.content.substring(11); // del "supplylist:" in string
            	Vector <SupplyList> supplylistArray = new Vector<SupplyList>();
            	String supplylistReply = "supplylistReply: ";
            	boolean found = false;
            	
            	for(String item: temp.split(",")) { // supplylist : an item w/ name and amount
            		SupplyList supplylist = new SupplyList();
            		
            		String value[] = item.split(":");
            		supplylist.name = value[0];
            		supplylist.amount = value[1];
            		supplylistArray.add(supplylist);
            	}
            	// compare with stock in provider
            	
            	for(SupplyList needItem: supplylistArray) {
            		supplylistReply += needItem.name;
            		supplylistReply += ":";
            		//System.out.println(sellItem.name +":"+sellItem.amountAvailable);
            		
            		for(SellSupply sellItem: Provider.sellItems) {
            			if(sellItem.name.equals(needItem.name)) {
            				supplylistReply += "Found, ";
            				if(sellItem.amountAvailable >= Integer.parseInt(needItem.amount)) {
            					supplylistReply += "Enough amount!";
            					
            					//add amount into distributor xml
            					String fileUrl = "Distributor.xml"; // set xml file name
            					//toXmlFile();
            					
            					
            					
            				}else {
            					supplylistReply += "Not enough amount!";

            				}
            				found = true;
            				break;
            			}
            			
            		}
            		if(!found){
        				supplylistReply += "Not found!";
        			}
            		
            	}
            	
            	for(NeedSupply needitem:  Distributor.needItems){
            		System.out.println(needitem.name);
            		
            		
            		
            	}
            		
            	
            	Message reply_msg = new Message();
    			reply_msg.type = Common.REQUEST_PURCHASE_REPLY;
    			reply_msg.from = server.company_Name;
    			reply_msg.content = "Succeed to purchase or not";
    			
    			listener.sendMessage(reply_msg);
            	return true;// continue to receive msg from dist. 
            }
            else if(msg.type.equals(Common.TERMINATE))
            {
            	server.append(msg.toString());
            	return false;// stop to receive msg from dist. 
            }
            else {
                System.out.println("Provider: Unknown message type from distributor");
                return true;// continue to receive msg from dist. 
            }
            
    }
    
    public void toXmlFile(Distributor dist, String fileurl)
    {
    	try
	    {
	    	File xmlfile = new File(fileurl);
    		BufferedWriter br = new BufferedWriter(new FileWriter(xmlfile));
    		br.write("<?xml version='1.0' ?>"); // start to write XML into file
    		br.write(dist.toXML());
    		br.close();
	    }
	    catch (Exception ex)
	    {
	    	System.out.println ("Exception:"+ex);
	    }
    }
    
}