package BCA;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class Update extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update frame = new Update();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Update() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update data");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		lblNewLabel.setBounds(161, 10, 112, 21);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(230, 44, 112, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Order id:");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(81, 45, 112, 17);
		contentPane.add(lblNewLabel_1);

	}
}
