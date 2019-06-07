package uta_parking.model;

import java.util.Arrays;

public class ParkingSpot {
	String parkingAreaName,spot,floor;
	
	public ParkingSpot() {
		this.floor = "";
		this.parkingAreaName = "";
		this.spot="";
	}
	
	public ParkingSpot(String parkingAreaName, String spot, String floor) {
		this.parkingAreaName = parkingAreaName;
		this.spot = spot;
		this.floor = floor;
	}

	public String getParkingAreaName() {
		return parkingAreaName;
	}

	public void setParkingAreaName(String parkingAreaName) {
		this.parkingAreaName = parkingAreaName;
	}

	public String getSpot() {
		return spot;
	}

	public void setSpot(String spot) {
		this.spot = spot;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public ParkingSpotErrorMsgs validateParkingSpotDetails(ParkingSpot parkingSpot) {
		ParkingSpotErrorMsgs errors = new ParkingSpotErrorMsgs();
		errors.setAreaNameError(validateParkingAreaName(parkingSpot.getParkingAreaName()));
		errors.setFloorError(validateParkingFloor(parkingSpot.getFloor()));
		errors.setSpotError(validateParkingSpotNumber(parkingSpot.getSpot()));
		
		if(errors.getAreaNameError().length()!=0||errors.getFloorError().length()!=0||errors.getSpotError().length()!=0) {
			errors.setParkingSpotErrors("Errors Exist");
		}
		return errors;
	}

	private String validateParkingSpotNumber(String spot2) {
		String error = "";
		if(spot2.equals("")) {
			error = "Spot number is mandatory";
		}
		else if(spot2.equals("0")){
			error = "Spot number cannot be 0";
		}
		else if(!spot2.matches("^[0-9]{1,3}$")) {
			error = "Spot number should only be a number between 1-999";
		}
		return error;
	}

	private String validateParkingFloor(String floor2) {
		String error="";
		if(floor2.equals("")) {
			error = "Floor cannot be empty";
		}else if(!floor2.matches("^[1-9]$")) {
			error = "Floor number should only be a number between 1-9";
		}
		return error;
	}

	private String validateParkingAreaName(String parkingAreaName2) {
		String errors="";
//		****OLD CODE****
//		if(parkingAreaName2.equals("")){
//			errors = "Parking area Name cannot be empty";
//		}
		
		String[] validParkignAreas = {"West Garage", "Davis", "Maverick", "Neddermann"};
		
		if(parkingAreaName.length()==0) {
			errors = "Parking area name cannot be empty";			
		}
//		else if(parkingAreaName.matches(".*\\d+.*") ) {
//			result = "Parkign Area name cannot contain numbers";			
//		}
//		else if((parkingAreaName.matches(".*\\W+\\s+.*"))) {
//			result = "Parking Area name cannot contain special characters";			
//		}
//		else if((parkingAreaName.length() < 5) || (parkingAreaName.length() > 11)) {
//			result = "Parking area name lenght must be within 5 to 11";
//		}		
		else {
			boolean contains = Arrays.stream(validParkignAreas).anyMatch(parkingAreaName::equals);
			
			if(!contains) {
				errors = "Parking area name must be one of the following: West Garage or Maverick or Davis or Neddermann";				
			}			
		}
		
		return errors;
	}
	
	

}
