package BCA;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ModifyOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearchOrderId;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JComboBox<String> cbCategory;
	private JTextField txtItemId;
	private JTextField txtQuantity;
	private JTextField txtTotal;
	private JTextField txtStatus;

	private JTable tableMenu;
	private DefaultTableModel tableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ModifyOrder frame = new ModifyOrder();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ModifyOrder() {
		setTitle("Modify / Cancel Existing Order");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Modify / Cancel Order");
		lblTitle.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblTitle.setBounds(310, 10, 250, 30);
		contentPane.add(lblTitle);

		// --- SEARCH ORDER SECTION ---
		JLabel lblSearchOrderId = new JLabel("Enter Order ID:");
		lblSearchOrderId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSearchOrderId.setBounds(30, 50, 120, 20);
		contentPane.add(lblSearchOrderId);

		txtSearchOrderId = new JTextField();
		txtSearchOrderId.setBounds(150, 50, 110, 22);
		contentPane.add(txtSearchOrderId);

		JButton btnFetch = new JButton("Search");
		btnFetch.setBounds(270, 50, 80, 22);
		contentPane.add(btnFetch);

		// --- CUSTOMER & ORDER DETAILS ---
		JLabel lblName = new JLabel("Customer Name:");
		lblName.setBounds(30, 85, 120, 20);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(150, 85, 200, 20);
		contentPane.add(txtName);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(30, 120, 120, 20);
		contentPane.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setEditable(false);
		txtPhone.setBounds(150, 120, 200, 20);
		contentPane.add(txtPhone);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(30, 155, 120, 20);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setBounds(150, 155, 200, 20);
		contentPane.add(txtEmail);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(30, 190, 120, 20);
		contentPane.add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setBounds(150, 190, 200, 20);
		contentPane.add(txtAddress);

		// Category Dropdown
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(30, 225, 120, 20);
		contentPane.add(lblCategory);

		cbCategory = new JComboBox<>(new String[]{"Veg", "Non-Veg", "Dessert"});
		cbCategory.setBounds(150, 225, 200, 20);
		contentPane.add(cbCategory);

		// Item ID Field
		JLabel lblItemId = new JLabel("Item ID:");
		lblItemId.setBounds(30, 260, 120, 20);
		contentPane.add(lblItemId);

		txtItemId = new JTextField();
		txtItemId.setBounds(150, 260, 200, 20);
		contentPane.add(txtItemId);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(30, 295, 120, 20);
		contentPane.add(lblQuantity);

		txtQuantity = new JTextField();
		txtQuantity.setBounds(150, 295, 200, 20);
		contentPane.add(txtQuantity);

		JLabel lblTotal = new JLabel("Total Amount:");
		lblTotal.setBounds(30, 330, 120, 20);
		contentPane.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(150, 330, 100, 20);
		contentPane.add(txtTotal);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(260, 330, 90, 20);
		contentPane.add(btnCalculate);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(30, 365, 120, 20);
		contentPane.add(lblStatus);

		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setBounds(150, 365, 200, 20);
		contentPane.add(txtStatus);

		// ACTION BUTTONS: UPDATE, DELETE, BACK
		JButton btnUpdate = new JButton("Update Order");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBounds(30, 415, 110, 30);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("Cancel Order");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(150, 415, 110, 30);
		contentPane.add(btnDelete);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(270, 415, 80, 30);
		contentPane.add(btnBack);

		// --- RIGHT SIDE: CATEGORY MENU TABLE ---
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(400, 50, 400, 335);
		contentPane.add(scrollPane);

		tableModel = new DefaultTableModel(new String[]{"ID", "Item Name", "Price ($)", "Status"}, 0);
		tableMenu = new JTable(tableModel);
		scrollPane.setViewportView(tableMenu);

		filterByCategory("Veg");

		// --- LISTENERS ---

		cbCategory.addActionListener(e -> filterByCategory((String) cbCategory.getSelectedItem()));

		tableMenu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tableMenu.getSelectedRow();
				if (row != -1) {
					txtItemId.setText(tableModel.getValueAt(row, 0).toString());
				}
			}
		});

		// 1. FETCH ORDER DATA
		btnFetch.addActionListener(e -> {
			try {
				if (txtSearchOrderId.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter an Order ID.");
					return;
				}

				int orderId = Integer.parseInt(txtSearchOrderId.getText().trim());

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
				Statement stmt = con.createStatement();

				String query = "SELECT c.NAME, c.PHONE, c.EMAIL, c.ADDRESS, o.ITEM_ID, o.QUANTITY, o.TOTAL_AMOUNT, o.STATUS "
						+ "FROM ORDERS o JOIN CUSTOMER c ON o.CUSTOMER_ID = c.CUSTOMER_ID "
						+ "WHERE o.ORDER_ID = " + orderId;

				ResultSet rs = stmt.executeQuery(query);

				if (rs.next()) {
					txtName.setText(rs.getString("NAME"));
					txtPhone.setText(rs.getString("PHONE"));
					txtEmail.setText(rs.getString("EMAIL"));
					txtAddress.setText(rs.getString("ADDRESS"));
					txtItemId.setText(String.valueOf(rs.getInt("ITEM_ID")));
					txtQuantity.setText(String.valueOf(rs.getInt("QUANTITY")));
					txtTotal.setText(String.valueOf(rs.getDouble("TOTAL_AMOUNT")));
					txtStatus.setText(rs.getString("STATUS"));
				} else {
					JOptionPane.showMessageDialog(null, "Order ID not found.");
					clearFields();
				}

				con.close();
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Order ID must be a valid number.");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error searching order: " + ex.getMessage());
			}
		});

		// 2. CALCULATE TOTAL
		btnCalculate.addActionListener(e -> {
			try {
				if (txtItemId.getText().trim().isEmpty() || txtQuantity.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter both Item ID and Quantity.");
					return;
				}

				int itemId = Integer.parseInt(txtItemId.getText().trim());
				int qty = Integer.parseInt(txtQuantity.getText().trim());

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("SELECT PRICE FROM MENU WHERE ITEM_ID = " + itemId);

				if (rs.next()) {
					double price = rs.getDouble("PRICE");
					txtTotal.setText(String.valueOf(price * qty));
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Item ID.");
				}

				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error calculating total: " + ex.getMessage());
			}
		});

		// 3. UPDATE ORDER
		btnUpdate.addActionListener(e -> {
			try {
				if (txtSearchOrderId.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please search for an Order ID first.");
					return;
				}

				if (txtTotal.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please click Calculate before updating.");
					return;
				}

				int orderId = Integer.parseInt(txtSearchOrderId.getText().trim());
				int itemId = Integer.parseInt(txtItemId.getText().trim());
				int qty = Integer.parseInt(txtQuantity.getText().trim());
				double total = Double.parseDouble(txtTotal.getText());

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
				Statement stmt = con.createStatement();

				String updateQuery = "UPDATE ORDERS SET ITEM_ID = " + itemId + ", QUANTITY = " + qty
						+ ", TOTAL_AMOUNT = " + total + " WHERE ORDER_ID = " + orderId;

				int rows = stmt.executeUpdate(updateQuery);

				if (rows > 0) {
					JOptionPane.showMessageDialog(null, "Order #" + orderId + " updated successfully!");
				} else {
					JOptionPane.showMessageDialog(null, "Failed to update. Order ID not found.");
				}

				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error updating order: " + ex.getMessage());
			}
		});

		// 4. CANCEL/DELETE ORDER (DIRECT CANCELLATION WITHOUT CONFIRMATION PROMPT)
		btnDelete.addActionListener(e -> {
			try {
				if (txtSearchOrderId.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please search for an Order ID first.");
					return;
				}

				int orderId = Integer.parseInt(txtSearchOrderId.getText().trim());

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("SELECT CUSTOMER_ID FROM ORDERS WHERE ORDER_ID = " + orderId);
				int customerId = -1;
				if (rs.next()) {
					customerId = rs.getInt("CUSTOMER_ID");
				}

				int rows = stmt.executeUpdate("DELETE FROM ORDERS WHERE ORDER_ID = " + orderId);

				if (customerId != -1) {
					stmt.executeUpdate("DELETE FROM CUSTOMER WHERE CUSTOMER_ID = " + customerId);
				}

				if (rows > 0) {
					JOptionPane.showMessageDialog(null, "Order #" + orderId + " canceled successfully!");
					clearFields();
					txtSearchOrderId.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Order ID not found.");
				}

				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error canceling order: " + ex.getMessage());
			}
		});

		// 5. BACK BUTTON
		btnBack.addActionListener(e -> {
			CustomerOptions optionsFrame = new CustomerOptions();
			optionsFrame.setVisible(true);
			dispose();
		});
	}

	private void clearFields() {
		txtName.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		txtItemId.setText("");
		txtQuantity.setText("");
		txtTotal.setText("");
		txtStatus.setText("");
	}

	private void filterByCategory(String category) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
			Statement stmt = con.createStatement();

			String query = "SELECT ITEM_ID, ITEM_NAME, PRICE, AVAILABILITY FROM MENU WHERE LOWER(CATEGORY) = '"
					+ category.toLowerCase() + "' ORDER BY ITEM_NAME ASC";
			ResultSet rs = stmt.executeQuery(query);

			tableModel.setRowCount(0);

			while (rs.next()) {
				tableModel.addRow(new Object[]{
						rs.getInt("ITEM_ID"),
						rs.getString("ITEM_NAME"),
						rs.getDouble("PRICE"),
						rs.getString("AVAILABILITY")
				});
			}
			con.close();
		} catch (Exception ex) {
			System.out.println("Error filtering menu items: " + ex.getMessage());
		}
	}
}