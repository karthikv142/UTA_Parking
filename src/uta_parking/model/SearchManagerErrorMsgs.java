package uta_parking.model;

public class SearchManagerErrorMsgs {
	public String search_usernameError;		
	public String utaIDError; 		
	public String phoneError; 		
	public String userEmailError; 		
	public String passwordError;
	public String error = "";
	
	
	public String getPasswordError() {
		return passwordError;
	}


	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}

	public String getUtaIDError() {
		return utaIDError;
	}


	public void setUtaIDError(String utaIDError) {
		this.utaIDError = utaIDError;
	}


	public String getPhoneError() {
		return phoneError;
	}


	public void setPhoneError(String phoneError) {
		this.phoneError = phoneError;
	}


	public String getUserEmailError() {
		return userEmailError;
	}


	public void setUserEmailError(String userEmailError) {
		this.userEmailError = userEmailError;
	}

	
	public SearchManagerErrorMsgs()
	{
		this.search_usernameError = "";
	}
	
	
	public void setSearch_usernameError(String search_usernameError)
	{this.search_usernameError = search_usernameError;}
	
	
	public String getSearch_usernameError() {
	return search_usernameError;
	}
}
