package uta_parking.model;

import uta_parking.data.UserDAO;

public class Login {
	private String username = "";
	private String password = "";
//	private String greetingText="";
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getPassword() {
		return password;
	}
//	public void setGreetingText(String greetingText) {
//		this.greetingText=greetingText;
//	}
//	public String getGreetingText() {
//		return greetingText;
//	}
	
	public String validateLogin(String username, String password)
	{
		String result ="";
		
		if (username.equals("") || password.equals(""))
			result = "Both username and password must be provided.";
		else
			if(!UserDAO.getLogin(username, password))
				result = "Invalid username or password.";
			
				
			
		
		return result;
	}
}