package model;


import java.util.Date;

public class Reservation {
	private int id;
	private String destination;
	private String hotel;
	private int number;
	private int price;
	private int clientId;
	private Date date;
	
	public Reservation() {
		
	}
	
	public Reservation(int id,String destination, String hotel, int number, int price, int clientId, Date date) {
		this.id=id;
		this.destination=destination;
		this.hotel=hotel;
		this.number=number;
		this.price=price;
		this.clientId=clientId;
		this.setDate(date);
	}
	public Reservation(String destination, String hotel, int number, int price, int clientId, Date date) {
		this.destination=destination;
		this.hotel=hotel;
		this.number=number;
		this.price=price;
		this.clientId=clientId;
		this.setDate(date);
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String toString() {
		return "Destination: " +destination+ "    Hotel:" +hotel+ "    Number:"+number+ "    Price:" +price+ "    Date:"+date;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
