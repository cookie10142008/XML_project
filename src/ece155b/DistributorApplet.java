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

import ece155b.data.Distributor;
import ece155b.data.Supply;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.xml.XMLParser;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    String fileurl = System.getProperty("user.dir") + File.separator + "test.xml";  //XML file to read/write to

    public void init()
    {
    	setUIFont(new FontUIResource("微軟正黑體",Font.PLAIN,20));
    	
	    Container content = getContentPane();
	    content.setBackground(Color.white);
	    content.setLayout(new FlowLayout());
	    content.add(new JLabel("Hello World"));
	
	    try {       //try to read the xml file if present
	        //readXmlFile(fileurl);
	        content.add(new JLabel("Hello!"));
	        
	        
	        
	    } catch(Exception e) { //if xml file does not exist create new blank doctor object
	        content.add(new JLabel("XML file not found"));
	        
	    }
    
    
	    makeGUI(content);
    
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
        
        //toXmlFile(...);
        
    }


	public void makeGUI(Container content){
		JFrame frame;
		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;
		JTable table_1;
//		frame = new JFrame();
//		frame.setBounds(100, 100, 636, 450);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(new FlowLayout());
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 26, 46, 15);
		//frame.getContentPane().add(lblNewLabel);
		content.add(lblNewLabel);

		
		JLabel label = new JLabel("New label");
		label.setBounds(10, 59, 46, 15);
		content.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(202, 38, 46, 15);
		content.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(261, 35, 96, 21);
		content.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 6, 414, 86);
		content.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(58, 10, 96, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(58, 55, 96, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Add a row");	
		btnNewButton.setBounds(20, 206, 87, 23);
		content.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(117, 206, 87, 23);
		content.add(btnNewButton_1);
		
/*		table = new JTable();
		table.setBounds(27, 113, 177, 70);
		frame.getContentPane().add(table);
		
		
		JTableModel = new DefaultTableModel();
		createTableModel();
		table.setModel(JTableModel); */
		
		
//	     String [] headings= new String[] {"序�??","?????","?���?","?��???","權�??"};
	     String [] headings= new String[] {"Item Type","Price","Available"};

	     // Step 2: ??��?��?�顯示在表格中�?��?��??
	      /*   Object[][] data = new Object[][] {
	                    {"1",Boolean.FALSE,"井�?�全","交�?�大�? ","02/06/2000",new Float(1)},
	                    {"2",Boolean.TRUE,"小山","清華大學 ","02/07/2000",new Float(2)}
	                    }; */
	     Object[][] data = new Object[][] {
             {"Item X","50.5","300"},
             {"Item Y","42.2","200"}
             }; 
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 97, 238, 99);
			content.add(scrollPane);
			// Step 3: 建�?? Table
			 //   javax.swing.JTable table=new javax.swing.JTable(data,headings);
			 JTable table = new JTable(data, headings);
			 scrollPane.setViewportView(table);
			 table.setCellSelectionEnabled(true);
			 table.setColumnSelectionAllowed(true);
			 
			 table.setRowHeight(50); //設定列高度為50		
			 TableColumnModel cModel = table.getColumnModel();//取得這個table的欄位模型 	
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
	
		btnNewButton.addActionListener(new ActionListener() {		//Add a row ??��??	
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
		
		
	}
 
 

	 class SupplyTab extends JTabbedPane implements ActionListener{
	    public SupplyTab(SellSupply viewsellsupply, int index){}
	    
	    public void actionPerformed(ActionEvent s){}
	 
	 }

	 public void actionPerformed(ActionEvent e){
		 
		
	 }
	 
	 
}// end class DistributorApplet
