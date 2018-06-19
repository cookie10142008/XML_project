package ece155b.provider.data;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import ece155b.common.Message;
import ece155b.distributor.DistributorApp;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Purchase{

	public JFrame frmPurchaseList;
	private JScrollPane purchaseScrollPane;
	public JTable purchaseTable;
	private DefaultTableModel purTable;
	private JButton btnOkButton, btnNoButton;
	public String[] headings;
	public Object[][] data;
	public static ArrayList<String> itemNameList, neededAmountList;
	public DistributorApp distApp;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Purchase window = new Purchase();
//					window.frmPurchaseList.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Purchase(DistributorApp distributorApp) {
		distApp = distributorApp;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmPurchaseList = new JFrame();
		frmPurchaseList.setTitle("Purchase List");
		frmPurchaseList.setBounds(100, 100, 550, 400);
		frmPurchaseList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPurchaseList.getContentPane().setLayout(null);
		frmPurchaseList.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //讓關閉(x)按鈕沒反應
		
		btnOkButton = new JButton("OK"); //檢查provider有沒有supply request list裡的資訊
		btnOkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(string == supply list request)=> distinguish supplylist & purchase button
				
				System.out.println("ok");
				itemNameList = new ArrayList();
				neededAmountList = new ArrayList();
				
				
				// get supply list from purchase list table
				TableModel purchaseModel = purchaseTable.getModel();
				int rowCount = purchaseTable.getRowCount();
				//System.out.println(rowCount+ (String)purchaseModel.getValueAt(0, 1)+ " "+ purchaseModel.getValueAt(0, 4));
				
				for(int i = 0; i < rowCount; i++)
				{
					//System.out.println(purchaseModel.getValueAt(i, 0)); //id

					itemNameList.add((String)purchaseModel.getValueAt(i, 1)); //name
					neededAmountList.add((String)purchaseModel.getValueAt(i, 4));	//needed amount

					
				}
				System.out.println(supplyList_toXML());
				distApp.append("<supply list>");
				distApp.append(supplyList_toXML());
				distApp.setXMLContent(supplyList_toXML());
				
				
				
				//distApp.append(supplyList_toXML());
				
//				Message msg = new Message();
//				//msg.content = supplyList_toXML();
//				msg.type = "Request_Supply";
//				msg.from = distApp.distributor_Name;
//				msg.content ="supply list request test";
//				
//				distApp.handler.listener.sendMessage(msg);
				
				
			}
		});
		btnOkButton.setBounds(123, 34, 106, 32);
		frmPurchaseList.getContentPane().add(btnOkButton);
		
		btnNoButton = new JButton("Cancel");
		btnNoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPurchaseList.dispose();

			}
		});
		btnNoButton.setBounds(267, 34, 106, 32);
		frmPurchaseList.getContentPane().add(btnNoButton);
		
		purchaseScrollPane = new JScrollPane();
		purchaseScrollPane.setBounds(36, 94, 440, 233);
		frmPurchaseList.getContentPane().add(purchaseScrollPane);
		
		
		// Item Sold
		headings = new String[] { "ID", "Name", "Brand", "Price", "Needed amount" }; 
		// customerTable的資料
//		data = new Object[][] {
//			{ null, null, null, null, null}
//				};
		
//		purTable = new DefaultTableModel(data, headings);
		purTable = new DefaultTableModel(null, headings);

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


	public String supplyList_toXML()
    {
        String xml = null;
        xml = "<SupplyList>";
        for(int i=0; i < itemNameList.size(); i++)
        {
        	xml += "<Supply>";
            	xml += "<supplyName>"+itemNameList.get(i)+"</supplyName>";
            	xml += "<supplyAmount>"+neededAmountList.get(i)+"</supplyAmount>";            	
            xml += "</Supply>";	
        
        }    
        xml += "</SupplyList>";
        
        return xml;
    }



}