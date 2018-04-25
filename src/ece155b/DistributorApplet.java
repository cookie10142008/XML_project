/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ece155b;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ece155b.data.Company;
import ece155b.data.Distributor;
import ece155b.data.Supply;
import ece155b.test.DemoXML;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.xml.XMLParser;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

/**
 * @author 林瑋鴻、鍾昀倫
 */
public class DistributorApplet extends JApplet implements ActionListener{
	public DistributorApplet() {
	}

  /* You will definitely have more functions below,
   * such as reading/writing XML files, GUI parts,
   * mouse/action event listeners...
   */
    Distributor distributor;
    String fileurl = System.getProperty("user.dir") + File.separator + "test.xml";  //XML file to read/write to (under bin dir)
    Container content = getContentPane();
    
    public void init()
    {
    	setUIFont(new FontUIResource("微軟正黑體",Font.PLAIN,20));
    	setSize(1300,800); // set JApplet window size
	    content.setBackground(Color.LIGHT_GRAY);
	    content.setLayout(null);
	
	    try {       //try to read the xml file if present
	        
	        
	        
	    } catch(Exception e) { //if xml file does not exist create new blank doctor object
	        content.add(new JLabel("XML file not found"));
	        
	    }
    
    
	    makeGUI();
	    
	    
	     
    
    }
    // set component font
    private void setUIFont(FontUIResource fontUIResource) {
    	 Enumeration keys = UIManager.getDefaults().keys();
         while (keys.hasMoreElements()) {
             Object key = keys.nextElement();
             Object value = UIManager.get(key);
             if (value != null && value instanceof FontUIResource) {
                 UIManager.put(key, fontUIResource);
             }
         }
		
	}

	public void destroy() { 
    //destructor that writes the doctor object to file before closing
        System.out.println("...closing "+fileurl);
        
        String fileUrl = "Distributor.xml";
        Distributor dist = new Distributor();
    	dist.name = "The company success";
    	dist.address = "Address";
    	dist.contact = "Contact me at 9 night a.m.";
        System.out.println("123");
    	toXmlFile(dist,fileUrl);
    	
        //Company company = new Company();
    }


