package uta_parking.model;

public class SearchUserErrorMsgs {
	
	public String search_usernameError;
	public String roleError;		
	public String utaIDError; 		
	public String phoneError; 		
	public String userEmailError; 	
	public String licenseError;	
	public String userStatusError; 	
	public String reasonError;
	public String error;
	
	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String getRoleError() {
		return roleError;
	}


	public void setRoleError(String roleError) {
		this.roleError = roleError;
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


	public String getLicenseError() {
		return licenseError;
	}


	public void setLicenseError(String licenseError) {
		this.licenseError = licenseError;
	}


	public String getUserStatusError() {
		return userStatusError;
	}


	public void setUserStatusError(String userStatusError) {
		this.userStatusError = userStatusError;
	}


	public String getReasonError() {
		return reasonError;
	}


	public void setReasonError(String reasonError) {
		this.reasonError = reasonError;
	}


	public SearchUserErrorMsgs()
	{
		this.search_usernameError = "";
	}
	
	
	public void setSearch_usernameError(String search_usernameError){
		this.search_usernameError = search_usernameError;
		}	
	
	public String getSearch_usernameError() {
	return search_usernameError;
	}
}
