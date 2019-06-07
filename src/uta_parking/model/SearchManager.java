package uta_parking.model;

import java.io.Serializable;
import java.util.regex.Pattern;

public class SearchManager implements Serializable{
private static final long serialVersionUID = 1L;
	
	private boolean errorExists;
	
	private String userName="";
	private String phone;
	private String uta_id;
	private String email;
	private String password;
	
	public SearchManager() {
		
	}
	public SearchManager(String userName, String phone, String uta_id, String email, String password){
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.uta_id = uta_id;
		this.password = password;
	}
	public SearchManagerErrorMsgs validateUser(SearchManager editedUser, SearchManagerErrorMsgs errors) {
		errorExists=false;
		errors = new SearchManagerErrorMsgs();
		errors.setError("");
		errors.setUserEmailError(editedUser.validateEmail(editedUser.getEmail()));
		errors.setPhoneError(editedUser.validatePhone(editedUser.getPhone()));
		errors.setPasswordError(editedUser.validatePassword(editedUser.getPassword()));
		if(errors.getPhoneError().length()!=0||errors.getUserEmailError().length()!=0|| errors.getPasswordError().length()!=0) 
		{
			errors.setError("Please check the following errors");
//			System.out.println("errors exists");
//			System.out.println(errors.getPhoneError()+ " "+ errors.getUserEmailError()+" "+errors.getPasswordError());
		}
		return errors;
	}
//	
//	public String validateSearchUser(String user_name)
//	{
//		String result="";
//		
//		Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
//
//		if (regex.matcher(user_name).find())
//		    result = "User Name Cannot contain special characters";
//		
//		
//		if (user_name.equals(""))
//			result="You must provide a username";
//	System.out.println(result);
//		return result;	
//	}
//	
	public String validateEmail(String email)
	{
		String result;
		Pattern regex = Pattern.compile("^[a-zA-Z0-9.]+@[a-zA-Z0-9]+.edu$");
		
		
		if (email.length()==0)
			result="You must provide an email";
		else if (regex.matcher(email).find())
			result = "";
		else
			result = "Not a valid Email!! .edu domain only. Alphanumeric and . only";		
		return result;	
	}
	
	public String validatePhone(String phone)
	{
		String result="";
		if (phone.length()==0)
			result="You must provide a phone";
		else
		if (!(phone.length()==10))
		    result = "Enter Valid Phone Number";
//		System.out.println(result);
		return result;	
	}
//	public String validateUtaID(String UtaID)
//	{
//		String result="";
//		Pattern regex = Pattern.compile(".*[0-9]*.{10}+");	
//		System.out.println(UtaID);
//		if (UtaID.equals("") || UtaID == null)
//			result="You must provide a UTA_ID";
//		
//		if (!regex.matcher(UtaID).find())
//		    result = "Enter Valid UTA_ID Number";
//		System.out.println(result);
//		return result;	
//	}
	public String validatePassword(String password) {
		
		String result="";
		if (password.length()==0)
			result="You must provide a password";
//		System.out.println(result);
		return result;	
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
