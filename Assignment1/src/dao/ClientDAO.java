package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Client;

public class ClientDAO {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO clienti (nume,card,cnp,address)"
			+ " VALUES (?,?,?,?)";
	private static final String updateStatementString = "UPDATE clienti" +" set address=? where id=?";
	private static final String deleteStatementString = "DELETE FROM clienti where id = ?";
			
	private final static String findStatementString = "SELECT * FROM clienti where id = ?";

	public static Client findById(int Id) {
		Client toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1,Id);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("nume");
			int card = rs.getInt("card");
			String cnp = rs.getString("cnp");
			String address = rs.getString("address");
			
			
			toReturn = new Client(Id, name,card,cnp, address);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static int insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, client.getName());
			insertStatement.setInt(2, client.getCard());
			insertStatement.setString(3, client.getCnp());
			insertStatement.setString(4, client.getAddress());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	public static void update(int id,String address) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		int insertedId = -1;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1, address);
			updateStatement.setInt(2, id);
			insertedId=updateStatement.executeUpdate();
		
			if(insertedId==0)
				JOptionPane.showMessageDialog(null,"Clientul nu a fost editat.","",JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null,"Clientul a fost editat.","",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	public static int delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		int insertedId = -1;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
			insertedId=1;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	public static List<Object> getAllClients() {
		List<Object> client = new ArrayList<Object>();
		try {
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement s = dbConnection.createStatement();
			ResultSet rs = s.executeQuery("select * from clienti");
			while (rs.next()) {
				Client cust = new Client();
				cust.setId(rs.getInt("id"));
				cust.setName(rs.getString("nume"));
				cust.setCard(rs.getInt("card"));
				cust.setCnp(rs.getString("cnp"));
				cust.setAddress(rs.getString("address"));
					
				client.add(cust);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}
	public static List<Object> getClients() {
		List<Object> client = new ArrayList<Object>();
		try {
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement s = dbConnection.createStatement();
			ResultSet rs = s.executeQuery("select * from reservation where curdate() > final_date");
			while (rs.next()) {
				int id_c = rs.getInt("id_c");
				Client c = ClientDAO.findById(id_c);
				client.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return client;
	}
	
}
