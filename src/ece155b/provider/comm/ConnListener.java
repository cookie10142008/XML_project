package ece155b.provider.comm;

import ece155b.common.Message;
import ece155b.provider.data.Distributor;
import java.io.*;
import java.net.*;

class ConnListener extends Thread{
    public BufferedWriter bwrite;
    public BufferedReader bread;
    private ConnHandler PARENT;
    
    public Distributor distributor; // Once patient is authenticated, set this value
    
    ConnListener(ConnHandler p, Socket socket) {
        PARENT 	= p;
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
                System.out.println("waiting");
                String xml 	= (String) bread.readLine();
                if(xml == null) break;
                PARENT.processMessage(xml, this);
            }
        } catch(Exception e) {
            // This may happen because of the fact that client
            // application is closed.
            System.out.println("Error reading message!");
        }
        System.out.println("Socket closed!");
        PARENT.removeUser(this);
    }
    
    public void sendMessage(Message msg) {
        try {
            System.out.println("Sending "+msg);
            bwrite.write(msg.toXML());
            bwrite.newLine();
            bwrite.flush();
        } catch (Exception ex) {
            System.out.println("Error sending message");
        }
    }
    
    
}