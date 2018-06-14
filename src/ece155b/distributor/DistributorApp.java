
package ece155b.distributor;

import ece155b.common.Common;
import ece155b.common.Message;
import ece155b.distributor.comm.ConnHandler;
import ece155b.distributor.data.ProviderContact;
import ece155b.distributor.data.Distributor;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;

public class DistributorApp extends JFrame {
    public Distributor distributor;
    private ConnHandler handler;
    private String distributor_Name;
    JTextArea texta;
    boolean connect = false;
    
    public static void main(String [] args) {
        new DistributorApp("test");
    	
    }
    
    public DistributorApp(String distName) {
    	distributor_Name = distName;
    	handler = new ConnHandler(this);// pass DistributorApp object
    	
    	
    	GUI();
        
    	
    	// choose which provider to connect
        // you will read this information from XML file
    	
//        ProviderContact contact = new ProviderContact();
//        contact.URL = "localhost";
//        contact.PORT = 5944;
//        contact.Name = "Yung-Ting Chuang"; //change to your name...
//        handler.connectToProvider(contact);
        
        //GUI();
    }
    
    
    public void GUI() {
    	
        /* GUI part of the application */
        texta = new JTextArea();
        texta.setFont(new Font("Monospaced", Font.BOLD, 22));
        JScrollPane scroll = new JScrollPane(texta);
        texta.setLineWrap(true);
        texta.setWrapStyleWord(true);
        texta.setText("port:");
        
        
        JButton testme = new JButton("Send message");
        testme.setFont(new Font("新細明體", Font.BOLD, 23));
        testme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	if(!connect)
            	{
	            	ProviderContact contact = new ProviderContact();
	            	contact.URL = "localhost";
	            	//contact.PORT = 5944;
	            	try {
	            		contact.PORT = Integer.parseInt(texta.getText().substring(5));
	            		contact.Name = "Yung-Ting Chuang"; //change to your name...
		            	handler.connectToProvider(contact);
		            	connect = true;
	            		
	            	} catch(NumberFormatException e) {
	            		JOptionPane.showMessageDialog(new JFrame("error"),"input error with non-integer");
	            		
	            	}
	            		
            	}
            	
                Message m = new Message();
                m.type = Common.BROADCAST;
                //m.type = Common.TERMINATE;
                m.content = "Test broadcast from Distributor";
                //m.from = "The Distributor A";
                m.from = distributor_Name;
                
                handler.broadcast(m);
            }
        });
        
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(testme, BorderLayout.SOUTH);
        
        setTitle("Distributor Application: " + distributor_Name);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void append(String str) {
        texta.append("\n"+str);
    }
}
