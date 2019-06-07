package uta_parking.model;

public class CurrentUserErrorMsgs {

	private String errorMsg="";
	private String current_user_idError="";
	private String current_usernameError="";
	private String current_utaIDError="";
	private String current_roleError="";
	
	
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg() {
		
		if (!current_usernameError.equals("") ||!current_utaIDError.equals("") ||!current_roleError.equals("") 
			||current_user_idError.equals(""))
		{
			errorMsg="Please correct the following errors";
		}
		
		else 
		{
			//System.out.println("all good");
		}
	}
	
	
	public String getCurrent_user_idError() {
		return current_user_idError;
	}
	public void setCurrent_user_idError(String current_user_idError) {
		this.current_user_idError= current_user_idError;
	}
	
	public String getCurrent_usernameError() {
		return current_usernameError;
	}
	
	public void setCurrent_usernameError(String current_usernameError) {
		this.current_usernameError= current_usernameError;
	}
	public String getCurrent_taIDError() {
		return current_utaIDError;
	}
	public void setUtaIDError(String current_utaIDError) {
		this.current_utaIDError= current_utaIDError;
	}
	
	public String getCurrent_roleError() {
		return current_roleError;
	}
	public void setCurrent_roleError(String current_roleError) {
		this.current_roleError= current_roleError;
	}

	

}
