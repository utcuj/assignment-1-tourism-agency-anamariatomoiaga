package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bll.UserBLL;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login {

	JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(40, 64, 79, 24);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(40, 130, 79, 24);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(146, 66, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBounds(293, 102, 109, 23);
		frame.getContentPane().add(rdbtnAdmin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String u = textField.getText();
				String p = String.valueOf(passwordField.getPassword());
				if(rdbtnAdmin.isSelected()) {
					int i = UserBLL.findUser(u, p, true);
					if(i==1) {
						frame.setVisible(false);
						Admin r= new Admin();
				        r.frame.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null, "Username or password incorect");
				}
				else {
					int i = UserBLL.findUser(u, p, false);
					if(i==1) {
						frame.setVisible(false);
						UserView r= new UserView();
				        r.frame.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null, "Username or password incorect");
				}
			}
		});
		btnLogin.setBounds(143, 202, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 132, 86, 20);
		frame.getContentPane().add(passwordField);
	}
}
