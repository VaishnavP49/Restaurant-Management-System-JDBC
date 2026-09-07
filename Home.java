package BCA;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField textField; 
	private JTextField textField_1; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Home() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Restaurant Management");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		lblNewLabel.setBounds(130, 20, 220, 28);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(209, 83, 123, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(209, 144, 123, 28);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(113, 85, 86, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(113, 146, 86, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		btnNewButton.setBounds(180, 198, 100, 28);
		contentPane.add(btnNewButton);

		btnNewButton.addActionListener(e -> {
		    String username = textField.getText();
		    String password = textField_1.getText();

		    try {
		        Class.forName("oracle.jdbc.driver.OracleDriver");

		        Connection con = DriverManager.getConnection(
		            "jdbc:oracle:thin:@localhost:1521/XEPDB1",
		            username,
		            password
		        );

		        JOptionPane.showMessageDialog(
		            null,
		            "Login Successful!"
		        );

		        con.close();

		        // Open Choice page instead of Index page
		        Choice choiceFrame = new Choice();
		        choiceFrame.setVisible(true);
		        dispose();

		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(
		            null,
		            "Invalid Username or Password"
		        );
		        System.out.println(ex);
		    }
		});
	}
}