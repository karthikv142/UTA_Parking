package uta_parking.model;

public class UserErrorMsgs {

	private String errorMsg;
	private String usernameError;
	private String utaIDError ;
	private String roleError ;
	private String passwordError ;
	private String phoneError ;
	private String emailError ;
	private String license_plateError ;
	
	
	public UserErrorMsgs(){
		
		
		this.errorMsg="";
		this.usernameError="";
		this.utaIDError ="";
		this.roleError ="";
		this.passwordError ="";
		this.phoneError ="";
		this.emailError ="";
		this.license_plateError ="";
		
		
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg() {
		
		/*	For debugging 
			System.out.println("When setting error msgs:");
			System.out.println(usernameError);
			System.out.println(utaIDError);
			System.out.println(roleError);
			System.out.println(passwordError);
			System.out.println(phoneError);
			System.out.println(emailError);
			System.out.println(license_plateError);
		*/
		
		if (!usernameError.equals("") ||!utaIDError.equals("") ||!roleError.equals("") 
			||!passwordError.equals("") ||!phoneError.equals("") ||!emailError.equals("") ||!license_plateError.equals(""))
		{errorMsg="Please correct the following errors";}
		
		else 
		{//System.out.println("all good");
		}
	}
	
	public String getUsernameError() {
		return usernameError;
	}
	
	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}
	public String getUtaIDError() {
		return utaIDError;
	}
	public void setUtaIDError(String utaIDError) {
		this.utaIDError = utaIDError;
	}
	
	public String getRoleError() {
		return roleError;
	}
	public void setRoleError(String roleError) {
		this.roleError = roleError;
	}
	public String getPasswordError() {
		return passwordError;
	}
	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}
	
	public String getPhoneError() {
		return phoneError;
	}
	public void setPhoneError(String phoneError) {
		this.phoneError = phoneError;
	}
	
	public String getEmailError() {
		return emailError;
	}
	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}
	
	public String getLicense_plateError() {
		return license_plateError;
	}
	public void setLicense_plateError(String license_plateError) {
		this.license_plateError = license_plateError;
	}
	
}