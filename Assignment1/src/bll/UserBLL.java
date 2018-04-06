package bll;

import java.util.List;

import dao.UserDAO;
import model.User;

public class UserBLL {
	public static int findUser(String username, String password, boolean a) {
		return UserDAO.findUser(username, password, a);
	}
	public static int insertUser(User u) {
		return UserDAO.insert(u);
	}
	public static void deleteUser(int id) {
		UserDAO.delete(id);
	}
	public static void updateUser(int id, String address) {
		UserDAO.update(id, address);
	}
	public static List<Object> getAllUsers() {
		return UserDAO.getAllUsers();
	}
}
