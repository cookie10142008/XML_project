/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ece155b;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
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
public class DistributorApplet extends JApplet implements ActionListener{
	public DistributorApplet() {
	}

  /* You will definitely have more functions below,
   * such as reading/writing XML files, GUI parts,
   * mouse/action event listeners...
   */
    
    String fileurl = System.getProperty("user.dir") + File.separator + "test.xml";  //XML file to read/write to (under bin dir)
    // new two item lists(sellsupply &　needsupply)
    public Distributor dist = new Distributor();
    //GUI
    Container content = getContentPane();
    public static JTextField textField_companyName, textField_contactMe, textField_address;
    public JTable customerTable;
    
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
        
//        String fileUrl = "Distributor.xml";
//        Distributor dist = new Distributor();
//    	dist.name = textField_companyName.getText();
//    	dist.address = textField_contactMe.getText();
//    	dist.contact = textField_address.getText();
//    	toXmlFile(dist,fileUrl);
    	//System.out.println(textField_companyName.getText());
        //Company company = new Company();
    }


	public void makeGUI(){
		
		
		//
		String[] headings = new String[] { "ID", "Name", "Item Type", "Price", "Available" }; // Item Sold
		// customerTable的資料
		Object[][] data = new Object[][] { 
			{ "", "", "", "", "" },
			// {"Item X","50.5","300"},
			// {"Item Y","42.2","200"}
		};

		JPanel customer_Panel = new JPanel();
		customer_Panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		customer_Panel.setBounds(15, 229, 685, 600);
		//content.add(customer_Panel);
		customer_Panel.setLayout(null);
     	    
		JScrollPane customerScrollPane = new JScrollPane();
		customerScrollPane.setBounds(101, 53, 650, 285);
		customer_Panel.add(customerScrollPane);
		//用DefaultTableModel建立customerTable

		DefaultTableModel custTable = new DefaultTableModel(data, headings); 
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

		JButton customerDelRowBtn = new JButton("Delete selected rows"); //custermoTable刪除列
		customerDelRowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = customerTable.getSelectedRow();

				if (i >= 0){
					custTable.removeRow(i);
				}
				else{
					JOptionPane.showMessageDialog(null, "Unable to Delete");
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

		String [] headings1 = new String[] {"ID", "Name", "Item Type", "Price", "Available"}; //Item Needed
		//providerTable的資料
		Object[][] data1 = new Object[][]{
			{"Item X", "250", "", "", ""},
			{"Item Y", "300", "", "", ""}
		};


		JPanel providerPanel = new JPanel();
		providerPanel.setLayout(null);
		providerPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		providerPanel.setBounds(765, 229, 685, 450);
		//getContentPane().add(providerPanel);

		JScrollPane providerScrollPane = new JScrollPane();
		providerScrollPane.setBounds(101, 53, 650, 285);
		providerPanel.add(providerScrollPane);

		//用DefaultTableModel建立providerTable
		DefaultTableModel provTable;  
		provTable = new DefaultTableModel(data1, headings1); 
		JTable providerTable = new JTable(provTable);     	    
		customerScrollPane.setViewportView(customerTable);
		providerScrollPane.setViewportView(providerTable);

		providerTable.setRowHeight(50); //設定列高度為50


		JButton pro_addRowBtn = new JButton("Add a row"); //providerTable的Add a row按鈕 
		pro_addRowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				provTable.addRow(new Vector()); //新增空白一列    
			}
		});
		pro_addRowBtn.setBounds(114, 371, 140, 31);
		providerPanel.add(pro_addRowBtn);
     	     		
		JButton pro_delRowBtn = new JButton("Delete selected rows");  //providerTable刪除列
		pro_delRowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = providerTable.getSelectedRow();

				if (i >= 0){
					provTable.removeRow(i);
				}
				else{
					JOptionPane.showMessageDialog(null, "Unable to Delete");
				}
				/*  		DefaultTableModel model = (DefaultTableModel) customerTable.getModel();

     	    		try{
     	    		int SelectedRowIndex = customerTable.getSelectedRow();
     	    		model.removeRow(SelectedRowIndex);
     	    		}catch (Exception ex){
     	    			JOptionPane.showMessageDialog(null,  ex);
     	    		} */
			}
		});
		pro_delRowBtn.setBounds(337, 371, 243, 31);
		providerPanel.add(pro_delRowBtn);

		JLabel labelItemsNeeded = new JLabel("Items needed from providers");
		labelItemsNeeded.setFont(new Font("新細明體", Font.BOLD, 25));
		labelItemsNeeded.setBounds(15, 0, 336, 38);
		providerPanel.add(labelItemsNeeded);

		JButton btnSaveInformation = new JButton("Save Information");
		btnSaveInformation.setBounds(121, 545, 234, 31);
		getContentPane().add(btnSaveInformation);
		btnSaveInformation.addActionListener(this);

		JButton btnLoadInformation = new JButton("Load Information");
		btnLoadInformation.setBounds(401, 545, 234, 31);
		getContentPane().add(btnLoadInformation);
		btnLoadInformation.addActionListener(this);

		// tab to transfer between panels
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1200, 500);
		//JPanel panel = new JPanel();
		tabbedPane.addTab("Company",companyPanel);
		tabbedPane.addTab("Customer",customer_Panel);

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
		tabbedPane.addTab("Provider",providerPanel);
		getContentPane().add(tabbedPane);


		customerAddRowBtn.addActionListener(new ActionListener() {		//custmerTable的Add a row Button
			public void actionPerformed(ActionEvent arg0) {

				custTable.addRow(new Vector()); //新增空白一列
			}
		});     	     	


				 		
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

	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
        	// !!! problem: no blank row + row can't be in typing situation 
			case "Save Information":
				System.out.println("save info");
				dist.sellItems.removeAllElements(); // remove all items in vector sellItems
