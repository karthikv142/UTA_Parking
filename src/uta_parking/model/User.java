package uta_parking.model;

import java.io.Serializable;
import uta_parking.data.UserDAO;

public class User implements Serializable {
	
	// USE FOR CHECKING REGEX : https://www.regextester.com/

	private static final long serialVersionUID = 2L;
	private String username ="";
	private String utaID="";
	private String role="";
	private String password="";
	private String phone="";
	private String email="";
	private String license_plate="";
	


	public void setUser ( String username ,String utaID,String role,
							String password,String phone,String email,String license_plate) {
		
		this.username=username;
		this.utaID=utaID;
		this.role= role;
		this.password=password;
		this.phone=phone;
		this.email=email;
		this.license_plate=license_plate;

	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUtaID() {
		return utaID;
	}
	public void setUtaID(String utaID) {
		this.utaID = utaID;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLicense_plate() {
		return license_plate;
	}
	public void setLicense_plate(String license_plate) {
		this.license_plate = license_plate;
	}
	
	
	public void validateUser (User user, UserErrorMsgs errorMsgs) {
	
		errorMsgs.setUsernameError(validateUsername(user.getUsername()));
		errorMsgs.setUtaIDError(validateUtaID(user.getUtaID()));
		errorMsgs.setRoleError(validateRole(user.getUtaID(), user.getRole()));
		errorMsgs.setPasswordError(validatePassword(user.getPassword()));
		errorMsgs.setPhoneError(validatePhone(user.getPhone()));
		errorMsgs.setEmailError(validateEmail(user.getEmail()));
		errorMsgs.setLicense_plateError(validateLicense_plate(user.getLicense_plate(),user.getRole()));
		
		errorMsgs.setErrorMsg();
	}

	
	
	/*	Username rules:
	 * 	- Username is unique in database
	 * 	- Only _ and alphanumeric characters (lowercase for letters).
	 *	- Cannot be empty or null.
	 *	- Character length is 8 min 16 max.
	 *	- Must start with a letter.
	 * */
	private String validateUsername (String username) {
		String result="";
		String usernameRegex ="^[a-z][a-z0-9_]+$";
		if (username.equals(""))
			result="You must provide a username";
		else
			if (!stringSize(username,8,16))
				result= "Your username should be 8 characters minumimum and 16 characters maximum";
			else
				if (!username.matches(usernameRegex))
					result = "Only lowercase letters numbers and _ are allowed. First character must start with a letter.";
				else
					if (!UserDAO.uniqueUsername(username))
						result="Username already in database";
			
		return result;				
	}
	
	/*	UTA ID rules:
	 *	- Cannot be empty or null.
	 *	- Exactly 10 digits.
	 * */
	private String validateUtaID(String utaID) {
		String result="";
		
		if(utaID.equals(""))
			result= "You must provide a UTA ID.";
		
		else
			if (!isTextAnInteger (utaID))
				result="UTA ID should be a number.";
			else
				if (!stringSize(utaID,10,10))
					result= "UTA ID should be 10 digits exactly.";
				
	return result;	
	}
	
	/*	UTA role rules:
	 *	- Cannot be empty or null.
	 *	- 3 roles maximum and each role must be different(Can't have two admin accounts with same UTAID)
	 * */
	private String validateRole(String utaID,String role) {
		String result="";
		
		//Check if user has registered with all 3 roles possibles
		if(UserDAO.maxRolesForUser(utaID))
			result="You have reached the maximum number of accounts you can create with your UTA ID.";
		else
			if (!UserDAO.uniqueRoleForUsername(utaID, role))
				result="You already have an account with this role.";
		
		return result;
	}
	
	
	/*	Password rules:
	 * 	- Only alphanumeric characters and special characters: !@#$%^&*(),.?\":{}|<>
	 *	- Cannot be empty or null.
	 *	- Character length is 8 min 16 max.
	 * */
	public static String validatePassword(String password) {
		String result="";
		String passwordRegex ="^[a-zA-z0-9!@#$%]+$";
		
		if(password.equals(""))
			result= "You must provide a password.";
		
		else
			if (!stringSize(password,8,16))
				result= "Your password should be 8 characters minumimum and 16 characters maximum";
			else
				if (!password.matches(passwordRegex))
					result = "Allowed : letters numbers and one or more of the following characters: !@#$%";
				
			
		return result;	
	}
	

	/*	Phone rules:
	 *	- Cannot be empty or null.
	 *	- Exactly 10 digits.
	 * */
	public static String validatePhone(String phone) {
		
		String result="";
		
		if(phone.equals(""))
			result= "You must provide a phone number.";
		
		else
			if (!isTextAnInteger (phone))
				result="Phone should be a number.";
			else	
				if (!stringSize(phone,10,10))
					result= "Phone should be 10 digits exactly.";				
	
		return result;
	}
	
	
	/*	Email rules:
	 * 	- Contains a exactly one @ character.
	 *	- Cannot be empty or null.
	 *	- Alphanumeric and .
	 *	- Character length is 7 min 45 max.
	 *	- Valid .edu extension only
	 * */
	public static String validateEmail(String email) {
		String result="";
		String emailRegex ="^[a-zA-Z0-9.]+@[a-zA-Z0-9]+.edu$";
		
		if(email.equals(""))
			result= "You must provide an email.";
		
		else
			if (!stringSize(email,7,45))
				result= "Your email should be 7 characters minumimum and 45 characters maximum";
			else
				if (!email.matches(emailRegex))
					result = ".edu domain only. Alphanumeric and . only";
			
		return result;
	}
	

	/*	License plate rules:
	 * 	- Format is : AAANNNN | A for letter and N for number.
	 *	- Cannot be empty or null.
	 *	- Only alphanumeric.
	 *	- Character length is 7 exactly.
	 *  - License plate should not be associated with another parking user account
	 * */
	public static String validateLicense_plate(String license_plate, String role) {
		String result="";
		String license_plateRegex ="[A-Z][A-Z][A-Z][0-9][0-9][0-9][0-9]";
		
		if(license_plate.equals(""))
			result= "You must provide a license plate number.";
		
		else
			if (!stringSize(license_plate,7,7))
				result= "License plate should be exactly 7 characters.";
			else
				if (!license_plate.matches(license_plateRegex))
					result = "Format is : AAANNNN . A for letter and N for number.";
				else
					if(!UserDAO.uniqueLicensePlate(license_plate) && role.equals("user"))
					result = "You already have a user account associated with this license plate number.";

		return result;
	}

	private static boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	private static boolean isTextAnInteger (String string) {
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
}