package uta_parking.model;

import java.io.Serializable;

import uta_parking.data.ReservationDAO;
import uta_parking.data.UserDAO;

public class Reservation implements Serializable{

	private static final long serialVersionUID = 3L;
	
	private String reservationID;
	private String user_id;
	private String date;
	private String parking_area_name;
	private String parking_type;
	private String floor;
	private String spot_number;
	private String user_name;
	private String start_time;
	private String duration;
	private String cost;
	private String status;
	private String camera_option;
	private String cart_option;
	private String history_option;
	
	public Reservation(){
	
	 this.reservationID = "";
	 this.user_id = "";
	 this.date = "";
	 this.parking_area_name = "";
	 this.parking_type = "";
	 this.floor = "";
	 this.spot_number = "";
	 this.user_name = "";
	 this.start_time = "";
	 this.duration = "";
	 this.cost = "";
	 this.status = "";
	 this.camera_option="";
	 this.cart_option="";
	 this.history_option="";
	 
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setReservation(String parking_area_name,String parking_type, String floor, String spot_number
			, String user_name, String start_time, String duration, String cost, String status, String camera_option,
			String  cart_option, String history_option) {

		setParking_area_name(parking_area_name);
		setParking_type(parking_type);
		setFloor(floor);
		setSpot_number(spot_number);
		setUser_name(user_name);
		setStart_time(start_time);
		setDuration(duration);
		setCost(cost);
		setStatus(status);
		setCamera_option(camera_option);
		setCart_option(cart_option);
		setHistory_option(history_option);
		
	}
	
	
	public String getReservationID()
	{return reservationID;}
	
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getParking_area_name() {
		return parking_area_name;
	}
	public void setParking_area_name(String parking_area_name) {
		this.parking_area_name = parking_area_name;
	}
	public String getParking_type() {
		return parking_type;
	}
	public void setParking_type(String parking_type) {
		this.parking_type = parking_type;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
        this.floor = floor;
	}
	
	public String getSpot_number() {
		return spot_number;
	}
	public void setSpot_number(String spot_number) {
        this.spot_number = spot_number;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name =  user_name;
	}
	
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
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
	
	public String getCamera_option() {
		return camera_option;
	}
	public void setCamera_option(String camera_option) {
		this.camera_option = camera_option;
	}
	public String getCart_option() {
		return cart_option;
	}
	
	public void setCart_option(String cart_option) {
		this.cart_option = cart_option;
	}
	
	public String getHistory_option() {
		return history_option;
	}
	
	public void setHistory_option(String history_option) {
		this.history_option = history_option;
	}
	
	
	

	public void validateReservation (Reservation reservation, ReservationErrorMsgs errorMsgs, String currentUserID, int currentHours, int currentMinutes) {

		
			errorMsgs.setParking_typeError(validateParking_type(reservation.getParking_type(),currentUserID));
			
			errorMsgs.setStart_timeError(validateStart_time(reservation.getStart_time(), currentHours, currentMinutes));
			
			errorMsgs.setDurationError(validateDuration(reservation.getDuration(),reservation.getStart_time()));			
			errorMsgs.setErrorMsg();
			

		
	}
	
	public void validateConfirmedReservation (Reservation confirmReservation, ReservationErrorMsgs confirmErrorMsg, CurrentUser CurrentUser) {
		

		if(ReservationDAO.maxedForTheDay(CurrentUser.getCurrent_username()))
			confirmErrorMsg.setMaxForTheDayError( "You have reached the maximum of 3 reservations for today.");
		
		else 
			if (ReservationDAO.hasOverlappingReservations(confirmReservation.getUser_name(),confirmReservation.getStart_time(), confirmReservation.getDuration()))
				confirmErrorMsg.setOverlapError("You cannot have overlapping reservations.");
		
	}
	
	private String validateParking_type(String parking_type, String current_user_utaID) {
		
		String result ="";
		
		String currentUserParkingType ="";

		//Get current user parking type
		currentUserParkingType = UserDAO.getUserParkingType(current_user_utaID);
		
		//For debugging:
		//System.out.println("Current user's parking type: " + currentUserParkingType);
		
		if(currentUserParkingType.contentEquals("basic") && 
				(parking_type.equals("premium") || parking_type.equals("midrange")|| parking_type.equals("access")))
				{result ="Basic permit holders can only reserve basic parking areas.";}
		
		else if (currentUserParkingType.contentEquals("midrange") && 
				(parking_type.equals("premium")|| parking_type.equals("access")))
				{result ="Midrange permit holders can only reserve basic or midrange parking areas.";}
		
		else if (currentUserParkingType.contentEquals("premium") && parking_type.equals("access"))
				{result ="Access parking areas are exclusively reserved for Access permit holders only.";}
		
		else if (currentUserParkingType.contentEquals("access") && 
				(parking_type.equals("premium")|| parking_type.equals("midrange") || parking_type.equals("basic")))
				{result ="Access parking holders can only reserve access parking areas.";}

		return result;
	}

	private String validateStart_time(String start_time, int currentHours, int currentMinutes) {
String result="";	
		
		/* START TIME RULES
		*	- CANNOT RESERVE Less than 15min before start time
		*	- TIME starts by the hour and is in 15 min increments
		*/
		
		
		/*
		//for debugging
		System.out.println("user hours:" + hours);
		System.out.println("system hours:" + currentHours);
		System.out.println("user minutes:" + minutes);
		System.out.println("system minutes:" + currentMinutes);
		*/
		

		if (start_time.isEmpty())
			result="You must provide a start time.";
		
		else
			
		{
			//Split into hours and minutes from user
			String hours_minutes[] = start_time.split(":");
			int hours = Integer.parseInt(hours_minutes[0]);
			int minutes = Integer.parseInt(hours_minutes[1]);
			
			//For calculations
			int getInterval = ((hours*60)+ minutes) - ((currentHours*60)+ currentMinutes);
			
			if (hours < currentHours || ((hours == currentHours) && minutes< currentMinutes))
				result="Reservation cannot start at/before the current time.";
			
			else 
				if (getInterval < 15)
					result="Start time cannot be less than 15mins from current time.";	
		}
		return result;
	
	
	}

	
	private String validateDuration(String duration, String start_time) {
		
			String result="";
			int min = 15;
			int max = 180;

				/*  Duration rules are :
				 * 	- minimum is 15mins
				 *	- maximum is : 3hrs
				 *	- in increments of 15mins : duration number % 15 = 0
				 *	- duration should not span over next day i.e : startTime + duration <= 00:00 of the next day
				 *  
				 */
		if (!start_time.isEmpty())		
			if (duration.equals(""))
				result="You must provide a duration!";
			else
				if (!isTextAnInteger(duration))
					result="Duration of the reservation must be a number!";
				else 
				{

					//Split into hours and minutes from user
					String hours_minutes[] = start_time.split(":");
					long hours = Integer.parseInt(hours_minutes[0]);
					long minutes = Integer.parseInt(hours_minutes[1]);
					
					final long multiplier = 60;
					
					long totalMinutes = minutes + hours * multiplier;
					long _duration = Long.parseLong(duration);
					
					if (!isBetween(_duration, min,max))
						result="Minimum is 15minutes. The maximum 180(3hours)";
					
					else {
						
						if (!isDivisibleBy(_duration, min))
							result="Duration must be in intervals of 15mins";
						
						else 
							if(!maxDuration(totalMinutes, _duration))
								result="Start time + duration cannot exceed midnight.";

					}
					
				}
			return result;		
		}


	

	//	This section is for general purpose methods used internally in this class	
	
/*	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
*/

	private boolean isTextAnInteger (String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}
	
	private boolean maxDuration(long start_time, long _duration) {
		boolean result;
		//1440 is the minutes there are in a single day
		
		if (start_time + _duration >1440) result = false;
		else result= true;
				
		return result;
	}
			
	private boolean isDivisibleBy (long number, int divisor) {
        boolean result;
		
        if ((number % divisor) == 0) result = true;
        else result =false;
        
		return result;
	}
	
	private boolean isBetween(long _duration, int min,int max)
	{
		 boolean result;
			
	        if (_duration < min || _duration > max ) result = false;
	        else result =true;
	        
			return result;
		
	}
	

	//add functions to delete reservations the next day at 00:02
	
}