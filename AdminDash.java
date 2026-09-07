package BCA;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AdminDash extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDash frame = new AdminDash();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminDash() {
		setTitle("Admin Dashboard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Admin Dashboard");
		lblTitle.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblTitle.setBounds(135, 25, 200, 30);
		contentPane.add(lblTitle);

		// Option 1: Display All Customer Orders
		JButton btnDisplayOrders = new JButton("View Orders");
		btnDisplayOrders.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisplayOrders.setBounds(115, 80, 200, 40);
		contentPane.add(btnDisplayOrders);

		// Option 2: Manage Food Menu Items
		JButton btnManageMenu = new JButton("Manage Menu");
		btnManageMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnManageMenu.setBounds(115, 140, 200, 40);
		contentPane.add(btnManageMenu);

		// Option 3: Back to main screen
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(10, 235, 90, 30);
		contentPane.add(btnBack);

		// --- LISTENERS ---

		// Opens the Display.java frame to view all customer orders
		btnDisplayOrders.addActionListener(e -> {
			Display displayFrame = new Display();
			displayFrame.setVisible(true);
			dispose();
		});

		// Place code or open frame for managing menu items
		btnManageMenu.addActionListener(e -> {
			ManageMenu menuFrame = new ManageMenu();
			menuFrame.setVisible(true);
			dispose();
		});

		// Navigates back to Choice.java
		btnBack.addActionListener(e -> {
			Choice choiceFrame = new Choice();
			choiceFrame.setVisible(true);
			dispose();
		});
	}
}