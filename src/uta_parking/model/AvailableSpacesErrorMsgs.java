package uta_parking.model;

public class AvailableSpacesErrorMsgs {
	
	String errorMsg,parkingTypeErrorMsg,timeErrorMsg;
	
	public AvailableSpacesErrorMsgs(){
		errorMsg = "";
		parkingTypeErrorMsg = "";
		timeErrorMsg = "";
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public String getParkingTypeErrorMsg() {
		return parkingTypeErrorMsg;
	}
	public void setParkingTypeErrorMsg(String parkingTypeErrorMsg) {
		this.parkingTypeErrorMsg = parkingTypeErrorMsg;
	}
	public String getTimeErrorMsg() {
		return timeErrorMsg;
	}
	public void setTimeErrorMsg(String timeErrorMsg) {
		this.timeErrorMsg = timeErrorMsg;
	}
	public void setErrorMsg(String eMsg) {
		this.errorMsg = eMsg;
	}

}
