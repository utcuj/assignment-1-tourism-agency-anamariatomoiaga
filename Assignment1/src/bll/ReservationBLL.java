package bll;

import java.util.List;
import java.util.NoSuchElementException;

import dao.ReservationDAO;
import model.Reservation;

public class ReservationBLL {
	public static Reservation findById(int id) {
		Reservation r = ReservationDAO.findById(id);
		if (r == null) {
			throw new NoSuchElementException("The reservation with id =" + id + " was not found!");
		}
		return r;
	}
	public static int insertReservation(Reservation r) {
		
		return ReservationDAO.insert(r); 
	}
	public static void updateReservation(int id,String hotel) {
		
		ReservationDAO.update(id,hotel); 
	}
	public static void updatePrice(int id,int price) {
		
		ReservationDAO.updatePrice(id,price); 
	}
	public static void deleteReservation(int id) {
		
		ReservationDAO.delete(id); 
	}
	public static List<Object> getAllReservations(){
		return ReservationDAO.getAllReservations();
	}
	
}
