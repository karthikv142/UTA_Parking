package uta_parking.model;

import java.io.Serializable;
import java.util.regex.Pattern;

public class SearchUserAdmin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private boolean errorExists;
	
	private String userName="";
	private String licenseNo="";
	private String phone;
	private String uta_id;
	private String role;
	private String email;
	private String status ="";
	private String reason="";
	
	public SearchUserAdmin() {
		
	}
	public SearchUserAdmin(String userName, String licenseNo, String phone, String uta_id, String role, String email, String status, String reason){
		this.userName = userName;
		this.licenseNo = licenseNo;
		this.email = email;
		this.reason= reason;
		this.role = role;
		this.phone = phone;
		this.uta_id = uta_id;
		this.status = status;
	}
	public SearchUserErrorMsgs validateUser(SearchUserAdmin user, SearchUserErrorMsgs errors) {
		errors = new SearchUserErrorMsgs();
		String error;
		errors.setLicenseError(user.validateLicenseNo(user.getLicenseNo()));
		errors.setRoleError(user.validateRole(user.getRole()));
		errors.setUserEmailError(user.validateEmail(user.getEmail()));
		errors.setPhoneError(user.validatePhone(user.getPhone()));
		errors.setReasonError(user.validateReason(user.getStatus(),user.getReason()));
		errors.setUserStatusError(user.validateStatus(user.getStatus(),user.getReason()));
		
		if(errors.getLicenseError().equals("")&&
				errors.getPhoneError().equals("")&&
				errors.getReasonError().equals("")&&
				errors.getRoleError().equals("")&&
				errors.getUserEmailError().equals("")&&
				errors.getUserStatusError().equals(""))
			error = "";
		else
			error = "Please check the following errors";
			
		errors.setError(error);
		return errors;
	}
	
	private String validateRole(String role2) {
		String error;
		
		if(role2.equals("")) 
			error = "Role cannot be empty";
		else
			error = "";
		return error;
	}
	
	private String validateStatus(String status2,String reason) {
		String result;
		
		if(status.equals("")) {
			result = "Kindly enter a status";
		}
		else if(status.equalsIgnoreCase("Active")&& !reason.equals(""))
			result = "No need to provide Reason";
		else if(!status.equalsIgnoreCase("Active")&& reason.equals(""))
			result = "Kindly state the reason";
		else
			result = "";
			
	
		return result;
	}
	
	public String validateLicenseNo(String licenseNo)
	{
		String result;
		if (licenseNo.equals(""))
			result="You must provide a license Number";
		else if (!(licenseNo.trim().length()==7))
			result = "License plate number should be exactly 7 characters";
		else if (!licenseNo.matches("[A-Z][A-Z][A-Z][0-9][0-9][0-9][0-9]"))
		    result = "License Plate number should be of the format AAANNNN";
		else
			result = "";
		return result;	
	}
	
	public String validateReason(String status,String reason)
	{
		String result;
	
		if(status.equals("")) {
			result = "Kindly enter a status";
		}
//		else if(status.equalsIgnoreCase("Active") && reason.equals("")) 
//			result = "";
		else if(status.equalsIgnoreCase("Active")&& !reason.equals(""))
			result = "No need to provide Reason";
		else if(!status.equalsIgnoreCase("Active")&& reason.equals(""))
			result = "Kindly state the reason";
		else
			result = "";
			
	
		return result;
	}
	
	public String validateEmail(String email)
	{
		String result;
		Pattern regex = Pattern.compile("^[a-zA-Z0-9.]+@[a-zA-Z0-9]+.edu$");
		
		
		if (email.equals(""))
			result="You must provide an email";
		else if (regex.matcher(email).find())
			result = "";
		else
			result = "Not a valid Email!! .edu domain only. Alphanumeric and . only";		
		return result;	
	}
	
	public String validatePhone(String phone)
	{
		String result;
		if (phone.equals(""))
			result="You must provide a phone";
		else if ((phone.length()==10))
			result = "";
		else
			result = "Enter Valid Phone Number";
			
		
		return result;	
	}
	
	public String getEmail() {
		return email;		
	}	
	
	public void setEmail(String email) {
		this.email = email;		
	}	
	
	public String getPhone() {
		return phone;		
	}	
	
	public void setPhone(String phone) {
		this.phone = phone;		
	}	

	public void setUta_id(String i) {
		this.uta_id = i;		
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}