package ece155b.provider.comm;

import ece155b.common.Message;
import ece155b.provider.data.Distributor;
import java.io.*;
import java.net.*;

class ConnListener extends Thread{
    public BufferedWriter bwrite;
    public BufferedReader bread;
    private ConnHandler PARENT_handler;
    
    public Distributor distributor; // Once patient is authenticated, set this value
    
    ConnListener(ConnHandler p, Socket socket) {
        PARENT_handler 	= p;
        try 
        {            
            bwrite = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bread = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            start();
        } catch (Exception ex) {
            System.out.println("Error with socket creation!");
        }
    }
    
    public void run() {
        try {
            while(true) {
                System.out.println("waiting for distributor message");
                String xml 	= (String) bread.readLine(); // read msg.toXML() from distributor
                
                if(xml == null) break;
                if(! PARENT_handler.processMessage(xml, this) )
                	break;
                
            }
        } catch(Exception e) {
            // This may happen because of the fact that client
            // application is closed.
            System.out.println("Error reading message!");
        }
        System.out.println("Socket closed!");
        PARENT_handler.server.append("Socket closed!");
        
        PARENT_handler.removeUser(this);
    }
    
    public void sendMessage(Message msg) {
        try {
            System.out.println("\nSending "+msg);
            bwrite.write(msg.toXML()); // write into OutputStream and send to client's getInputStream()
            bwrite.newLine();
            bwrite.flush();
        } catch (Exception ex) {
            System.out.println("Error sending message");
        }
    }
    
    
}