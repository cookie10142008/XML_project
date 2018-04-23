package ece155b;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel; //?���?
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class project1 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table_1;
	
//	private JTable table = new JTable();
	
		
//	DefaultTableModel JTableModel = new DefaultTableModel();
//	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					project1 window = new project1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public project1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 636, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 26, 46, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("New label");
		label.setBounds(10, 59, 46, 15);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(202, 26, 87, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 6, 414, 86);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(58, 10, 96, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(58, 55, 96, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(303, 27, 96, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Add a row");	
		btnNewButton.setBounds(46, 322, 87, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(161, 322, 87, 23);
		frame.getContentPane().add(btnNewButton_1);
		
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
			scrollPane.setBounds(10, 97, 414, 210);
			frame.getContentPane().add(scrollPane);
			// Step 3: 建�?? Table
			 //   javax.swing.JTable table=new javax.swing.JTable(data,headings);
			 JTable table = new JTable(data, headings);
			 scrollPane.setViewportView(table);
			 table.setCellSelectionEnabled(true);
			 table.setColumnSelectionAllowed(true);
			 
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
		
	

	         
	     // 建�?��??? Frame �??��表格
	     /*    javax.swing.JFrame MyFrame=new javax.swing.JFrame("TableStep1 表格測試");
	         MyFrame.setSize(500,200);
	         MyFrame.setLocation(200,200);
	         MyFrame.getContentPane().add(new javax.swing.JScrollPane(table));
	         MyFrame.setVisible(true);  */

		
	}
}
