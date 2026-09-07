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

public class ManageMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtItemId;
	private JTextField txtItemName;
	private JTextField txtPrice;
	private JComboBox<String> cbCategory;
	private JComboBox<String> cbAvailability;

	private JTable tableMenu;
	private DefaultTableModel tableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ManageMenu frame = new ManageMenu();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ManageMenu() {
		setTitle("Admin - Manage Menu Items");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Manage Menu Items");
		lblTitle.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblTitle.setBounds(330, 10, 220, 30);
		contentPane.add(lblTitle);

		// --- FORM FIELDS ---
		JLabel lblItemId = new JLabel("Item ID:");
		lblItemId.setBounds(30, 60, 100, 20);
		contentPane.add(lblItemId);

		txtItemId = new JTextField();
		txtItemId.setBounds(140, 60, 200, 20);
		contentPane.add(txtItemId);

		JLabel lblItemName = new JLabel("Item Name:");
		lblItemName.setBounds(30, 100, 100, 20);
		contentPane.add(lblItemName);

		txtItemName = new JTextField();
		txtItemName.setBounds(140, 100, 200, 20);
		contentPane.add(txtItemName);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(30, 140, 100, 20);
		contentPane.add(lblCategory);

		cbCategory = new JComboBox<>(new String[]{"Veg", "Non-Veg", "Dessert"});
		cbCategory.setBounds(140, 140, 200, 20);
		contentPane.add(cbCategory);

		JLabel lblPrice = new JLabel("Price ($):");
		lblPrice.setBounds(30, 180, 100, 20);
		contentPane.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setBounds(140, 180, 200, 20);
		contentPane.add(txtPrice);

		JLabel lblAvailability = new JLabel("Availability:");
		lblAvailability.setBounds(30, 220, 100, 20);
		contentPane.add(lblAvailability);

		cbAvailability = new JComboBox<>(new String[]{"Available", "Out of Stock"});
		cbAvailability.setBounds(140, 220, 200, 20);
		contentPane.add(cbAvailability);

		// --- BUTTONS ---
		JButton btnAdd = new JButton("Add Item");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdd.setBounds(30, 291, 100, 30);
		contentPane.add(btnAdd);

		JButton btnUpdate = new JButton("Update Item");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBounds(140, 291, 105, 30);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("Delete Item");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(255, 291, 105, 30);
		contentPane.add(btnDelete);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(30, 380, 105, 30);
		contentPane.add(btnBack);

		// --- RIGHT SIDE: MENU TABLE ---
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(380, 60, 430, 350);
		contentPane.add(scrollPane);

		tableModel = new DefaultTableModel(new String[]{"ID", "Item Name", "Category", "Price ($)", "Status"}, 0);
		tableMenu = new JTable(tableModel);
		scrollPane.setViewportView(tableMenu);

		// Load menu items on startup
		loadMenuData();

		// --- LISTENERS ---

		// 1. Fill text fields when a table row is clicked
		tableMenu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tableMenu.getSelectedRow();
				if (row != -1) {
					txtItemId.setText(tableModel.getValueAt(row, 0).toString());
					txtItemName.setText(tableModel.getValueAt(row, 1).toString());
					cbCategory.setSelectedItem(tableModel.getValueAt(row, 2).toString());
					txtPrice.setText(tableModel.getValueAt(row, 3).toString());
					cbAvailability.setSelectedItem(tableModel.getValueAt(row, 4).toString());
				}
			}
		});

		// 2. ADD ITEM
		btnAdd.addActionListener(e -> {
			try {
				if (txtItemId.getText().trim().isEmpty() || txtItemName.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all required fields.");
					return;
				}

				int itemId = Integer.parseInt(txtItemId.getText().trim());
				String name = txtItemName.getText().trim();
				String category = (String) cbCategory.getSelectedItem();
				double price = Double.parseDouble(txtPrice.getText().trim());
				String availability = (String) cbAvailability.getSelectedItem();

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
				Statement stmt = con.createStatement();

				String sql = "INSERT INTO MENU VALUES(" + itemId + ", '" + name + "', '" + category + "', " + price + ", '" + availability + "')";
				stmt.executeUpdate(sql);

				JOptionPane.showMessageDialog(null, "Item Added Successfully!");
				con.close();
				loadMenuData();
				clearFields();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error adding item: " + ex.getMessage());
			}
		});

		// 3. UPDATE ITEM
		btnUpdate.addActionListener(e -> {
			try {
				if (txtItemId.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please select an item to update.");
					return;
				}

				int itemId = Integer.parseInt(txtItemId.getText().trim());
				String name = txtItemName.getText().trim();
				String category = (String) cbCategory.getSelectedItem();
				double price = Double.parseDouble(txtPrice.getText().trim());
				String availability = (String) cbAvailability.getSelectedItem();

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
				Statement stmt = con.createStatement();

				String sql = "UPDATE MENU SET ITEM_NAME = '" + name + "', CATEGORY = '" + category + "', PRICE = " + price + ", AVAILABILITY = '" + availability + "' WHERE ITEM_ID = " + itemId;
				int rows = stmt.executeUpdate(sql);

				if (rows > 0) {
					JOptionPane.showMessageDialog(null, "Item Updated Successfully!");
					loadMenuData();
					clearFields();
				} else {
					JOptionPane.showMessageDialog(null, "Item ID not found.");
				}
				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error updating item: " + ex.getMessage());
			}
		});

		// 4. DELETE ITEM
		btnDelete.addActionListener(e -> {
			try {
				if (txtItemId.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please select an item to delete.");
					return;
				}

				int itemId = Integer.parseInt(txtItemId.getText().trim());

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
				Statement stmt = con.createStatement();

				String sql = "DELETE FROM MENU WHERE ITEM_ID = " + itemId;
				int rows = stmt.executeUpdate(sql);

				if (rows > 0) {
					JOptionPane.showMessageDialog(null, "Item Deleted Successfully!");
					loadMenuData();
					clearFields();
				} else {
					JOptionPane.showMessageDialog(null, "Item ID not found.");
				}
				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error deleting item: " + ex.getMessage());
			}
		});

		// 5. BACK BUTTON
		btnBack.addActionListener(e -> {
			AdminDash dash = new AdminDash();
			dash.setVisible(true);
			dispose();
		});
	}

	private void clearFields() {
		txtItemId.setText("");
		txtItemName.setText("");
		txtPrice.setText("");
		cbCategory.setSelectedIndex(0);
		cbAvailability.setSelectedIndex(0);
	}

	private void loadMenuData() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "restaurant", "restaurant123");
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM MENU ORDER BY ITEM_ID ASC";
			ResultSet rs = stmt.executeQuery(query);

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
			System.out.println("Error loading menu data: " + ex.getMessage());
		}
	}
}