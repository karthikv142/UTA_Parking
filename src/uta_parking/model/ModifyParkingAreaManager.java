package uta_parking.model;

import java.io.Serializable;
import java.sql.Time;

public class ModifyParkingAreaManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String reserve_id="";	
	private String parking_area_name="";
	private String type="";	
	private String floor;
	
	private String spot_num;
	private String user_id;
	private Time start_time;
	private String duration;
	
	private String cost;
	private String status;	
	
	private String cart;
	private String camera;
	private String history;	
	
	private String availability;
	
	private void modifyParkingArea(String reserve_id, String parking_area_name, String type, String floor, String spot_num, String user_id, Time start_time, String duration, String cost, String status, String cart, String camera, String history, String availability) {
	
		setReserve_id(reserve_id); 
		
		setParking_area_name(parking_area_name);
		setType(type);
		setFloor(floor); 
		
		setSpot_num(spot_num);
		setUser_id(user_id);
		setStart_time(start_time);
		setDuration(duration);
		
		setCost(cost);
		setStatus(status);
		
		setCart(cart);
		setCamera(camera);
		setHistory(history);		

		setAvailability(availability);
				 
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

	public String getSpot_num() {
		return spot_num;
	}

	public void setSpot_num(String spot_num2) {
		this.spot_num = spot_num2;
	}

	public String getReserve_id() {
		return reserve_id;
	}

	public void setReserve_id(String reserve_id) {
		this.reserve_id = reserve_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}