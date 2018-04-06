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
import model.User;


public class UserDAO {
	protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
	private final static String findUser = "SELECT * FROM user where admin = ?";
	private static final String insertStatementString = "INSERT INTO user (name,address,username,parola,admin)"
			+ " VALUES (?,?,?,?,?)";
	private static final String updateStatementString = "UPDATE user" +" set address=? where id=?";
	private static final String deleteStatementString = "DELETE FROM user where id = ?";
		
	public static int findUser(String username, String password, boolean a) {
		
		int toReturn = 0;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findUser);
			findStatement.setBoolean(1, a);
			rs = findStatement.executeQuery();
						
			while(rs.next()){
                if(username.equals(rs.getString("username")) && password.equals(rs.getString("parola"))){
                	toReturn=1;
                }
           
                    
            }
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"UserDAO:findUser " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static int insert(User u) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, u.getName());
			insertStatement.setString(2, u.getAddress());
			insertStatement.setString(3, u.getUsername());
			insertStatement.setString(4, u.getPassword());
			insertStatement.setBoolean(5, u.getAdmin());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
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
			updateStatement.setString(1,address);
			updateStatement.setInt(2, id);
			insertedId=updateStatement.executeUpdate();
			if(insertedId==0) {
				JOptionPane.showMessageDialog(null,"Nu se poate edita","",JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null,"Userul a fost editat","",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:update " + e.getMessage());
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
				JOptionPane.showMessageDialog(null,"Userul nu a fost stears.","",JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null,"Userul a fost stears.","",JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	
	public static List<Object> getAllUsers() {
		List<Object> r = new ArrayList<Object>();
		try {
			Connection dbConnection = ConnectionFactory.getConnection();
			Statement s = dbConnection.createStatement();
			ResultSet rs = s.executeQuery("select * from user where admin = false");
			while (rs.next()) {
				User cust = new User();
				cust.setId(rs.getInt("id"));
				cust.setName(rs.getString("name"));
				cust.setAddress(rs.getString("address"));
				cust.setUsername(rs.getString("username"));
				cust.setPassword(rs.getString("parola"));
				cust.setAdmin(rs.getBoolean("admin"));
				r.add(cust);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	


}
