package uta_parking.model;

public class ReservationErrorMsgs {

	private String errorMsg;
	private String parking_area_nameError;
	private String parking_typeError;
	private String floorError;
	private String spot_numberError;
	private String options_idError;
	private String user_idError;
	private String start_timeError;
	private String durationError;
	private String costError;
	private String statusError;
	private String overlapError;
	private String maxForTheDayError;
	
	public ReservationErrorMsgs(){
	 this.errorMsg = "";
	 this.parking_area_nameError = "";
	 this.parking_typeError = "";
	 this.floorError = "";
	 this.spot_numberError = "";
	 this.options_idError = "";
	 this.user_idError = "";
	 this.start_timeError = "";
	 this.durationError = "";
	 this.costError = "";
	 this.statusError = "";
	 this.overlapError="";
	 this.maxForTheDayError="";
	}
	
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg() {
		
			if ( !parking_area_nameError.equals("") || 
				 !parking_typeError.equals("") || 
				 !floorError.equals("") || 
				 !spot_numberError.equals("") || 
				 !options_idError.equals("") || 
				 !user_idError.equals("") || 
				 !start_timeError.equals("") || 
				 !durationError.equals("") || 
				 !costError.equals("") || 
				 !statusError.equals(""))
				
				this.errorMsg="Please correct the following errors";
		
					
	}
	
	
	
	public String getParking_are_nameError() {
		return parking_area_nameError;
	}
	public void setParking_area_nameError(String parking_area_nameError) {
		this.parking_area_nameError = parking_area_nameError;
	}
	public String getParking_typeError() {
		return parking_typeError;
	}
	public void setParking_typeError(String parking_typeError) {
		this.parking_typeError = parking_typeError;
	}
	public String getFloorError() {
		return floorError;
	}
	public void setFloorError(String floorError) {
        this.floorError = floorError;
	}
	
	public String getSpot_numberError() {
		return spot_numberError;
	}
	public void setSpot_numberError(String spot_numberError) {
        this.spot_numberError = spot_numberError;
	}
	
	public String getOptions_idError() {
		return options_idError;
	}
	public void setOptions_idError(String options_idError) {
		this.options_idError =  options_idError;
	}
	
	public String getUser_idError() {
		return user_idError;
	}
	public void setUser_idError(String user_idError) {
		this.user_idError =  user_idError;
	}
	
	public String getStart_timeError() {
		return start_timeError;
	}
	public void setStart_timeError(String start_timeError) {
		this.start_timeError = start_timeError;
	}
	
	public String getDurationError() {
		return durationError;
	}
	public void setDurationError(String durationError) {
		this.durationError = durationError;
	}
	
	public String getCostError() {
		return costError;
	}
	public void setCostError(String costError) {
		this.costError = costError;
	}
	
	public String getStatusError() {
		return statusError;
	}
	public void setStatusError(String statusError) {
		this.statusError = statusError;
	}
	public String getOverlapError() {
		return overlapError;
	}
	public void setOverlapError(String overlapError) {
		this.overlapError = overlapError;
	}
	public String getMaxForTheDayError() {
		return maxForTheDayError;
	}
	public void setMaxForTheDayError(String maxForTheDayError) {
		this.maxForTheDayError = maxForTheDayError;
	}

}