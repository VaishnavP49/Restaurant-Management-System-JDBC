package BCA;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Index extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Restaurant Management");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 24));
		lblNewLabel.setBounds(93, 21, 271, 32);
		contentPane.add(lblNewLabel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("HP Simplified Jpan", Font.PLAIN, 15));
		btnAdd.setBounds(81, 83, 108, 32);
		contentPane.add(btnAdd);
		
		btnAdd.addActionListener(e -> {
			Insert insertFrame = new Insert();
			insertFrame.setVisible(true);
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("HP Simplified Jpan", Font.PLAIN, 15));
		btnUpdate.setBounds(81, 144, 108, 32);
		contentPane.add(btnUpdate);
		
		JButton btnView = new JButton("View");
		btnView.setFont(new Font("HP Simplified Jpan", Font.PLAIN, 15));
		btnView.setBounds(244, 83, 108, 32);
		contentPane.add(btnView);
		
		btnView.addActionListener(e -> {
			Display displayFrame = new Display();
			displayFrame.setVisible(true);
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("HP Simplified Jpan", Font.PLAIN, 15));
		btnDelete.setBounds(244, 144, 108, 32);
		contentPane.add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("HP Simplified Jpan", Font.PLAIN, 15));
		btnExit.setBounds(20, 221, 108, 32);
		contentPane.add(btnExit);
		
		btnExit.addActionListener(e -> System.exit(0));
	}
}