//				SellSupply item1 = new SellSupply("12","coke","cosco",100,3);
//    			dist.addSellItem(item1);
				
    			String fileUrl = "Distributor.xml";
		        
		    	dist.name = textField_companyName.getText();
		    	dist.address = textField_contactMe.getText();
		    	dist.contact = textField_address.getText();
//		    	toXmlFile(dist,fileUrl);
			    
		    	
		    	int row = customerTable.getRowCount(); //get Row Count = 5
		        int col = customerTable.getColumnCount();  //get Column Count = 5
		        String id="", name="", brand="";
		        double price = 0;
		        int count = 0;
//		        System.out.println("col欄位數:"+col+"; row列數:"+row);
		       
		            
		        for(int i = 0; row > i; i++){   //from first row to last row
		        	for(int j = 0; col > j; j++){  //row1: from first column to last column
		        		System.out.println("col欄位數:"+j+"; row列數:"+i);
		        		if ((String) customerTable.getValueAt(i, j) != ""){		        			
		        			switch(j) {
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
		        					String tempPrice = (String) customerTable.getValueAt(i, j);
		        					price = Double.parseDouble(tempPrice); 		        					
		        				}
		        				catch (NumberFormatException ex)
		        				{
		        					JOptionPane.showMessageDialog(null, "價格輸入不正確，請輸入數字");		        					
		        				}
		        				break;
		        			
		        			case 4:
		        				try
		        				{
		        					String tempCount = (String) customerTable.getValueAt(i, j);
		        					count = Integer.parseInt(tempCount);
		        				}
		        				catch (NumberFormatException ex)
		        				{
		        					JOptionPane.showMessageDialog(null, "數量輸入不正確，請輸入數字");
		        				}
//		        				count = Integer.parseInt((String) customerTable.getValueAt(i, j));
		        				break;
		        			
		        		}}
/*		        			}else{
		        				switch(j){
		        				case 0:
			        				JOptionPane.showMessageDialog(null, "ID第"+ String.valueOf(i+1) +"行未填寫");
		        					break;
		        				case 1:
			        				JOptionPane.showMessageDialog(null, "Name第"+ String.valueOf(i+1) +"行未填寫");        					
		        					break;
		        				case 2:
			        				JOptionPane.showMessageDialog(null, "Brand第"+ String.valueOf(i+1) +"行未填寫");		        					
		        					break;
		        				case 3:
			        				JOptionPane.showMessageDialog(null, "Price第"+ String.valueOf(i+1) +"行未填寫");
		        					break;
		        				case 4:
			        				JOptionPane.showMessageDialog(null, "Count第"+ String.valueOf(i+1) +"行未填寫");
		        					break;
		        				}
		        				JOptionPane.showMessageDialog(null, "第"+ String.valueOf(i+1) +"行第"+ String.valueOf(j+1) +"列空白");
			        			break;
		        			} */
	        		
		        	}
		        	
		        	// new SellSupply("12","coke","cosco",100,3);
		        	SellSupply item = new SellSupply(id,name,brand,price,count);
        			dist.addSellItem(item);         	
		        	
		        }
		        
		        toXmlFile(dist,fileUrl);				
				break;
				
			case "Load Information":
				System.out.println("load info");
				
				JFileChooser fileChooser = new JFileChooser();//declare file chooser 
				int returnValue = fileChooser.showOpenDialog(null);//call file chooser 
				if (returnValue == JFileChooser.APPROVE_OPTION) //choosing file or not 
				{ 
					File selectedFile = fileChooser.getSelectedFile();//get file & assign to File 
					
					
					System.out.println(selectedFile.getName() +"  " + selectedFile.getAbsolutePath()); //print file name & path				
					//XMLParser xmlParser = new XMLParser(dist,selectedFile);
					XMLParser xmlParser = new XMLParser();
					
					try {
						xmlParser.readXmlFile(selectedFile.getAbsolutePath()); // transfer file to parse
						
					} catch (SAXException | IOException | ParserConfigurationException e1) {
						
						e1.printStackTrace();
					}
					
					
				} 			
				
				
				break;
	                
		}
		
		 
		
	}
}// end class DistributorApplet
