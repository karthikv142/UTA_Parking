package uta_parking.model;

public class ParkingAreaErrorMsgs {
	private String errorMsg="";
	private String parking_areanameError="";
	private String capacityError="";
	private String floorError="";
	private String typeError="";
	
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(String message) {
		
		errorMsg = message;
	}
	
	
	public String getParking_areanameError() {
		return parking_areanameError;
	}
	public void setParking_areanameError(String parking_areanameError) {
		this.parking_areanameError= parking_areanameError;
	}
	
	public String getCapacityError() {
		return capacityError;
	}
	
	public void setCapacityError(String capacityError) {
		this.capacityError= capacityError;
	}
	
	public String getFloorError() {
		return floorError;
	}
	public void setFloorError(String floorError) {
		this.floorError= floorError;
	}
	
	public String getTypeError() {
		return typeError;
	}
	public void setTypeError(String typeError) {
		this.typeError= typeError;
	}
	
	
}
