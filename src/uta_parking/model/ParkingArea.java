package uta_parking.model;

import java.util.regex.Pattern;

public class ParkingArea {
	
	String parkingAreaName,basic,midRange,premium,access,camera,history,cart,floor,capacity;
	public ParkingArea(){
		parkingAreaName = "";
		basic="";
		midRange= "";
		premium="";
		access="";
		camera="";
		history="";
		cart="";
		floor="";
		capacity="";
	}
	private boolean errorExists;
	public ParkingArea(String parkingAreaName,
			String basic,
			String midRange,
			String premium, 
			String access,
			String camera,
			String history,
			String cart,
			String floor,
			String capacity){
		this.parkingAreaName = parkingAreaName;
		this.basic = basic;
		this.midRange = midRange;
		this.access = access;
		this.camera=camera;
		this.premium=premium;
		this.history=history;
		this.floor=floor;
		this.cart=cart;
		this.capacity = capacity;
	}
	
	public ParkingAreaErrorMsgs validateParkingArea(ParkingArea editedParking) {		
		ParkingAreaErrorMsgs errors = new ParkingAreaErrorMsgs();
		errors.setParking_areanameError(editedParking.validateArea(editedParking.getParkingAreaName()));
		errors.setCapacityError(editedParking.validateCapacity(editedParking.getCapacity()));
		errors.setFloorError(editedParking.validateFloor(editedParking.getFloor()));
		errors.setTypeError(editedParking.validateType(editedParking));
		String error;
		if(errors.getCapacityError().equals("")&& errors.getFloorError().equals("")&& errors.getTypeError().equals("")&&errors.getParking_areanameError().equals("")) 
			error = "";
		else
			error = "Please check the following errors";
		
		errors.setErrorMsg(error);
		return errors;
	}
	
	public String validateCapacity(String capacity)
	{
		String result="";
		if (capacity.equals(""))
			result="You must provide a Value.";
		else
			result = "";
		return result;	
	}
	
	public String validateArea(String areaname)
	{
		String result="";		
		if (areaname.equals(""))
			result="You must provide a Parking Area Name";
		else
			result = "";
		return result;	
	}	
	
	public String validateFloor(String floor)
	{
		String result="";
		if (floor.equals(""))
			result="You must provide a Value.";
		else
			result = "";
		return result;	
	}
	public String validateType(ParkingArea editedParking)
	{
		String result="";
		if(editedParking.getBasic().equals("0")&& editedParking.getAccess().equals("0")&& editedParking.getMidRange().equals("0") && editedParking.getPremium().equals("0")) {
			result = "Please select the following options";
		}
		else
			result = "";
		return result;
	}
	

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getParkingAreaName() {
		return parkingAreaName;
	}

	public void setParkingAreaName(String parkingAreaName) {
		this.parkingAreaName = parkingAreaName;
	}

	public String getBasic() {
		return basic;
	}

	public void setBasic(String basic) {
		this.basic = basic;
	}

	public String getMidRange() {
		return midRange;
	}

	public void setMidRange(String midRange) {
		this.midRange = midRange;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
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

	public String getCart() {
		return cart;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}
	

}
