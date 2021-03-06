package ece155b.provider;

import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ece155b.distributor.data.SellSupply;
import ece155b.provider.data.*;
import ece155b.provider.xml.ProParser;
import javax.xml.parsers.*;

import org.xml.sax.SAXException;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

/**
 * @author 林瑋鴻、鍾昀倫
 */
public class ProviderApplet extends JApplet implements ActionListener{
	
	public ProviderApplet() {
		
//		new ProviderApp("aaa");
		
	}
	
    String fileurl = System.getProperty("user.dir") + File.separator + "test.xml";  //XML file to read/write to (under bin dir)
    // new two item lists(sellsupply &　needsupply)
    public Provider prov = new Provider();
    //GUI
    Container content = getContentPane();
    public static JTextField textField_companyName;
    public static JTextArea textArea_address, textArea_contactMe;
    public static JTable customerTable;
    public static JTable distributorNameTable;
    public static JTabbedPane tabbedPane; 
    public static DefaultTableModel custTable, distNameTable;
    
    public void init()
    {
    	Frame title = (Frame)this.getParent().getParent();
    	title.setTitle("Provider");
    	
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
    }

	public void makeGUI(){
	
		String[] headings = new String[] { "ID", "Name", "Item Type", "Price", "Available" }; // Item Sold
		// customerTable的資料
		Object[][] data = new Object[][] { 
			{ null, null, null, null, null}
		
		};

		JPanel customer_Panel = new JPanel();
		customer_Panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		customer_Panel.setBounds(15, 229, 685, 600);
		customer_Panel.setLayout(null);
     	    
		JScrollPane customerScrollPane = new JScrollPane();
		customerScrollPane.setBounds(101, 53, 650, 285);
		customer_Panel.add(customerScrollPane);
		
		custTable = new DefaultTableModel(data, headings); 
		customerTable = new JTable(custTable);
		customerTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		customerScrollPane.setViewportView(customerTable);

		customerTable.setRowHeight(50); //設定列高度為50		
		TableColumnModel customerModel = customerTable.getColumnModel();//取得這個table的欄位模型 	
		TableColumn columnID = customerModel.getColumn(0);  //取得這個table某個欄位的資訊 
		TableColumn columnName = customerModel.getColumn(1);
		TableColumn columnItemType = customerModel.getColumn(2);
		TableColumn columnPrice = customerModel.getColumn(3);
		TableColumn columnCount = customerModel.getColumn(4);

		columnID.setPreferredWidth(150);  //個別設定偏好的寬度  		
		columnName.setPreferredWidth(150);
		columnItemType.setPreferredWidth(150);  
		columnPrice.setPreferredWidth(150);
		columnCount.setPreferredWidth(150);

		JButton customerAddRowBtn = new JButton("Add a row");
		customerAddRowBtn.setBounds(114, 371, 140, 31);
		customer_Panel.add(customerAddRowBtn);
		
		customerAddRowBtn.addActionListener(new ActionListener() {  //custmerTable的Add a row Button
			public void actionPerformed(ActionEvent arg0) {

				custTable.addRow(new Vector()); //新增空白一列
			}
		});     	     	
		
		JButton customerDelRowBtn = new JButton("Delete selected rows"); //custermoTable刪除列
		customerDelRowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int count[] = customerTable.getSelectedRows(); //刪除多行
				if(count.length <= 0){
					JOptionPane.showMessageDialog(null, "Unable to Delete");
				}else{
					for(int i = 0; i < count.length; i++){
						custTable.removeRow(customerTable.getSelectedRow());
					}
				} 	    		
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
		companyPanel.setLayout(null);

		textField_companyName = new JTextField();
		textField_companyName.setBounds(218, 55, 384, 29);
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
		labelContactMe.setBounds(58, 253, 128, 23);
		companyPanel.add(labelContactMe);

		JLabel labelAddress = new JLabel("Address:");
		labelAddress.setBounds(59, 149, 85, 23);
		companyPanel.add(labelAddress);

		customerScrollPane.setViewportView(customerTable);

		JButton btnSaveInformation = new JButton("Save Information");
		btnSaveInformation.setBounds(121, 545, 234, 31);
		getContentPane().add(btnSaveInformation);
		btnSaveInformation.addActionListener(this);

		JButton btnLoadInformation = new JButton("Load Information");
		btnLoadInformation.setBounds(401, 545, 234, 31);
		getContentPane().add(btnLoadInformation);
		btnLoadInformation.addActionListener(this);
		
//		// Load Distributor List button
//		JButton btnLoadDistributorList = new JButton("Load Distributor List");
//		btnLoadDistributorList.setBounds(699, 545, 234, 31);
//		getContentPane().add(btnLoadDistributorList);
//		btnLoadDistributorList.addActionListener(this);

		// tab to transfer between panels
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1200, 500);
		tabbedPane.addTab("Company",companyPanel);
		
		
		LineBorder textArea_address_border=new LineBorder(Color.BLACK);
		textArea_address = new JTextArea();
		textArea_address.setBorder(textArea_address_border); 
		textArea_address.setLineWrap(true);
		textArea_address.setWrapStyleWord(true);
		textArea_address.setBounds(218, 269, 289, 69);
		JScrollPane scrollPane = new JScrollPane(textArea_address);
		scrollPane.setBounds(217, 127, 289, 69);
		companyPanel.add(scrollPane);
		
		
		LineBorder textArea_contact_border=new LineBorder(Color.BLACK);
		textArea_contactMe = new JTextArea();
		textArea_contactMe.setBorder(textArea_contact_border); 
		textArea_contactMe.setLineWrap(true);
		textArea_contactMe.setWrapStyleWord(true);
		textArea_contactMe.setBounds(218, 269, 289, 69);
		JScrollPane scrollPane_contact = new JScrollPane(textArea_contactMe);
		scrollPane_contact.setBounds(218, 235, 289, 69);
		companyPanel.add(scrollPane_contact);
		tabbedPane.addTab("Distributor",customer_Panel);

