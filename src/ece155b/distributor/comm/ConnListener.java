package ece155b.distributor.comm;

import ece155b.common.Message;
import ece155b.provider.data.Distributor;
import ece155b.distributor.data.ProviderContact;
import java.io.*;
import java.net.*;

class ConnListener extends Thread{
    public BufferedWriter bwrite;
    public BufferedReader bread;
    private ConnHandler PARENT_Handler;
    private ProviderContact contact;
    
    public Distributor distributor; // Once distributor is authenticated, set this value
    
    ConnListener(ConnHandler p, Socket socket, ProviderContact pro) {
        PARENT_Handler 	= p;
        contact = pro;
        try 
        {            
            bwrite = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // write to provider
            bread = new BufferedReader(new InputStreamReader(socket.getInputStream())); // read from provider
            start(); // execute run()
        } catch (Exception ex) {
            System.out.println("Error with distributor's socket creation!");
        }
    }
    
    public void run() {
        try {
            while(true) {
                System.out.println("waiting");
                PARENT_Handler.distApp.append("waiting");
                
                String xml 	= (String) bread.readLine();
                if(xml == null) break;
                PARENT_Handler.processMessage(xml, this); // deal with message type
            }
        } catch(Exception e) {
            // This may happen because of the fact that client
            // application is closed.
            System.out.println("Error reading message from provider!");
            PARENT_Handler.distApp.append("Error reading message from provider!");
            
        }
        PARENT_Handler.removeProConn(this);
        
        System.out.println("Socket closed!");
        PARENT_Handler.distApp.append("Socket closed!");

    }
    
    public void sendMessage(Message msg) {
        try {
            System.out.println("\nSending to "+contact.Name+" \n"+msg);
            PARENT_Handler.distApp.append("Sending to "+contact.Name+" \n"+msg);
            
            bwrite.write(msg.toXML());
            bwrite.newLine();
            bwrite.flush();
        } catch (Exception ex) {
            System.out.println("Error sending message");
        }
    }
    
    
}