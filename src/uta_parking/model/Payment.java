package uta_parking.model;

public class Payment {
	
	private String credit_card_type;
	private String credit_card_number;
	private String amount;
	
	
	
   public Payment() {
	  this.credit_card_type="";
	  this.credit_card_number="";
	  this.amount="";
 
   }
   
   public void setPayment(String credit_card_number, String credit_card_type, String amount) {
	   
	   this.setCredit_card_type(credit_card_type);
	   this.setCredit_card_number(credit_card_number);
	   this.setAmount(amount);

   }

	
	public String getCredit_card_number() {
		return credit_card_number;
	}
	
	public void setCredit_card_number(String credit_card_number) {
		this.credit_card_number= credit_card_number;
		
	}
	
	public String getCredit_card_type() {
		return credit_card_type;
	}
	
	public void setCredit_card_type(String credit_card_type) {
		this.credit_card_type= credit_card_type;
		
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount= amount;
		
	}
	
	public String validatePayment (String credit_card_number, String credit_card_type) {
		String result ="";
		
		if (credit_card_number.equals(""))
			result = "A credit card number must be provided.";
		else
			if (!isTextAnInteger (credit_card_number))
				result="Credit card number should be a number.";
			else
				if (!stringSize(credit_card_number,16,16))
					result= "Credit card number should be 16 digits exactly.";
		
		return result;
		
		
	}
	
	private boolean stringSize(String string, int min, int max) {
//		return string.length()>=min && string.length()<=max;
		return string.length()==16;
	}
	private boolean isTextAnInteger (String string) {
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

