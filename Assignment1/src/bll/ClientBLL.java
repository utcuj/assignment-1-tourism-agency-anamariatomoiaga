package bll;

import java.util.List;
import java.util.NoSuchElementException;

import dao.ClientDAO;
import model.Client;


public class ClientBLL {
	public static int insertClient(Client client) {
		
		return ClientDAO.insert(client); 
	}
	public static void updateClient(int id,String address) {
		
		ClientDAO.update(id,address); 
	}
	public static int deleteClient(int id) {
		
		return ClientDAO.delete(id); 
	}
	public static Client findClientById(int id) {
		Client c = ClientDAO.findById(id);
		if (c == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return c;
	}
	public static List<Object> getAllClients(){
		return ClientDAO.getAllClients();
	}
	public static List<Object> getClients(){
		return ClientDAO.getClients();
	}
}
