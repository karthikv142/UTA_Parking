package uta_parking.model;

public class ParkingSpotErrorMsgs {
	public String getNoSpotsInDB() {
		return NoSpotsInDB;
	}

	public void setNoSpotsInDB(String noSpotsInDB) {
		NoSpotsInDB = noSpotsInDB;
	}

	String areaNameError;
	String spotError;
	String floorError;
	String parkingSpotErrors;
	String NoSpotsInDB="";
	
	public String getParkingSpotErrors() {
		return parkingSpotErrors;
	}

	public void setParkingSpotErrors(String parkingSpotErrors) {
		this.parkingSpotErrors = parkingSpotErrors;
	}

	public ParkingSpotErrorMsgs(){
		areaNameError = "";
		spotError = "";
		floorError = "";
		parkingSpotErrors ="";
	}

	public String getAreaNameError() {
		return areaNameError;
	}

	public void setAreaNameError(String areaNameError) {
		this.areaNameError = areaNameError;
	}

	public String getSpotError() {
		return spotError;
	}

	public void setSpotError(String spotError) {
		this.spotError = spotError;
	}

	public String getFloorError() {
		return floorError;
	}

	public void setFloorError(String floorError) {
		this.floorError = floorError;
	}
}
