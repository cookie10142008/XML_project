package ece155b.provider;

import ece155b.common.Common;
import ece155b.common.Message;
import ece155b.distributor.data.ProviderContact;
import ece155b.provider.comm.ConnHandler;
import ece155b.provider.data.Provider;
import ece155b.provider.xml.ProParser;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class ProviderApp extends JFrame {
    
    public Provider provider;
    
    private int PortNo;
    private ConnHandler connhand;
    JTextArea texta;
    public String company_Name;
  
    public static void main(String [] args) {
        new ProviderApp("enter from main");
    }
    
    public ProviderApp(String companyName) {
    	company_Name = companyName;
    	
    	// no use yet
    	try {
            provider = new ProParser(new File("distributors.xml")).provider; // new provider object in ProParser.java
        } catch (Exception ex){
            System.out.println("Error reading file");
        }
        
        // set random port for provider      
        PortNo = 5000 + (int)(Math.random()*1001);
        connhand = new ConnHandler(this, PortNo);
        
        ProviderContact provContact = new ProviderContact();
        provContact.Name = company_Name;
        provContact.PORT = PortNo;
        provContact.URL = "localhost";
        ProviderContact.avai_provider.add(provContact);
        
        for(ProviderContact pc: ProviderContact.avai_provider)
        {
        	System.out.println(pc);
        	
        }
        
        GUI();
    }

    
    public void GUI() {
        getContentPane().setLayout(new BorderLayout());
        
        texta = new JTextArea();
        texta.setFont(new Font("Monospaced", Font.BOLD, 22));
        JScrollPane scroll = new JScrollPane(texta);
        texta.setLineWrap(true);
        texta.setWrapStyleWord(true);
        
        String showPort = "the port number is:" + PortNo;
        texta.setText(showPort);
        
        JButton testme = new JButton("Broadcast message");
        testme.setFont(new Font("新細明體", Font.BOLD, 23));
        testme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {        
            	                
                Message msg = new Message();
                msg.type = Common.BROADCAST;
                msg.from = company_Name; 
                msg.content = "Test broadcast from provider";
                
                connhand.broadcast(msg);
            }
        });
        
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(testme, BorderLayout.SOUTH);
        
//        String[] messageType = { 
//        		"BROADCAST",
//        		"AUTHENTICATE_DISTRIBUTOR", 
//        		"AUTHENTICATE_DISTRIBUTOR_REPLY", 
//        		"REQUEST_SUPPLY_LIST", 
//        		"REQUEST_SUPPLY_LIST_REPLY",
//        		"REQUEST_PURCHASE",
//        		"REQUEST_PURCHASE_REPLY",
//        		"TERMINATE"
//        		};
        
        setTitle("Provider Application: "+ company_Name);
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void append(String str) {
        texta.append("\n\n"+str);
    }
}