package uta_parking.model;

public class AvailableSpaces {

	String parkingType="";
	
	
	
	public AvailableSpaces() {
	}
	
	public AvailableSpaces(String parkingType) {
		this.parkingType = parkingType;
	}



	public String getParkingType() {
		return parkingType;
	}

	public void setParkingType(String parkingType) {
		this.parkingType = parkingType;
	}

	public String validateParkingType(String parkingType) {
		String result = "";
		if(parkingType == "") {
			result = "Parking Type is a mandatory Field!!";			
		}
		return result;
	}
}
