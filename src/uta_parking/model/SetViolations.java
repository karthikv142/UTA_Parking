package uta_parking.model;

public class SetViolations {
	String user_name;
	String uta_id ;
	int noshow;
	int overstay;

	public String getUserName() {
	return user_name;
	}
	
	public void setUserName(String userName) {
	this.user_name = userName;
	}
	
	public String getutaId() {
		return uta_id;
	}
	
	public void setutaId(String utaId) {
		this.uta_id = utaId;
	}
	
	public int getNoShow() {
		return noshow;
	}
	
	public void setNoShow(int noshow) {
		this.noshow = noshow;
	}
	
	public int getOverStay() {
		return overstay;
	}
	
	public void setOverStay(int overstay) {
		this.overstay = overstay;
	}
}
