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

public class Insert extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JComboBox<String> cbCategory;
	private JTextField txtItemId;
	private JTextField txtOrderId; // Read-only box for customer
	private JTextField txtQuantity;
	private JTextField txtTotal;
	private JTextField txtStatus;

	private JTable tableMenu;
	private DefaultTableModel tableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Insert frame = new Insert();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Insert() {
		setTitle("Place New Order");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Place New Order");
		lblTitle.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblTitle.setBounds(340, 10, 200, 30);
		contentPane.add(lblTitle);

		// --- AUTO-GENERATED READ-ONLY ORDER ID BOX ---
		JLabel lblOrderId = new JLabel("Your Order ID:");
		lblOrderId.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblOrderId.setBounds(30, 50, 120, 20);
		contentPane.add(lblOrderId);

		txtOrderId = new JTextField("Auto-Generated");
		txtOrderId.setEditable(false); // Customer cannot edit this
		txtOrderId.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtOrderId.setBounds(150, 50, 200, 22);
		contentPane.add(txtOrderId);

		// --- CUSTOMER DETAILS ---
		JLabel lblName = new JLabel("Customer Name:");
		lblName.setBounds(30, 85, 120, 20);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(150, 85, 200, 20);
		contentPane.add(txtName);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(30, 120, 120, 20);
		contentPane.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setBounds(150, 120, 200, 20);
		contentPane.add(txtPhone);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(30, 155, 120, 20);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(150, 155, 200, 20);
		contentPane.add(txtEmail);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(30, 190, 120, 20);
		contentPane.add(lblAddress);

		txtAddress = new JTextField();
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
		JLabel lblItemId = new JLabel("Enter Item ID:");
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

		txtStatus = new JTextField("Pending");
		txtStatus.setEditable(false);
		txtStatus.setBounds(150, 365, 200, 20);
		contentPane.add(txtStatus);

		// Action Buttons: PLACE ORDER & BACK
		JButton btnSubmit = new JButton("Place Order");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubmit.setBounds(80, 415, 120, 30);
		contentPane.add(btnSubmit);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.setBounds(220, 415, 90, 30);
		contentPane.add(btnBack);

		// --- RIGHT SIDE: CATEGORY MENU TABLE ---
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(400, 50, 400, 335);
		contentPane.add(scrollPane);

		tableModel = new DefaultTableModel(new String[]{"ID", "Item Name", "Price ($)", "Status"}, 0);
		tableMenu = new JTable(tableModel);
		scrollPane.setViewportView(tableMenu);

		// Initial Data Load for default category ("Veg")
		filterByCategory("Veg");

		// --- LISTENERS ---

		// 1. Filter table when category changes
		cbCategory.addActionListener(e -> {
			String selectedCategory = (String) cbCategory.getSelectedItem();
			filterByCategory(selectedCategory);
		});

		// 2. Auto-fill Item ID on row click
		tableMenu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tableMenu.getSelectedRow();
				if (row != -1) {
					String itemId = tableModel.getValueAt(row, 0).toString();
					txtItemId.setText(itemId);
				}
			}
		});

		// 3. Calculate price using Item ID entered by user
		btnCalculate.addActionListener(e -> {
			try {
				if (txtItemId.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter an Item ID.");
					return;
				}

				int itemId = Integer.parseInt(txtItemId.getText().trim());
				int qty = Integer.parseInt(txtQuantity.getText().trim());

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
				Statement stmt = con.createStatement();

				String query = "SELECT PRICE FROM MENU WHERE ITEM_ID = " + itemId;
				ResultSet rs = stmt.executeQuery(query);

				if (rs.next()) {
					double price = rs.getDouble("PRICE");
					txtTotal.setText(String.valueOf(price * qty));
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Item ID. Item not found in database.");
				}

				con.close();
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Item ID and Quantity must be valid numbers.");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error calculating total: " + ex.getMessage());
			}
		});

		// 4. SUBMIT ORDER
		btnSubmit.addActionListener(e -> {
			try {
				if (txtTotal.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please click Calculate before submitting the order.");
					return;
				}

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
				Statement stmt = con.createStatement();

				// Insert Customer
				ResultSet rsCust = stmt.executeQuery("SELECT NVL(MAX(CUSTOMER_ID), 0) + 1 FROM CUSTOMER");
				rsCust.next();
				int customerId = rsCust.getInt(1);

				String custQuery = "INSERT INTO CUSTOMER VALUES(" + customerId + ", '" +
						txtName.getText() + "', '" +
						txtPhone.getText() + "', '" +
						txtEmail.getText() + "', '" +
						txtAddress.getText() + "')";
				stmt.executeUpdate(custQuery);

				// Generate next Order ID
				ResultSet rsOrd = stmt.executeQuery("SELECT NVL(MAX(ORDER_ID), 1000) + 1 FROM ORDERS");
				rsOrd.next();
				int currentOrderId = rsOrd.getInt(1);

				int itemId = Integer.parseInt(txtItemId.getText().trim());
				int qty = Integer.parseInt(txtQuantity.getText().trim());
				double total = Double.parseDouble(txtTotal.getText());
				String status = txtStatus.getText();

				String orderQuery = "INSERT INTO ORDERS VALUES(" + currentOrderId + ", " + customerId + ", " + itemId + ", " + qty + ", " + total + ", '" + status + "')";
				stmt.executeUpdate(orderQuery);

				// Display newly created Order ID in the read-only box
				txtOrderId.setText(String.valueOf(currentOrderId));

				JOptionPane.showMessageDialog(null, "Order #" + currentOrderId + " Placed Successfully!\nNote this ID to modify or cancel your order later.");
				con.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error placing order: " + ex.getMessage());
			}
		});

		// 5. Back Button Listener (Navigates back to CustomerOptions)
		btnBack.addActionListener(e -> {
			CustomerOptions optionsFrame = new CustomerOptions();
			optionsFrame.setVisible(true);
			dispose();
		});
	}

	// Filter Menu Items based on Category
	private void filterByCategory(String category) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
			Statement stmt = con.createStatement();

			String query = "SELECT ITEM_ID, ITEM_NAME, PRICE, AVAILABILITY FROM MENU WHERE LOWER(CATEGORY) = '" + category.toLowerCase() + "' ORDER BY ITEM_NAME ASC";
			ResultSet rs = stmt.executeQuery(query);

			tableModel.setRowCount(0);

			while (rs.next()) {
				int id = rs.getInt("ITEM_ID");
				String name = rs.getString("ITEM_NAME");
				double price = rs.getDouble("PRICE");
				String avail = rs.getString("AVAILABILITY");

				tableModel.addRow(new Object[]{id, name, price, avail});
			}
			con.close();
		} catch (Exception ex) {
			System.out.println("Error filtering menu items: " + ex.getMessage());
		}
	}
}