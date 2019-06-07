package uta_parking.model;



import java.math.RoundingMode;
import java.text.DecimalFormat;

import uta_parking.data.OptionsDAO;

public class Options {
	
	
	public double calculateTotalCost(String dayName, String start_time, String duration, String camera_option, String cart_option, String history_option)
	{			
		double camera_cost;
		double cart_cost;
		double history_cost;
		
		if (camera_option.equals("1"))
			{camera_cost = OptionsDAO.getOptionRate("camera");
				
			}
		
		else 
			{camera_cost=0.0;}
		
		
		if (cart_option.equals("1"))
			{
			cart_cost = OptionsDAO.getOptionRate("cart");
			  
			}
		else
			{cart_cost=0.0;}
		
		if (history_option.equals("1"))
		{history_cost = OptionsDAO.getOptionRate("history");
			
			
		}
	
		else
		{history_cost=0.0;}
		
		//NOW WE NEED TO GET THE DATE TO SEE IF WE NEED TO DOUBLE THE CART RATE
		/*
		 * Cart normal hours :
		 * Monday-Friday : 6:00 to 20:00
		 * Saturday : 8:00 to 17:00
		 * Sunday : 12:00 to 17:00
		 * */
		
		/*
		//GET LOCAL DATE
		ZoneId z = ZoneId.of( "US/Central" );
		LocalDate today = LocalDate.now(z); 		
		String dayName = today.getDayOfWeek().name();
		
		//this.setDate(dayName + " " + today);
		*/
	
		//Split into hours and minutes from user
		String hours_minutes[] = start_time.split(":");
		int hours = Integer.parseInt(hours_minutes[0]);
		int minutes = Integer.parseInt(hours_minutes[1]);
		
		int startTimeInMinutes = hours * 60 + minutes;
		int endTimeInMinutes = startTimeInMinutes + Integer.parseInt(duration);
		
		//For debugging
		//System.out.println("Today is: "+dayName);

		switch (dayName) {
			case "SUNDAY":
				if (startTimeInMinutes < 12*60 || startTimeInMinutes >= 17*60) 
						cart_cost = cart_cost *2;
				else 
					if (endTimeInMinutes > 17*60)
						cart_cost = cart_cost *2;
				break;
			case "SATURDAY":
				if (startTimeInMinutes < 8*60 || startTimeInMinutes >= 17*60) 
					cart_cost = cart_cost *2;
				else 
					if (endTimeInMinutes > 17*60)
						cart_cost = cart_cost *2;
				break;
			case "MONDAY":
				if (startTimeInMinutes < 6*60  || startTimeInMinutes >= 20*60)
					cart_cost = cart_cost *2;
				else	
					if ( endTimeInMinutes > 20*60)
						cart_cost = cart_cost *2;
				break;
			case "TUESDAY":
				if (startTimeInMinutes < 6*60  || startTimeInMinutes >= 20*60)
					cart_cost = cart_cost *2;
				else	
					if ( endTimeInMinutes > 20*60)
						cart_cost = cart_cost *2;
				break;
				
			case "WEDNESDAY":
				if (startTimeInMinutes < 6*60  || startTimeInMinutes >= 20*60)
					cart_cost = cart_cost *2;
				else	
					if ( endTimeInMinutes > 20*60)
						cart_cost = cart_cost *2;
				break;
			case "THURSDAY":
				if (startTimeInMinutes < 6*60  || startTimeInMinutes >= 20*60)
					cart_cost = cart_cost *2;
				else	
					if ( endTimeInMinutes > 20*60)
						cart_cost = cart_cost *2;
				break;
			case "FRIDAY":
				if (startTimeInMinutes < 6*60  || startTimeInMinutes >= 20*60)
					cart_cost = cart_cost *2;
				else	
					if ( endTimeInMinutes > 20*60)
						cart_cost = cart_cost *2;
				break;	
			default:
				System.out.println("No other day.");
		}
		
		
		double taxRate = 0.0825;
		double total = camera_cost + cart_cost+history_cost;
		
		total = total +  taxRate * total;
		
		
		 DecimalFormat df = new DecimalFormat("#.##");

		    df.setRoundingMode(RoundingMode.FLOOR);

		   total = new Double(df.format(total));
		
		System.out.println("Total cost is: " + total);
		
	
	
		return total;
	}

}
