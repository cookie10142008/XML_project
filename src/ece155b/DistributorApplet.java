/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ece155b;
import java.awt.*;
import javax.swing.*;
import ece155b.data.Distributor;
import ece155b.data.Supply;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.xml.XMLParser;
import javax.xml.parsers.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// share with ­Û
/**
 *
 * @author Administrator
 */
public class DistributorApplet extends JApplet implements ActionListener{

  /* You will definitely have more functions below,
   * such as reading/writing XML files, GUI parts,
   * mouse/action event listeners...
   */
    Distributor distributor;
    String fileurl = System.getProperty("user.dir") + File.separator + "test.xml";  //XML file to read/write to

  public void init()
  {
    Container content = getContentPane();
    content.setBackground(Color.white);
    content.setLayout(new FlowLayout());
    //content.add(new JLabel("Hello World"));

    try {       //try to read the xml file if present
        //readXmlFile(fileurl);
        content.add(new JLabel("Hello!"));
    } catch(Exception e) { //if xml file does not exist create new blank doctor object
        content.add(new JLabel("Hello!"));
    }
    makeGUI();
  }

    public void destroy() { 
    //destructor that writes the doctor object to file before closing
        System.out.println("...closing");
        //toXmlFile(...);
    }


 public void makeGUI(){}

 class SupplyTab extends JTabbedPane implements ActionListener{
    public SupplyTab(SellSupply viewsellsupply, int index){}
    public void actionPerformed(ActionEvent s){}
 }

 public void actionPerformed(ActionEvent e){}
}
