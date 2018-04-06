package model;

public class Client {
	private int id;
	private String name;
	private int card;
	private String cnp;
	private String address;
	public Client(int id, String name, int card, String cnp, String address) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
		this.card=card;
		this.cnp=cnp;
		this.address=address;
	}
	public Client(String name, int card, String cnp, String address) {
		// TODO Auto-generated constructor stub
		
		this.name=name;
		this.card=card;
		this.cnp=cnp;
		this.address=address;
	}
	public Client() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCard() {
		return card;
	}
	public void setCard(int card) {
		this.card = card;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String toString() {
		return "Name: " +name + "    Card: " +card+ "    Cnp: " + cnp+ "    Address: " +address;
	}
	
}
