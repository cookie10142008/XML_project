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
    String fileurl = System.getProperty("user.dir") + File.separator + "test.xml";  //XML file to read/write to
    Container content = getContentPane();
    private JTextField textField;
    private JTextField textField_1;
    public void init()
    {
    	setUIFont(new FontUIResource("微軟正黑體",Font.PLAIN,20));
    	setSize(1600,800); // set JApplet window size
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
        
        //toXmlFile(...);
        
    }


	public void makeGUI(){
		JFrame frame;
		JTextField textField_2;
		JTable table_1;
		
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
             
             JPanel customer_Panel = new JPanel();
             customer_Panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
     	    customer_Panel.setBounds(15, 229, 685, 450);
     	    content.add(customer_Panel);
     	    customer_Panel.setLayout(null);
     	    
     	    JScrollPane scrollPane = new JScrollPane();
     	    scrollPane.setBounds(101, 53, 452, 285);
     	    customer_Panel.add(scrollPane);
     	    // Step 3: 建�?? Table
     	     //   javax.swing.JTable table=new javax.swing.JTable(data,headings);
     	     JTable table = new JTable(data, headings);
     	     scrollPane.setViewportView(table);
     	     table.setCellSelectionEnabled(true);
     	     table.setColumnSelectionAllowed(true);
     	     
     	     table.setRowHeight(50); //設定列高度為50		
     	     
     	     JButton btnNewButton = new JButton("Add a row");
     	     btnNewButton.setBounds(114, 371, 140, 31);
     	     customer_Panel.add(btnNewButton);
     	     
     	     JButton btnNewButton_1 = new JButton("Delete selected rows");
     	     btnNewButton_1.addActionListener(new ActionListener() {
     	     	public void actionPerformed(ActionEvent arg0) {
     	     	}
     	     });
     	     btnNewButton_1.setBounds(337, 371, 243, 31);
     	     customer_Panel.add(btnNewButton_1);
     	     
     	     		
     	     		JLabel lblItemsSoldTo = new JLabel("Items sold to customers");
     	     		lblItemsSoldTo.setFont(new Font("新細明體", Font.BOLD, 25));
     	     		lblItemsSoldTo.setBounds(15, 0, 336, 38);
     	     		customer_Panel.add(lblItemsSoldTo);
     	     		
     	     		JPanel Company_panel = new JPanel();
     	     		Company_panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
     	     		Company_panel.setBounds(342, 26, 974, 187);
     	     		getContentPane().add(Company_panel);
     	     		Company_panel.setLayout(null);
     	     		
     	     		textField_2 = new JTextField();
     	     		textField_2.setBounds(229, 55, 136, 29);
     	     		Company_panel.add(textField_2);
     	     		textField_2.setColumns(10);
     	     		
     	     		JLabel lblNewLabel = new JLabel("Company information");
     	     		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 25));
     	     		lblNewLabel.setBounds(15, 0, 289, 40);
     	     		Company_panel.add(lblNewLabel);
     	     		
     	     		JLabel lblNewDlabel = new JLabel("Company name:");
     	     		lblNewDlabel.setBounds(58, 58, 195, 23);
     	     		Company_panel.add(lblNewDlabel);
     	     		
     	     		JLabel lblContactAddress = new JLabel("Contact address:");
     	     		lblContactAddress.setBounds(58, 102, 195, 23);
     	     		Company_panel.add(lblContactAddress);
     	     		
     	     		textField = new JTextField();
     	     		textField.setColumns(10);
     	     		textField.setBounds(229, 99, 136, 29);
     	     		Company_panel.add(textField);
     	     		
     	     		JLabel lblAddress = new JLabel("Address:");
     	     		lblAddress.setBounds(447, 58, 162, 23);
     	     		Company_panel.add(lblAddress);
     	     		
     	     		textField_1 = new JTextField();
     	     		textField_1.setColumns(10);
     	     		textField_1.setBounds(539, 55, 136, 29);
     	     		Company_panel.add(textField_1);
     	     
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
		
		
	}
 
 

	 class SupplyTab extends JTabbedPane implements ActionListener{
	    public SupplyTab(SellSupply viewsellsupply, int index){}
	    
	    public void actionPerformed(ActionEvent s){}
	 
	 }

	 public void actionPerformed(ActionEvent e){
		 
		
	 }
}// end class DistributorApplet
