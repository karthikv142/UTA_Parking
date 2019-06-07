package uta_parking.model;

import java.io.Serializable;

public class DeletePastReservations implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String parking_area_name="";
	private String type="";
	
	private String floor;
	private String capacity;
	private String availability;
	private String cart;
	private String camera;
	private String history;
	
	private void deletePastReservations(String parking_area_name, String type, String floor, String capacity, String availability, String cart, String camera, String history) {
		setParking_area_name(parking_area_name);
		setType(type);
		setFloor(floor); 
		setCapacity(capacity);
		setAvailability(availability);
		setCart(cart);
		setCamera(camera);
		setHistory(history);		 
	}

	public String getParking_area_name() {
		return parking_area_name;
	}

	public void setParking_area_name(String parking_area_name) {
		this.parking_area_name = parking_area_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor2) {
		this.floor = floor2;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity2) {
		this.capacity = capacity2;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability2) {
		this.availability = availability2;
	}

	public String getCart() {
		return cart;
	}

	public void setCart(String cart2) {
		this.cart = cart2;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera2) {
		this.camera = camera2;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history2) {
		this.history = history2;
	}

}