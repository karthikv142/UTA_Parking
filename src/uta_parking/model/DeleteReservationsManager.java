package uta_parking.model;

import java.io.Serializable;
import java.sql.Time;

public class DeleteReservationsManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
//	private String reserve_id="";
	private String reserve_id;
	
//	private String parking_area_name="";
	private String parking_area_name;
	
//	private String type="";
	private String type;
	
	private String floor;
	
	private static String spot_num;
	private String user_id;
	private static String start_time;
	private static String duration;
	
	private String cost;
	private String status;	
	
	private String cart;
	private String camera;
	private String history;	
	
	
	private String availability;
	
	public void deleteReservationsManager(String reserve_id, String parking_area_name, String type, String floor, String spot_num, String user_id, String start_time, String duration, String cost, String status, String cart, String camera, String history, String availability) {
		
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

	public String getReserve_id() {
		return reserve_id;
	}

	public void setReserve_id(String reserve_id) {
		this.reserve_id = reserve_id;
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

	public static String getSpot_num() {
		return spot_num;
	}

	public void setSpot_num(String spot_num) {
		DeleteReservationsManager.spot_num = spot_num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public static String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		DeleteReservationsManager.start_time = start_time;
	}

	public static String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		DeleteReservationsManager.duration = duration;
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

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	public DeleteReservationsManagerErrorMessages validateDeleteReservation() {
		DeleteReservationsManagerErrorMessages errors = new DeleteReservationsManagerErrorMessages();
		errors.setDuration(validateDuration(DeleteReservationsManager.getDuration()));
		errors.setSpot_num(validateSpot(DeleteReservationsManager.getSpot_num()));
		errors.setStart_time(validateTime(DeleteReservationsManager.getStart_time()));
		
		if(errors.getDuration()!=""||errors.getSpot_num()!=""||errors.getStart_time()!="") {
			errors.setErrorExist("The above Errors exist. Please correct them.");			
		}
		
		return errors;
	}

	public static String validateDuration(String duration) {
		String result = "";		
		
		if(duration.length() == 0) {
//		if(duration. == "") {
			result = "Duration cannot be empty";
			return result;
		}		
		
		if(duration.matches("^[a-zA-Z]*$")) {
			result = "Duration must be a number!";
			return result;			
		}
		
		int numDuration = Integer.parseInt(duration);
		
		if(((numDuration < 15) || (numDuration > 180))) {
			result = "Duration must be within 15 to 180 minutes";			
		}
		
		else if(numDuration % 15 != 0) {
			result = "Duration must be in multiples of 15";			
		}
		
//		else {
//			result = "Valid Duration";
//		}
		return result;
	}
	
	public static String validateSpot(String spot) {
		String result = "";
		
		if(spot.length() == 0) {
//		if(spot == "") {
			result = "Spot cannot be empty";
			return result;			
		}		
		
		if(spot.matches("^[a-zA-Z]*$") ) {
			result = "Spot must be a number!";
			return result;			
		}
		
		int numSpot = Integer.parseInt(spot);
		
		if(((numSpot < 1) || (numSpot > 250))) {
			result = "Spot must be within 1 to 250";			
		}	
/*		
		else {
			result = "Valid Spot";
		}*/
		return result;		
	}
	
	public static String validateTime(String time) {
//		String result = "In Time Validations";
		String result = "";
		
		if(time.length() == 0) {
//		if(time == "") {
			result = "Time cannot be empty";
			return result;			
		}		
		else if ((time.length()) != 8) {
			result = "Please enter time in the format of HH:MM:SS";
			return result;
		}
		
//		if(time.matches("^[a-zA-Z]*$") ) {
//			result = "Time must be a number!";
//			return result;			
//		}
		
		String numTime [] = time.split(":");
		
		String Shour = numTime[0];
		String Sminute = numTime[1];
		String Ssecond = numTime[2];
		
		if ((Shour.matches("^[a-zA-Z]*$")) || (Shour.matches(".*\\W+.*"))) {
//		if ((Shour.matches("^[a-zA-Z]*$"))) {
//		if ((Shour.matches(".*\\W+.*"))) {		
		
			result = "Hour must be a number";
			return result;
		}		
		
		else if ((Sminute.matches("^[a-zA-Z]*$")) || (Sminute.matches(".*\\W+.*"))) {
			result = "Minutes must be a number";
			return result;
		}
		
		else if ((Ssecond.matches("^[a-zA-Z]*$")) || (Ssecond.matches(".*\\W+.*"))) {
			result = "Seconds must be a number";
			return result;
		}	
		
		int hour = Integer.parseInt(numTime[0]);		
		int minute = Integer.parseInt(numTime[1]);		
		int second = Integer.parseInt(numTime[2]);
//		System.out.println("Hour : " + hour + " Minute: " + minute + " Second: " +second);
		
//		if((hour < 0) || (hour > 23)) {
		if((hour > 23)) {
			result = "Enter a valid hour";			
		}
//		else if((minute != 00) || (minute != 15) || (minute != 30) || (minute != 45)) {
//		else if((minute % 15 != 0) || (minute > 59)) {		
//		else if((minute % 15 != 0)) {
		
		else if(minute >= 60) {
			result = "Minutes must be in multiples of 15s and from 00 to 45";
		}
		else if (minute % 15 != 0) {
			result = "Minutes must be in multiples of 15s and from 00 to 45";
		}		
//		else if((second < 0) || (second > 59)) {
		else if(second > 59) {
			result = "Seconds must be less than 60";			
		}		
		return result;		
	}	
}