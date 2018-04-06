package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Reservation;
import java.text.SimpleDateFormat;

public class ReservationDAO {
	protected static final Logger LOGGER = Logger.getLogger(ReservationDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO reservation (destination,hotel,number,price,id_c,final_date)"
			+ " VALUES (?,?,?,?,?,?)";
	private static final String updateStatementString = "UPDATE reservation" +" set hotel=? where id=?";
	private static final String deleteStatementString = "DELETE FROM reservation where id = ?";
	private final static String findStatementString = "SELECT * FROM reservation where id = ?";
	private static final String updateStatementString1 = "UPDATE reservation" +" set price=price - ?  where id_c=? and curdate()<final_date";

	public static Reservation findById(int Id) {
		Reservation toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1,Id);
			rs = findStatement.executeQuery();
			rs.next();

			String destination = rs.getString("destination");
			String hotel = rs.getString("hotel");
			int number = rs.getInt("number");
			int price = rs.getInt("price");
			int id_c = rs.getInt("id_c");
			Date d = rs.getDate("final_date");
			
			toReturn = new Reservation(Id, destination,hotel,number, price, id_c,d);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ReservationDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static int insert(Reservation r) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, r.getDestination());
			insertStatement.setString(2, r.getHotel());
			insertStatement.setInt(3, r.getNumber());
			insertStatement.setInt(4, r.getPrice());
			insertStatement.setInt(5, r.getClientId());
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			insertStatement.setString(6,f.format(r.getDate()));
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ReservationDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	public static void update(int id,String hotel) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		int insertedId = -1;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1,hotel);
			updateStatement.setInt(2, id);
			insertedId=updateStatement.executeUpdate();
			
			if(insertedId==0) {
				JOptionPane.showMessageDialog(null,"Rezervarea nu a fost editata.","",JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null,"Rezervarea a fost editata.","",JOptionPane.INFORMATION_MESSAGE);
	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ReservationDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	public static void updatePrice(int id,int price) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		int insertedId = -1;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString1, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setInt(1,price);
			updateStatement.setInt(2, id);
			insertedId=updateStatement.executeUpdate();
			if(insertedId==0) {
				JOptionPane.showMessageDialog(null,"Nu se poate efectua plata partiala","",JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null,"Plata partiala a fost acceptata","",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ReservationDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	public static void delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		int insertedId = -1;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setInt(1, id);
			insertedId=deleteStatement.executeUpdate();
			if(insertedId==0)
				JOptionPane.showMessageDialog(null,"Rezervarea nu a fost stearsa.","",JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null,"Rezervarea a fost stearsa.","",JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ReservationDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	
	public static List<Object> getAllReservations() {
		List<Object> r = new ArrayList<Object>();
		try {
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement s = dbConnection.createStatement();
			ResultSet rs = s.executeQuery("select * from reservation");
			while (rs.next()) {
				Reservation cust = new Reservation();
				cust.setId(rs.getInt("id"));
				cust.setDestination(rs.getString("destination"));
				cust.setHotel(rs.getString("hotel"));
				cust.setNumber(rs.getInt("number"));
				cust.setPrice(rs.getInt("price"));
				cust.setClientId(rs.getInt("id_c"));
				cust.setDate(rs.getDate("final_date"));
				r.add(cust);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
}
