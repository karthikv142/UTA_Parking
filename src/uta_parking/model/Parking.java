package uta_parking.model;

import java.io.Serializable;

public class Parking implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String parking_area_name;
	private String parking_type;
	private String floor;
	private String capacity;
	private String availability;
	private String cart;
	private String camera;
	private String history;
	
	//Constructor
	public Parking() {
		this.parking_area_name="";
		this.parking_type="";
		this.floor="";
		this.capacity="";
		this.availability="";
		this.cart="";
		this.camera="";
		this.history="";
	}
	
	public void setParking (String parking_area_name,String parking_type, String floor, String capacity,
							String availability, String cart, String camera, String history) {
		this.parking_area_name=parking_area_name;
		this.parking_type=parking_type;
		this.floor=floor;
		this.capacity=capacity;
		this.availability=availability;
		this.cart=cart;
		this.camera=camera;
		this.history=history;
		
	}
	
	public String getParking_area_name() {
		return parking_area_name;
	}
	
	public void setParking_area_name(String parking_area_name) {
		this.parking_area_name = parking_area_name;
	}
	
	public String getParking_type() {
		return parking_type;
	}
	
	public void setParking_type(String parking_type) {
		this.parking_type = parking_type;
	}
	
	public String getFloor() {
		return floor;
	}
	
	public void setFloor(String floor) {
		this.floor = floor;
	}
	
	public String getCapacity() {
		return capacity;
	}
	
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	public String getAvailability() {
		return availability;
	}
	
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	public String getCart() {
		return cart;
	}
	
	public void setCart(String cart) {
		this.cart = cart;
	}
	
	public String getCamera() {
		return camera;
	}
	
	public void setCamera(String camera) {
		this.camera = camera;
	}
	
	public String getHistory() {
		return history;
	}
	
	public void setHistory(String history) {
		this.history = history;
	}
	

	
}







