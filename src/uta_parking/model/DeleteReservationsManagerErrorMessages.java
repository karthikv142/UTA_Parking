package uta_parking.model;

public class DeleteReservationsManagerErrorMessages {
	
	String durationError, spotError, timeError;
	
	public DeleteReservationsManagerErrorMessages() {
		durationError = "";		
		spotError = "";
		timeError = "";
		errorExist = "";
	}
	
//	String errorExist="";
	String errorExist;
	
	public DeleteReservationsManagerErrorMessages(String duration, String spot_num, String start_time) {
		this.durationError = duration;
		this.spotError = spot_num;
		this.timeError = start_time;		
	}
	
//	public ModifyParkingAreaErrorMessages validateModifyParkingArea(ModifyParkingArea modifyArea) {
//		//errorExist=false;
//		ModifyParkingAreaErrorMessages errors = new ModifyParkingAreaErrorMessages();
//		errors.setErrorMessages("");
//		
//		errors.setDurationError(modifyArea.validateDuration(modifyArea.getDuration()));
//		errors.setSpotError(modifyArea.validateSpot(modifyArea.getSpot_num()));
//		errors.setTimeError(modifyArea.validateTime(modifyArea.getStart_time()));		
//		
//		if((errors.getDurationError() != "") || (errors.getSpotError() != "") || (errors.getTimeError() != "")) {
//			errors.setErrorMessages("Please have a look at the following errors");
//			System.out.println("Errors exist");
//			System.out.println("Duration error: " + errors.getDurationError() );
//			System.out.println("Spot error: " + errors.getSpotError());
//			System.out.println("Time error: " + errors.getTimeError());			
//		}
//		return errors;
//	}
	
	public String getDuration() {
		return durationError;
	}

	public void setDuration(String duration) {
		this.durationError = duration;
	}
	
	public String getSpot_num() {
		return spotError;
	}

	public void setSpot_num(String spot_num) {
		this.spotError = spot_num;
	}
	
	public String getStart_time() {
		return timeError;
	}

	public void setStart_time(String start_time) {
		this.timeError = start_time;
	}

	public String getErrorExist() {
		return errorExist;
	}

	public void setErrorExist(String errorExist) {
		this.errorExist = errorExist;
	}
}