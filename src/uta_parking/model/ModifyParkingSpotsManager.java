package uta_parking.model;

import java.io.Serializable;

public class ModifyParkingSpotsManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String reserve_id = "";
	private String parking_area_name = "";
	private String type = "";
	private String floor = "";
	
	private String spot_num = "";	
	
	private String capacity = "";
	private String availability = "";
	private String cart = "";
	private String camera = "";
	
	private String history = "";
	
	public void modifyParkingSpotsManager(String reserve_id, String parking_area_name, String type, String floor, String spot_num, String availability, String cart, String camera, String history) {
		setReserve_id(reserve_id);
		setParking_area_name(parking_area_name);
		setType(type);
		setFloor(floor);
//		setCapacity(capacity);		
		setSpot_num(spot_num);
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

	public String getReserve_id() {
		return reserve_id;
	}

	public void setReserve_id(String reserve_id) {
		this.reserve_id = reserve_id;
	}

	public String getSpot_num() {
		return spot_num;
	}

	public void setSpot_num(String spot_num) {
		this.spot_num = spot_num;
	}
}