		JTextArea txtarea_PressEnter = new JTextArea();
		txtarea_PressEnter.setForeground(Color.BLUE);
		txtarea_PressEnter.setBackground(Color.LIGHT_GRAY);
		txtarea_PressEnter.setWrapStyleWord(true);
		txtarea_PressEnter.setLineWrap(true);
		txtarea_PressEnter.setFont(new Font("新細明體", Font.BOLD, 25));
		txtarea_PressEnter.setEditable(false);
		txtarea_PressEnter.setText("Please press enter after finishing editing info in table(make sure to leave the editting situation)");
		txtarea_PressEnter.setBounds(809, 59, 357, 133);
		customer_Panel.add(txtarea_PressEnter);
		
		//distributor connection tabbedPane
		JPanel connectPanel = new JPanel();
		connectPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		connectPanel.setBounds(341, 39, 974, 187);
		tabbedPane.addTab("Distributor Connection",connectPanel);
		connectPanel.setLayout(null);
		
		JButton btnConnProv = new JButton("Connect to distributor!");
		btnConnProv.setFont(new Font("新細明體", Font.BOLD, 23));
		btnConnProv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new ProviderApp(textField_companyName.getText());
				
			}
		});
		btnConnProv.setBounds(416, 189, 269, 57);
		connectPanel.add(btnConnProv);
		
		//distributors' Name table
		String [] heading_distName = new String[] {"Distributor Name"}; 
		//Table的資料
		Object[][] data_distName = new Object[][]{
			{ null}
		};
		
		JScrollPane distributorNameScrollPane = new JScrollPane();
		distributorNameScrollPane.setBounds(101, 53, 269, 285);
		connectPanel.add(distributorNameScrollPane);
		
		distNameTable = new DefaultTableModel(data_distName, heading_distName); 
		distributorNameTable = new JTable(distNameTable);
		distributorNameTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		distributorNameScrollPane.setViewportView(distributorNameTable);

		distributorNameTable.setRowHeight(50); //設定列高度為50		
		TableColumnModel distributorNameModel = distributorNameTable.getColumnModel();//取得這個table的欄位模型 	
		TableColumn columnID1 = distributorNameModel.getColumn(0);  //取得這個table某個欄位的資訊 
		columnID1.setPreferredWidth(150);  //個別設定偏好的寬度  		
		

		JButton distributorNameAddRowBtn = new JButton("Add a row");
		distributorNameAddRowBtn.setBounds(114, 371, 140, 31);
		connectPanel.add(distributorNameAddRowBtn);
		
		distributorNameAddRowBtn.addActionListener(new ActionListener() {  //custmerTable的Add a row Button
			public void actionPerformed(ActionEvent arg0) {

				distNameTable.addRow(new Vector()); //新增空白一列
			}
		});     	     	
		
		JButton distributorNameDelRowBtn = new JButton("Delete selected rows"); //custermoTable刪除列
		distributorNameDelRowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int count[] = distributorNameTable.getSelectedRows(); //刪除多行
				if(count.length <= 0){
					JOptionPane.showMessageDialog(null, "Unable to Delete");
				}else{
					for(int i = 0; i < count.length; i++){
						distNameTable.removeRow(distributorNameTable.getSelectedRow());
					}
				} 	    		
			}
		});
		distributorNameDelRowBtn.setBounds(337, 371, 243, 31);
		connectPanel.add(distributorNameDelRowBtn);
		
		
		getContentPane().add(tabbedPane);// last line
		
	}
 
	public void toXmlFile(Provider prov, String url)
    {
    	try
	    {
	    	File xmlfile = new File(url);
    		BufferedWriter br = new BufferedWriter(new FileWriter(xmlfile));
    		br.write("<?xml version='1.0' ?>"); // start to write XML into file
    		br.write(prov.toXML());
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

	
	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
        	// !!! problem: no blank row + row can't be in typing situation 
			case "Save Information":
				System.out.println("save info");
				
//				//add distname from applet gui
//				for(int i=0; i < distributorNameTable.getRowCount(); i++)
//				{
//					ProParser.distributorList.add((String)distributorNameTable.getValueAt(i,0));
//				
//				}
					
				prov.sellItems.removeAllElements(); // remove all items in vector sellItems	, needItems		
				Provider.dist_list.removeAllElements();
				
    			String fileUrl = "Provider_"+textField_companyName.getText()+".xml";
    			boolean customerSave = false;
		        // get company info
		    	prov.name = textField_companyName.getText();
		    	//dist.address = textField_address.getText();
		    	//dist.contact = textField_contactMe.getText();
			    
		    	prov.contact = textArea_contactMe.getText();
		    	prov.address = textArea_address.getText();
		    	
		    	int custRow = customerTable.getRowCount(); //get Row Count = 5
		        int custCol = customerTable.getColumnCount();  //get Column Count = 5
		        String id="", name="", brand="";
		        double price = 0;
		        int count = 0;
//		        System.out.println("col欄位數:"+col+"; row列數:"+row);
    					        
 		        	customerOuterLoop:
		        	for(int i = 0; custRow > i; i++){
//		        		customerInnerLoop:
		        		for(int j = 0; custCol > j; j++){
		        			if ( customerTable.getValueAt(i, j) != null){
		        				switch(j){
		        				case 0:
		        					id = (String) customerTable.getValueAt(i, j);
		        					break;
		        				case 1:
		        					name = (String) customerTable.getValueAt(i, j);
		        					break;
		        				case 2:
		        					brand = (String) customerTable.getValueAt(i, j);
		        					break;
		        				case 3:	
		        					try
			        				{
			        					String tempPrice = String.valueOf( customerTable.getValueAt(i, j) ) ;
			        					price = Double.parseDouble(tempPrice); 		 
			        					System.out.println("test:"+String.valueOf(i+1)+"行"+price);
			        					
			        				}
			        				catch (NumberFormatException ex)
			        				{
			        					JOptionPane.showMessageDialog(null, "Customer的第"+ String.valueOf(i+1) +"行價格輸入不正確，請輸入數字");	
			        					customerSave = false;
			        					break customerOuterLoop;
			        				}
//		        					break customerOuterLoop;
//		        					break;
		        				case 4:	       
			        				try
			        				{

			        					String tempCount = String.valueOf( customerTable.getValueAt(i, j) );
			        					System.out.println("test1:"+String.valueOf(i+1)+"行"+customerTable.getValueAt(i, j));
			        					count = (int)Double.parseDouble(tempCount);//  ???
			        					System.out.println("test2:"+String.valueOf(i+1)+"行"+count);
			        					customerSave = true;
			        				}
			        				catch (NumberFormatException ex)
			        				{
			        					JOptionPane.showMessageDialog(null, "Customer的第"+ String.valueOf(i+1) +"行數量輸入不正確，請輸入數字");
			        					customerSave = false;
			        					break customerOuterLoop;
			        				}
//		        					break customerOuterLoop;
//			        				break;
		        				} // end switch
		        			}else{
		        				JOptionPane.showMessageDialog(null, "Customer的第"+ String.valueOf(i+1) +"列第"+ String.valueOf(j+1) +"行空白");
	        					customerSave = false;
		        				break customerOuterLoop;	        				
		        			} // end if
		        		} // end column
		        		
		        		// new SellSupply		        		
		              	SellSupply item = new SellSupply(id,name,brand,price,count);
	        			prov.addSellItem(item);
		              	
		        	} // end row
		        
		        // get dist name from distributorName table
		        int distRow = distributorNameTable.getRowCount(); //get Row Count = 5    
		        String distname = "";
		        
		        for(int i = 0; distRow > i; i++){
		        	distname = (String) distributorNameTable.getValueAt(i, 0);
		        	Provider.dist_list.add(distname);
		        } // end row
		        
		        //Provider.dist_list.add("dist name");
		        
		        // check customerSave
		        if(customerSave) {
		        	JOptionPane.showMessageDialog(null, "存檔成功");
		        	toXmlFile(prov,fileUrl);	// write info into xml format
		        }
		        			        
				break;
				
			//"Load Information" button
			case "Load Information":
				System.out.println("load info");
				
				JFileChooser fileChooser = new JFileChooser();//declare file chooser 
				int returnValue = fileChooser.showOpenDialog(null);//call file chooser 
				if (returnValue == JFileChooser.APPROVE_OPTION) //choosing file or not 
				{ 
					File selectedFile = fileChooser.getSelectedFile();//get file & assign to File 
					
					
					System.out.println(selectedFile.getName() +"  " + selectedFile.getAbsolutePath()); //print file name & path				
					
					ProParser proParser = new ProParser();
					
					try {
						proParser.readXmlFile(selectedFile.getAbsolutePath()); // transfer file to parse
						
					} catch (SAXException | IOException | ParserConfigurationException e1) {
						
						e1.printStackTrace();
					}
					
					
				} 			
				
				
				break;
				
				
//			case "Load Distributor List":
//				//System.out.println(Provider.dist_list.get(1));
//				
//				break;
	                
		}
		
	}


}// end class ProviderApplet
