package ece155b.provider.data;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class Purchase{

	private JFrame frame;
	private JScrollPane purchaseScrollPane;
	private JTable purchaseTable;
	private DefaultTableModel purTable;
	private JButton btnOkButton, btnNoButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Purchase window = new Purchase();
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
	public Purchase() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnOkButton = new JButton("OK");
		btnOkButton.setBounds(94, 31, 87, 23);
		frame.getContentPane().add(btnOkButton);
		
		btnNoButton = new JButton("Cancel");
		btnNoButton.setBounds(270, 31, 87, 23);
		frame.getContentPane().add(btnNoButton);
		
		purchaseScrollPane = new JScrollPane();
		purchaseScrollPane.setBounds(92, 94, 266, 130);
		frame.getContentPane().add(purchaseScrollPane);
		
		
		// Item Sold
		String[] headings = new String[] { "ID", "Name", "Brand", "Price", "In Stock" }; 
		// customerTable的資料
		Object[][] data = new Object[][] {
			{ null, null, null, null, null}
				};
		
		purTable = new DefaultTableModel(data, headings);
		purchaseTable = new JTable(purTable);
		purchaseTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		purchaseScrollPane.setViewportView(purchaseTable);

		purchaseTable.setRowHeight(50); //設定列高度為50	
		TableColumnModel purchaseModel = purchaseTable.getColumnModel();//取得這個table的欄位模型 	
		TableColumn columnID = purchaseModel.getColumn(0);  //取得這個table某個欄位的資訊 
		TableColumn columnName = purchaseModel.getColumn(1);
		TableColumn columnItemType = purchaseModel.getColumn(2);
		TableColumn columnPrice = purchaseModel.getColumn(3);
		TableColumn columnCount = purchaseModel.getColumn(4);

		columnID.setPreferredWidth(150);  //個別設定偏好的寬度  		
		columnName.setPreferredWidth(150);
		columnItemType.setPreferredWidth(150);  
		columnPrice.setPreferredWidth(150);
		columnCount.setPreferredWidth(150);
		purchaseScrollPane.setViewportView(purchaseTable);
	}
}
