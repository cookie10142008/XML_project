package ece155b.provider;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ece155b.distributor.data.SellSupply;
import ece155b.provider.data.*;
import ece155b.provider.xml.ProParser;
//import ece155b.distributor.data.Distributor;
//import ece155b.distributor.data.Supply;
//import ece155b.distributor.data.SellSupply;
//import ece155b.distributor.xml.XMLParser;
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
		
		new ProviderApp();
		
	}
	
    String fileurl = System.getProperty("user.dir") + File.separator + "test.xml";  //XML file to read/write to (under bin dir)
    // new two item lists(sellsupply &　needsupply)
    public Provider prov = new Provider();
    //GUI
    Container content = getContentPane();
    public static JTextField textField_companyName;
    public static JTextArea textArea_address, textArea_contactMe;
    public static JTable customerTable;
    public static JTable providerTable;
    public static JTabbedPane tabbedPane; 
    public static DefaultTableModel custTable, provTable;
    
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
		//content.add(customer_Panel);
		customer_Panel.setLayout(null);
     	    
		JScrollPane customerScrollPane = new JScrollPane();
		customerScrollPane.setBounds(101, 53, 650, 285);
		customer_Panel.add(customerScrollPane);
		//用DefaultTableModel建立customerTable

		//DefaultTableModel custTable = new DefaultTableModel(data, headings); 
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
		//getContentPane().add(companyPanel);
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

		String [] headings1 = new String[] {"ID", "Name", "Item Type", "Price", "Available"}; //Item Needed
		//providerTable的資料
		Object[][] data1 = new Object[][]{
			{ null, null, null, null, null}
			
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
		
		provTable = new DefaultTableModel(data1, headings1); 
		providerTable = new JTable(provTable);     	    
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
				
				int count[] = providerTable.getSelectedRows(); //刪除多行
				if(count.length <= 0){
					JOptionPane.showMessageDialog(null, "Unable to delete");
				}else{
					for(int i = 0; i < count.length; i++){
						provTable.removeRow(providerTable.getSelectedRow());
					}
				}
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
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1200, 500);
		//JPanel panel = new JPanel();
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
		
//		tabbedPane.addTab("Provider",providerPanel);
		
//		JTextArea txtarea_PressEnter1 = new JTextArea();
//		txtarea_PressEnter1.setForeground(Color.BLUE);
//		txtarea_PressEnter1.setBackground(Color.LIGHT_GRAY);
//		txtarea_PressEnter1.setWrapStyleWord(true);
//		txtarea_PressEnter1.setLineWrap(true);
//		txtarea_PressEnter1.setFont(new Font("新細明體", Font.BOLD, 25));
//		txtarea_PressEnter1.setEditable(false);
//		txtarea_PressEnter1.setText("Please press enter after finishing editing info in table(make sure to leave the editting situation)");
//		txtarea_PressEnter1.setBounds(809, 59, 357, 133);
//		providerPanel.add(txtarea_PressEnter1);
		getContentPane().add(tabbedPane);


		customerAddRowBtn.addActionListener(new ActionListener() {		//custmerTable的Add a row Button
			public void actionPerformed(ActionEvent arg0) {

				custTable.addRow(new Vector()); //新增空白一列
			}
		});     	     	


				 		
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
				prov.sellItems.removeAllElements(); // remove all items in vector sellItems	, needItems		
//				prov.needItems.removeAllElements();
				
    			String fileUrl = "Provider.xml";
    			boolean customerSave = false;
//    			boolean providerSave = false;
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
		       		       
/*		        	int provRow = providerTable.getRowCount();
				    int provCol = providerTable.getColumnCount();  //get Column Count = 5
		        	System.out.println("provider table");
		        	providerOuterLoop:
		        	for(int i = 0; provRow > i; i++){
		        		for(int j = 0; provCol > j; j++){
		        			if ( providerTable.getValueAt(i, j) != null){
		        				switch(j){
		        				case 0:
		        					id = (String) providerTable.getValueAt(i, j);
		        					System.out.println("id="+id+" "+i+","+j);
		        					break;
		        				case 1:
		        					name = (String) providerTable.getValueAt(i, j);
		          					System.out.println("name="+name+" "+i+","+j);
		        					break;
		        				case 2:
		        					brand = (String) providerTable.getValueAt(i, j);
		          					System.out.println("brand="+brand+" "+i+","+j);
		        					break;
		        				case 3:
		        					try
			        				{
			        					String tempPrice = String.valueOf( providerTable.getValueAt(i, j) ); //String.valueOf = (string) ??
			        					price = Double.parseDouble(tempPrice);
			         					System.out.println("price="+price+" "+i+","+j);

			        				}
			        				catch (NumberFormatException ex)
			        				{
			        					JOptionPane.showMessageDialog(null, "Provider的第"+ String.valueOf(i+1) +"行價格輸入不正確，請輸入數字");	
			        					providerSave = false;
			        					break providerOuterLoop;
			        				}
		        				case 4:
		        					try
			        				{
			        					String tempCount = String.valueOf( providerTable.getValueAt(i, j) );
			        					//count = Integer.parseInt(tempCount);
			          					count = (int)Double.parseDouble(tempCount);
			        					System.out.println("count="+count+" "+i+","+j);
			          					providerSave = true;

			        				}
			        				catch (NumberFormatException ex)
			        				{
			        					JOptionPane.showMessageDialog(null, "Provider的第"+ String.valueOf(i+1) +"行數量輸入不正確，請輸入數字");
			        					providerSave = false;
			        					break providerOuterLoop;
			        				}
		        				
		        				}// end switch
		        			}else{
		        				JOptionPane.showMessageDialog(null, "Provider的第"+ String.valueOf(i+1) +"列第"+ String.valueOf(j+1) +"行空白");
		        				providerSave = false;
		        				break providerOuterLoop;		        				
		        			}// end if
		        		}// end column
		        		
		        		//new NeedSupply 
	        			
		        	}// end row
		        
		        System.out.println("providerSave: "+providerSave +" "+"customerSave: " + customerSave);
		        if(!providerSave || !customerSave){
		        	if (custRow == 0 && provRow == 0){
		             	JOptionPane.showMessageDialog(null, "未輸入資訊");
		        	}
		        }
*/		        
		        if(customerSave) {
		        	JOptionPane.showMessageDialog(null, "存檔成功");
		        	toXmlFile(prov,fileUrl);	
		        }
		        			        
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

//					XMLParser xmlParser = new XMLParser();
					ProParser proParser = new ProParser();
					
					try {
						proParser.readXmlFile(selectedFile.getAbsolutePath()); // transfer file to parse
						
					} catch (SAXException | IOException | ParserConfigurationException e1) {
						
						e1.printStackTrace();
					}
					
					
				} 			
				
				
				break;
	                
		}
		
		 
		
	}


}// end class ProviderApplet
