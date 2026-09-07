package BCA;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Display extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Display() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 320); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCustomer = new JButton("Customer table");
		btnCustomer.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		btnCustomer.setBounds(26, 48, 135, 26);
		contentPane.add(btnCustomer);
		
		JButton btnMenu = new JButton("Menu table");
		btnMenu.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		btnMenu.setBounds(26, 107, 135, 26);
		contentPane.add(btnMenu);
		
		JButton btnOrders = new JButton("Orders table");
		btnOrders.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		btnOrders.setBounds(26, 163, 135, 26);
		contentPane.add(btnOrders);
		
		JButton btnExit = new JButton("Back");
		btnExit.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		btnExit.setBounds(26, 227, 85, 26);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("View Data");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 16));
		lblNewLabel.setBounds(240, 10, 148, 20);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(180, 48, 380, 205);
		contentPane.add(scrollPane);
		
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		loadCustomerData();

		btnCustomer.addActionListener(e -> loadCustomerData());
		btnMenu.addActionListener(e -> loadMenuData());
		btnOrders.addActionListener(e -> loadOrdersData());

		// FIX: Reopen AdminDash frame before disposing Display
		btnExit.addActionListener(e -> {
			AdminDash adminDashFrame = new AdminDash();
			adminDashFrame.setVisible(true);
			dispose();
		});
	}

	private void loadCustomerData() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER ORDER BY CUSTOMER_ID ASC");

			tableModel.setColumnIdentifiers(new String[]{"ID", "Name", "Phone", "Email", "Address"});
			tableModel.setRowCount(0); 

			while (rs.next()) {
				tableModel.addRow(new Object[]{
						rs.getInt("CUSTOMER_ID"),
						rs.getString("NAME"),
						rs.getString("PHONE"),
						rs.getString("EMAIL"),
						rs.getString("ADDRESS")
				});
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error loading Customer data: " + ex.getMessage());
		}
	}

	private void loadMenuData() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM MENU ORDER BY ITEM_ID ASC");

			tableModel.setColumnIdentifiers(new String[]{"ID", "Item Name", "Category", "Price", "Available"});
			tableModel.setRowCount(0);

			while (rs.next()) {
				tableModel.addRow(new Object[]{
						rs.getInt("ITEM_ID"),
						rs.getString("ITEM_NAME"),
						rs.getString("CATEGORY"),
						rs.getDouble("PRICE"),
						rs.getString("AVAILABILITY")
				});
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error loading Menu data: " + ex.getMessage());
		}
	}

	private void loadOrdersData() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
			Statement stmt = con.createStatement();

			String sql = "SELECT o.ORDER_ID, c.NAME, m.ITEM_NAME, o.QUANTITY, o.TOTAL_AMOUNT, o.STATUS " +
					     "FROM ORDERS o " +
					     "JOIN CUSTOMER c ON o.CUSTOMER_ID = c.CUSTOMER_ID " +
					     "JOIN MENU m ON o.ITEM_ID = m.ITEM_ID " +
					     "ORDER BY o.ORDER_ID DESC";

			ResultSet rs = stmt.executeQuery(sql);

			tableModel.setColumnIdentifiers(new String[]{"Order ID", "Customer", "Item", "Qty", "Total", "Status"});
			tableModel.setRowCount(0);

			while (rs.next()) {
				tableModel.addRow(new Object[]{
						rs.getInt("ORDER_ID"),
						rs.getString("NAME"),
						rs.getString("ITEM_NAME"),
						rs.getInt("QUANTITY"),
						rs.getDouble("TOTAL_AMOUNT"),
						rs.getString("STATUS")
				});
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error loading Orders data: " + ex.getMessage());
		}
	}
}