	public void makeGUI(){
		
		JTextField textField_companyName, textField_contactMe, textField_address;
		JTable table_1;
		
		
		
	     String [] headings= new String[] {"Item Type","Price","Available"}; //Item Sold
	     //customerTable的資料
	     Object[][] data = new Object[][] {
             {"Item X","50.5","300"},
             {"Item Y","42.2","200"}
             }; 
             
            JPanel customer_Panel = new JPanel();
            customer_Panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
     	    customer_Panel.setBounds(15, 229, 685, 450);
     	    //content.add(customer_Panel);
     	    customer_Panel.setLayout(null);
     	    
     	    JScrollPane customerScrollPane = new JScrollPane();
     	    customerScrollPane.setBounds(101, 53, 452, 285);
     	    customer_Panel.add(customerScrollPane);
     	    //建立table
     	     JTable customerTable = new JTable(data, headings);
     	     customerScrollPane.setViewportView(customerTable);
     	     customerTable.setCellSelectionEnabled(true);
     	     customerTable.setColumnSelectionAllowed(true);
     	     
     	     customerTable.setRowHeight(50); //設定列高度為50		
     	     
     	     JButton customerAddRowBtn = new JButton("Add a row");
     	     customerAddRowBtn.setBounds(114, 371, 140, 31);
     	     customer_Panel.add(customerAddRowBtn);
     	     
     	     JButton customerDelRowBtn = new JButton("Delete selected rows");
     	     customerDelRowBtn.addActionListener(new ActionListener() {
     	     	public void actionPerformed(ActionEvent arg0) {
     	     	}
     	     });
     	     customerDelRowBtn.setBounds(337, 371, 243, 31);
     	     customer_Panel.add(customerDelRowBtn);
     	     
     	     		
     	     		JLabel labelItemsSold = new JLabel("Items sold to customers");
     	     		labelItemsSold.setFont(new Font("新細明體", Font.BOLD, 25));
     	     		labelItemsSold.setBounds(15, 0, 336, 38);
     	     		customer_Panel.add(labelItemsSold);
     	     		
     	     		JPanel companyPanel = new JPanel();
     	     		companyPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
     	     		companyPanel.setBounds(341, 39, 974, 187);
     	     		//getContentPane().add(companyPanel);
     	     		companyPanel.setLayout(null);
     	     		
     	     		textField_companyName = new JTextField();
     	     		textField_companyName.setBounds(229, 55, 136, 29);
     	     		companyPanel.add(textField_companyName);
     	     		textField_companyName.setColumns(10);
     	     		
     	     		JLabel labelCompanyInfo = new JLabel("Company information");
     	     		labelCompanyInfo.setFont(new Font("新細明體", Font.BOLD, 25));
     	     		labelCompanyInfo.setBounds(15, 0, 289, 40);
     	     		companyPanel.add(labelCompanyInfo);
     	     		
     	     		JLabel labelCompanyName = new JLabel("Company name:");
     	     		labelCompanyName.setBounds(58, 58, 195, 23);
     	     		companyPanel.add(labelCompanyName);
     	     		
     	     		JLabel labelContactMe = new JLabel("Contact me:");
     	     		labelContactMe.setBounds(58, 102, 195, 23);
     	     		companyPanel.add(labelContactMe);
     	     		
     	     		textField_contactMe = new JTextField();
     	     		textField_contactMe.setColumns(10);
     	     		textField_contactMe.setBounds(229, 99, 136, 29);
     	     		companyPanel.add(textField_contactMe);
     	     		
     	     		JLabel labelAddress = new JLabel("Address:");
     	     		labelAddress.setBounds(447, 58, 85, 23);
     	     		companyPanel.add(labelAddress);
     	     		
     	     		textField_address = new JTextField();
     	     		textField_address.setColumns(10);
     	     		textField_address.setBounds(539, 55, 136, 29);
     	     		companyPanel.add(textField_address);
     	     		
     	     		String [] headings1 = new String[] {"Item Type", "Required"}; //Item Needed
     			   //providerTable的資料
     			Object[][] data1 = new Object[][]{
     				{"Item X", "250"},
     				{"Item Y", "300"}
     			};
     	     		
     	     		
     	     		JPanel providerPanel = new JPanel();
     	     		providerPanel.setLayout(null);
     	     		providerPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
     	     		providerPanel.setBounds(765, 229, 685, 450);
     	     		//getContentPane().add(providerPanel);
     	     		
     	     		JScrollPane providerScrollPane = new JScrollPane();
     	     		providerScrollPane.setBounds(101, 53, 452, 285);
     	     		providerPanel.add(providerScrollPane);
     	     		//建立providerTable
     	     		JTable providerTable = new JTable(data1, headings1);
     	     		providerScrollPane.setViewportView(providerTable);
     	     		providerTable.setCellSelectionEnabled(true);
     	     		providerTable.setColumnSelectionAllowed(true);
     	     		
     	     		JButton pro_addRowBtn = new JButton("Add a row");
     	     		pro_addRowBtn.setBounds(114, 371, 140, 31);
     	     		providerPanel.add(pro_addRowBtn);
     	     		
     	     		JButton pro_delRowBtn = new JButton("Delete selected rows");
     	     		pro_delRowBtn.setBounds(337, 371, 243, 31);
     	     		providerPanel.add(pro_delRowBtn);
     	     		
     	     		JLabel labelItemsNeeded = new JLabel("Items needed from providers");
     	     		labelItemsNeeded.setFont(new Font("新細明體", Font.BOLD, 25));
     	     		labelItemsNeeded.setBounds(15, 0, 336, 38);
     	     		providerPanel.add(labelItemsNeeded);
     	     		
     	     		JButton btnSaveInformation = new JButton("Save Information");
     	     		btnSaveInformation.setBounds(126, 723, 234, 31);
     	     		getContentPane().add(btnSaveInformation);
     	     		
     	     		JButton btnLoadInformation = new JButton("Load Information");
     	     		btnLoadInformation.setBounds(426, 723, 234, 31);
     	     		getContentPane().add(btnLoadInformation);
     	     		
     	     		// tab to transfer between panels
     	     		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
     	     		tabbedPane.setBounds(0, 0, 1200, 500);
     	     		//JPanel panel = new JPanel();
     	     		tabbedPane.addTab("Company",companyPanel);
     	     		tabbedPane.addTab("Customer",customer_Panel);
     	     		tabbedPane.addTab("Provider",providerPanel);
     	     		getContentPane().add(tabbedPane);
     	     		
     	     		
     //新增
     	     		
     	     		
     	     
     	     	customerAddRowBtn.addActionListener(new ActionListener() {		//Add a row ??��??	
     	     				public void actionPerformed(ActionEvent arg0) {
     	     				/*	data[i+1][0] = " ";
     	     					data[i+1][1] = " ";
     	     					data[i+1][2] = " "; */ 
     	     					 Object[][] data = new Object[][] {
     	     			             {"1","井�?�全","交�?�大�? "},
     	     			             {"2","小山","清華大學 "},
     	     			             {" ", " ", " "}
     	     			             };  
     	     				}
     	     			});
     	     	
     	     	System.out.println("測試");
             
   			 TableColumnModel cModel = customerTable.getColumnModel();//取得這個table的欄位模型 	
			 TableColumn columnName = cModel.getColumn(0);  //取得這個table某個欄位的資訊 
			 columnName.setPreferredWidth(200);  //個別設定偏好的寬度 
			 
			 
			 
			 
/*			 table_1 = new JTable();
			 table_1.setBounds(280, 102, 144, 76);
			 frame.getContentPane().add(table_1);  */
//			panel.add(new JScrollPane(table));
			 
			 String [] headings_1= new String[] {"Item Type","Required"};
			  Object[][] data_1 = new Object[][] {
		             {"Item X", "250"},
		             {"Item X", "400"}
		             };
	          table_1 = new JTable(data_1, headings_1);
				 table_1.setCellSelectionEnabled(true);
				 table_1.setColumnSelectionAllowed(true);
				 
			 
			 int i = data.length;
			 int j = data[0].length;
		
		
	}
 
	public void toXmlFile(Distributor dist, String url)
    {
    	try
	    {
	    	File xmlfile = new File(url);
    		BufferedWriter br = new BufferedWriter(new FileWriter(xmlfile));
    		br.write("<?xml version='1.0' ?>"); // start to write XML into file
    		br.write(dist.toXML());
    		br.close();
	    }
	    catch (Exception ex)
	    {
	    	System.out.println ("Exception:"+ex);
	    }
    }

	class SupplyTab extends JTabbedPane implements ActionListener{
	    public SupplyTab(SellSupply viewsellsupply, int index){}
	    
	    public void actionPerformed(ActionEvent s){}
	 
	}

	public void actionPerformed(ActionEvent e){

		
		 
		
	}
}// end class DistributorApplet
