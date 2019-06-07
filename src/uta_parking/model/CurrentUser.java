package uta_parking.model;

public class CurrentUser {

	private String current_user_id="";
	private String current_username ="";
	private String current_utaID="";
	private String current_role="";
	
	
	
	

	public void setCurrent_user ( String current_user_id, String current_username ,String current_utaID,String current_role) {
		
		this.current_username=current_username;
		this.current_utaID=current_utaID;
		this.current_role=current_role;
		this.current_user_id = current_user_id;

	}
	
	
	public String getCurrent_user_id() {
		return current_user_id;
	}
	public void setCurrent_user_id(String current_user_id) {
		this.current_user_id = current_user_id;
	}
	
	public String getCurrent_username() {
		return current_username;
	}
	
	public void setCurrent_username(String current_username) {
		this.current_username = current_username;
	}
	public String getCurrent_utaID() {
		return current_utaID;
	}
	public void setCurrentUtaID(String current_utaID) {
		this.current_utaID = current_utaID;
	}
	
	public String getCurrent_role() {
		return current_role;
	}
	public void setCurrent_role(String current_role) {
		this.current_role = current_role;
	}

	

}
