package ece155b.distributor.comm;

import ece155b.common.Common;
import ece155b.common.Message;
import ece155b.distributor.DistributorApp;
import ece155b.distributor.DistributorApplet;
import ece155b.distributor.data.ProviderContact;

import java.net.*;
import java.util.Vector;

public class ConnHandler {
    Vector<ConnListener> providers;   // vector that keeps track of connected clients
    DistributorApp pApp;
    
    public ConnHandler(DistributorApp distributorApp) {
        providers = new Vector <ConnListener>();
        pApp = distributorApp;
    }
    
    public void connectToProvider(ProviderContact pro) {
        try {
            Socket socket = new Socket(pro.URL, pro.PORT);
            providers.addElement(new ConnListener(this, socket, pro));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void removeProConn(ConnListener cl) {
        providers.remove(cl);
    }
    
    public void broadcast(Message m) {
        System.out.println("BCAST #"+providers.size());
        
        if(providers.size() == 0)
            System.out.println("No peer to broadcast");
        else for (int i = 0; i<providers.size(); i++) {
            ConnListener cl = (ConnListener) providers.elementAt(i);
            cl.sendMessage(m);
        }
    }
    
    protected synchronized void processMessage(String xml, ConnListener listener) {
        Message msg = new Message(xml);
        
        if(msg.type.equals(Common.BROADCAST)) {
            pApp.append(msg.toString());
        } else if(msg.type.equals(Common.AUTHENTICATE_DISTRIBUTOR_REPLY)) {
            // Patient is logged in, can request time
        } else if(msg.type.equals(Common.REQUEST_SUPPLY_LIST_REPLY)) {
            // Action to take..
        } else if(msg.type.equals(Common.REQUEST_PURCHASE_REPLY)) {
            // Action to take..
        } else
            System.out.println("Unknown message type");
    }
}