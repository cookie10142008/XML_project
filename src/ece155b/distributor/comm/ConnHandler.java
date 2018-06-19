package ece155b.distributor.comm;

import ece155b.common.Common;
import ece155b.common.Message;
import ece155b.distributor.DistributorApp;
import ece155b.distributor.DistributorApplet;
import ece155b.distributor.data.ProviderContact;

import java.net.*;
import java.util.Vector;

public class ConnHandler {			  
    Vector<ConnListener> providers;   // vector that keeps track of connected "clients" -> providers?
    DistributorApp distApp;
    public ConnListener listener;
    
    
    public ConnHandler(DistributorApp distributorApp) {
        providers = new Vector <ConnListener>();
        distApp = distributorApp;
    }
    
    public void connectToProvider(ProviderContact pro) {
        try {
            Socket socket = new Socket(pro.URL, pro.PORT);
            listener = new ConnListener(this, socket, pro);
            
            providers.addElement(listener); //ConnListener: deal w/ getInputStream, getOutputStream
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void removeProConn(ConnListener cl) {
        providers.remove(cl);
    }
    
    public void broadcast(Message m) {
        System.out.println("BCAST #"+ providers.size());
        distApp.append("BCAST #"+ providers.size());
        
        if(providers.size() == 0) {
            System.out.println("No peer to broadcast");
        	distApp.append("BCAST #"+ providers.size());
        }
        else { 
        	for (int i = 0; i < providers.size(); i++) {
	            ConnListener cl = (ConnListener) providers.elementAt(i);
	            cl.sendMessage(m);
        	}
        	
        }
        
    }
    
    // process message from provider
    protected synchronized void processMessage(String xml, ConnListener listener) {
        Message msg = new Message(xml);
        
        if(msg.type.equals(Common.BROADCAST)) { // receive broadcast message from provider
            distApp.append(msg.toString());
        } else if(msg.type.equals(Common.AUTHENTICATE_DISTRIBUTOR_REPLY)) {
        	// distributor receive agreement of auth. and log in, time to request 
        	distApp.append(msg.toString());
        	
        	
        } else if(msg.type.equals(Common.REQUEST_SUPPLY_LIST_REPLY)) {
        	distApp.append(msg.toString());
        	
        	
        	
        	
        } else if(msg.type.equals(Common.REQUEST_PURCHASE_REPLY)) {
            // Action to take..
        } else
            System.out.println("Unknown message type from provider");
        	distApp.append("Unknown message type from provider");
    }
}