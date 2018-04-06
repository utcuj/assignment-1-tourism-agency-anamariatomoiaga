package model;

public class User {
	private int id;
	private String name;
	private String address;
	private String username;
	private String password;
	private Boolean admin;
	public User(String name, String address, String username, String password, Boolean admin) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.address=address;
		this.username=username;
		this.password=password;
		this.admin=admin;
	}
	public User() {
		
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
