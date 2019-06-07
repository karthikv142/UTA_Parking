package uta_parking.model;

import java.io.Serializable;

public class SearchUserManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String user_name="";
	private String license_no="";
	private String phone;
	private String uta_id;
	private String role;
	private String email;
	private int noshow;
	private int overstay;
	private String type;
	private String status;
	private String reason;
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setUser(String user_name, String role, String uta_id, String phone, String email, String license_no, int noshow, int overstay, String type, String status, String reason) {
		setUser_name(user_name);
//		setUserPassword(password);
		setRole(role);
		setUta_id(uta_id);
		setPhone(phone);
		setEmail(email);
		setLicense_no(license_no);
		setNoshow(noshow);
		setOverstay(overstay);
		setType(type);
		setStatus(status);
		setReason(reason);
	}	
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}
	
	public String getStatus() {
		return status;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOverstay() {
		return overstay;		
	}	
	
	public void setOverstay(int overstay) {
		 this.overstay = overstay;
	}		

	public int getNoshow() {
		return noshow;		
	}	
	
	public void setNoshow(int noshow) {
		 this.noshow = noshow;		
	}	
	
	public String getEmail() {
		return email;		
	}	
	
	public void setEmail(String email) {
		this.email = email;		
	}	

	public String getLicense_no() {
		return license_no;
	}	
	
	public void setLicense_no(String license_no) {
		 this.license_no = license_no;		
	}	
	
	public String getPhone() {
		return phone;		
	}	
	
	public void setPhone(String phone) {
		this.phone = phone;		
	}	

	public void setUta_id(String uta_id) {
		this.uta_id = uta_id;		
	}
	
	public String getUta_id() {
		return uta_id;
	}

	public String getRole() {
		return role;		
	}	
	
	public void setRole(String role) {
		this.role =  role;		
	}

	public String getUser_name() {
		return user_name;		
	}		
	
	public void setUser_name(String user_name) {
		 this.user_name = user_name;		
	}	
	
	public String validateSearchUser(String user_name)
	{
		String result="";
//		********old code********
//		if (user_name.equals("") || user_name == null)
//			result="You must provide a username";
		
		if(user_name == "") {
			result = "Username cannot be empty";			
		}
		else if((user_name.matches(".*\\W+.*"))) {
			result = "Username cannot contain special characters";			
		}
		else if(user_name.length() < 8) {
			result = "Username too short: length must be within 8 to 16";
		}
		else if(user_name.length() > 16) {
			result = "Username too long: length must be within 8 to 16";
		}
		else if(!((user_name.matches("^[A-Za-z].*$")))) {
			result = "Username must start with an alphabet";			
		}			
		return result;		
	}
	
}