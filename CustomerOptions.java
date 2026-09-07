package BCA;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomerOptions extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				CustomerOptions frame = new CustomerOptions();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public CustomerOptions() {
		setTitle("Customer Services");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Customer Order");
		lblTitle.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblTitle.setBounds(139, 30, 240, 30);
		contentPane.add(lblTitle);

		JButton btnNewOrder = new JButton("Place New Order");
		btnNewOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewOrder.setBounds(120, 85, 200, 40);
		contentPane.add(btnNewOrder);

		JButton btnModifyOrder = new JButton("Modify Order");
		btnModifyOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModifyOrder.setBounds(120, 156, 200, 40);
		contentPane.add(btnModifyOrder);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(10, 223, 90, 30);
		contentPane.add(btnBack);

		// --- EVENT LISTENERS ---

		// Opens Insert.java for new orders
		btnNewOrder.addActionListener(e -> {
			Insert insertFrame = new Insert();
			insertFrame.setVisible(true);
			dispose();
		});

		// Opens ModifyOrder.java for existing orders
		btnModifyOrder.addActionListener(e -> {
			ModifyOrder modifyFrame = new ModifyOrder();
			modifyFrame.setVisible(true);
			dispose();
		});

		// Returns to Choice.java
		btnBack.addActionListener(e -> {
			Choice choiceFrame = new Choice();
			choiceFrame.setVisible(true);
			dispose();
		});
	}
}