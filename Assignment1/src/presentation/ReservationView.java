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

import bll.ReservationBLL;
import model.Reservation;
import reflections.Reflections;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ReservationView {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationView window = new ReservationView();
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
	public ReservationView() {
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
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setBounds(10, 29, 77, 14);
		frame.getContentPane().add(lblDestination);
		
		JLabel lblHotel = new JLabel("Hotel");
		lblHotel.setBounds(10, 67, 46, 14);
		frame.getContentPane().add(lblHotel);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(10, 103, 62, 14);
		frame.getContentPane().add(lblNumber);
		
		JLabel lblNewLabel = new JLabel("Price");
		lblNewLabel.setBounds(10, 140, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(101, 26, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblIdClient = new JLabel("Id Client");
		lblIdClient.setBounds(10, 175, 62, 14);
		frame.getContentPane().add(lblIdClient);
		
		textField_1 = new JTextField();
		textField_1.setBounds(101, 64, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(101, 100, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(101, 137, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(101, 172, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id;
				String d=textField.getText();
				String r=textField_1.getText();
				int n=Integer.parseInt(textField_2.getText());
				int p=Integer.parseInt(textField_3.getText());
				int c=Integer.parseInt(textField_4.getText());
				Date date = new Date();
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(textField_6.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Reservation reservation=new Reservation(d,r,n,p,c,date);
				id=ReservationBLL.insertReservation(reservation);
				if(id>-1)
					JOptionPane.showMessageDialog(null,"Rezervarea a fost adaugata.","",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAdd.setBounds(260, 25, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id=Integer.parseInt(textField_5.getText());
				String hotel=textField_1.getText();
				ReservationBLL.updateReservation(id,hotel);
			}
		});
		btnUpdate.setBounds(260, 63, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int id1=Integer.parseInt(textField_5.getText());
				ReservationBLL.deleteReservation(id1);
					
			}
		});
		btnDelete.setBounds(260, 103, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id=Integer.parseInt(textField_5.getText());
				Reservation r = new Reservation();
				r=ReservationBLL.findById(id);
				JOptionPane.showMessageDialog(null,r.toString(),"",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnView.setBounds(260, 136, 89, 23);
		frame.getContentPane().add(btnView);
		
		JButton btnViewAll = new JButton("View all");
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reflections r=new Reflections();
				JTable t=new JTable();
				t=r.createTable(ReservationBLL.getAllReservations());
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
		btnViewAll.setBounds(260, 171, 89, 23);
		frame.getContentPane().add(btnViewAll);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				UserView r= new UserView();
		        r.frame.setVisible(true);
			}
		});
		btnBack.setBounds(260, 215, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 236, 46, 14);
		frame.getContentPane().add(lblId);
		
		textField_5 = new JTextField();
		textField_5.setBounds(101, 233, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 211, 46, 14);
		frame.getContentPane().add(lblDate);
		
		textField_6 = new JTextField();
		textField_6.setBounds(101, 202, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
	}
}
