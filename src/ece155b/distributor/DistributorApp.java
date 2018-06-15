
package ece155b.distributor;

import ece155b.common.Common;
import ece155b.common.Message;
import ece155b.distributor.comm.ConnHandler;
import ece155b.distributor.data.ProviderContact;
import ece155b.distributor.data.Distributor;

import java.awt.*;
import java.awt.event.*;


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
//        contact.PORT = port;
//        contact.Name = "Yung-Ting Chuang"; //change to your name...
//        handler.connectToProvider(contact);
    	
        //GUI();
    }

    public void GUI() {
    	
        /* GUI part of the application */
        texta = new JTextArea();
        texta.setFont(new Font("Monospaced", Font.BOLD, 22));
        JScrollPane scroll = new JScrollPane(texta);
        scroll.setBounds(0, 0, 578, 307);
        texta.setLineWrap(true);
        texta.setWrapStyleWord(true);
        texta.setText("port:");
        
        
        JButton testme = new JButton("Send message");
        testme.setBounds(0, 387, 578, 35);
        testme.setFont(new Font("新細明體", Font.BOLD, 22));
        testme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	if(!connect)
            	{
            		// choose which provider to connect
                    // you will read this information from XML file
	            	ProviderContact contact = new ProviderContact();
	            	contact.URL = "localhost";
	            	
	            	try {
	            		contact.PORT = Integer.parseInt(texta.getText().substring(5).trim());
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
        getContentPane().setLayout(null);
        getContentPane().add(scroll);
        getContentPane().add(testme);
        // message type
        String[] messageType = { 
        		"BROADCAST",
        		"AUTHENTICATE_DISTRIBUTOR", 
        		"REQUEST_SUPPLY_LIST", 
        		"REQUEST_PURCHASE",
        		"TERMINATE"
        		};
        JComboBox comboBox = new JComboBox(messageType);
        comboBox.setFont(new Font("新細明體", Font.BOLD, 22));
        comboBox.setBounds(63, 307, 515, 77);
        
        getContentPane().add(comboBox);
        
        JLabel lblNewLabel = new JLabel("Type:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 22));
        lblNewLabel.setBounds(0, 307, 64, 77);
        getContentPane().add(lblNewLabel);
        
        setTitle("Distributor Application: " + distributor_Name);
        setSize(600, 478);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void append(String str) {
        texta.append("\n"+str);
    }
}
