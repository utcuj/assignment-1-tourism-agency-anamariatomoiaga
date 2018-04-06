package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;

import bll.ClientBLL;
import bll.ReservationBLL;
import reflections.Reflections;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserView {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView window = new UserView();
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
	public UserView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 440, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPartialPaymant = new JLabel("Partial payments");
		lblPartialPaymant.setBounds(160, 104, 127, 14);
		frame.getContentPane().add(lblPartialPaymant);
		
		JLabel lblClientId = new JLabel("Client id");
		lblClientId.setBounds(10, 158, 72, 14);
		frame.getContentPane().add(lblClientId);
		
		textField = new JTextField();
		textField.setBounds(56, 155, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(163, 158, 34, 14);
		frame.getContentPane().add(lblPrice);
		
		textField_1 = new JTextField();
		textField_1.setBounds(194, 155, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblViewAll = new JLabel("View clients who missed the final payment deadline");
		lblViewAll.setBounds(67, 208, 299, 23);
		frame.getContentPane().add(lblViewAll);
		
		JButton btnView_2 = new JButton("View");
		btnView_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reflections r=new Reflections();
				JTable t=new JTable();
				t=r.createTable(ClientBLL.getClients());
				JFrame frame = new JFrame("Clients");
			    frame.setSize(500,120);
			    frame.setLocationRelativeTo(null);
			    JPanel panel = new JPanel();
			    JScrollPane jsp = new JScrollPane(t);
			    panel.setLayout(new BorderLayout());
			    panel.add(jsp,BorderLayout.CENTER);
			    frame.setContentPane(panel);
			    frame.setVisible(true);
			}
		});
		btnView_2.setBounds(160, 242, 89, 23);
		frame.getContentPane().add(btnView_2);
		
		JButton btnClient = new JButton("Client");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				ClientView r= new ClientView();
		        r.frame.setVisible(true);
			}
		});
		btnClient.setBounds(67, 44, 107, 23);
		frame.getContentPane().add(btnClient);
		
		JButton btnReservation = new JButton("Reservation");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				ReservationView r= new ReservationView();
		        r.frame.setVisible(true);
			}
		});
		btnReservation.setBounds(237, 44, 107, 23);
		frame.getContentPane().add(btnReservation);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int id=Integer.parseInt(textField.getText());
				int price=Integer.parseInt(textField_1.getText());
				ReservationBLL.updatePrice(id,price);
				
			}
		});
		btnPay.setBounds(325, 154, 89, 23);
		frame.getContentPane().add(btnPay);
	}
}
