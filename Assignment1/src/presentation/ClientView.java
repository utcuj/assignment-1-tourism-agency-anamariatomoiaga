package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bll.ClientBLL;
import model.Client;
import reflections.Reflections;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientView {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientView window = new ClientView();
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
	public ClientView() {
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
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(20, 28, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblCard = new JLabel("Card");
		lblCard.setBounds(20, 67, 46, 14);
		frame.getContentPane().add(lblCard);
		
		JLabel lblCnp = new JLabel("Cnp");
		lblCnp.setBounds(20, 106, 46, 14);
		frame.getContentPane().add(lblCnp);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(20, 150, 64, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(20, 198, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(93, 25, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(93, 64, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(93, 103, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(93, 147, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(93, 195, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id;
				String numeClient=textField.getText();
				int card=Integer.parseInt(textField_1.getText());
				String cnp=textField_2.getText();
				String address=textField_3.getText();
				Client clientAdaugat=new Client(numeClient,card,cnp,address);
				id=ClientBLL.insertClient(clientAdaugat);
				if(id>-1)
					JOptionPane.showMessageDialog(null,"Clientul a fost adaugat.","",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAdd.setBounds(279, 19, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id=Integer.parseInt(textField_4.getText());
				String adresaClient=textField_3.getText();
				ClientBLL.updateClient(id,adresaClient);
				
			}
		});
		btnUpdate.setBounds(279, 63, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id=Integer.parseInt(textField_4.getText());
				Client client = new Client();
				client=ClientBLL.findClientById(id);
				JOptionPane.showMessageDialog(null,client.toString(),"",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnView.setBounds(279, 102, 89, 23);
		frame.getContentPane().add(btnView);
		
		JButton btnViewAll = new JButton("View all");
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reflections r=new Reflections();
				JTable t=new JTable();
				t=r.createTable(ClientBLL.getAllClients());
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
		btnViewAll.setBounds(279, 141, 89, 23);
		frame.getContentPane().add(btnViewAll);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				UserView r= new UserView();
		        r.frame.setVisible(true);
			}
		});
		btnBack.setBounds(279, 194, 89, 23);
		frame.getContentPane().add(btnBack);
	}

}
