package BCA;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Choice extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Choice frame = new Choice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Choice() {
		setTitle("Select Portal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select the User");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		lblNewLabel.setBounds(141, 39, 162, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		btnCustomer.setBounds(48, 112, 156, 43);
		contentPane.add(btnCustomer);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		btnAdmin.setBounds(238, 112, 156, 43);
		contentPane.add(btnAdmin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		btnExit.setBounds(10, 218, 108, 35);
		contentPane.add(btnExit);

		// --- LISTENERS ---

		// 1. Navigate to Customer Services Portal (CustomerOptions.java)
		btnCustomer.addActionListener(e -> {
			CustomerOptions customerOptionsFrame = new CustomerOptions();
			customerOptionsFrame.setVisible(true);
			dispose(); // Close Choice window
		});

		// 2. Navigate to Admin Dashboard (AdminDash.java)
		btnAdmin.addActionListener(e -> {
			AdminDash adminDashFrame = new AdminDash();
			adminDashFrame.setVisible(true);
			dispose(); // Close Choice window
		});

		// 3. Exit Application
		btnExit.addActionListener(e -> System.exit(0));
	}
}