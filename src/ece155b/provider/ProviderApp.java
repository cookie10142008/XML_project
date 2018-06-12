package ece155b.provider;

import ece155b.common.Common;
import ece155b.common.Message;
import ece155b.provider.comm.ConnHandler;
import ece155b.provider.data.Provider;
import ece155b.provider.xml.ProParser;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class ProviderApp extends JFrame {
    
    public Provider provider;
    private int PortNo;
    
    private ConnHandler connh;
    
    public static void main(String [] args) {
        new ProviderApp();
    }
    
    public ProviderApp() {
        try {
            provider = new ProParser(new File("distributors.xml")).provider;
        } catch (Exception ex){
            System.out.println("Error reading file");
        }
        
        int PortNo  = 1111;
        connh 	= new ConnHandler(this, PortNo);
        GUI();
    }
    
    JTextArea texta;
    
    public void GUI() {
        setLayout(new BorderLayout());
        
        texta = new JTextArea();
        JScrollPane scroll = new JScrollPane(texta);
        texta.setLineWrap(true);
        texta.setWrapStyleWord(true);
        
        JButton testme = new JButton("Broadcast message");
        testme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                
                Message msg = new Message();
                msg.type = Common.BROADCAST;
                msg.from = "The provider";
                
                msg.content = "Test broadcast from provider";
                
                connh.broadcast(msg);
            }
        });
        
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(testme, BorderLayout.SOUTH);
        
        setTitle("Provider Application");
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
    }
    
    public void append(String str) {
        texta.append("\n"+str);
    }
}