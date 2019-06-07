package uta_parking.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import uta_parking.util.SQLConnection;

public class OptionsDAO {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	//get the rate of a given option name
	public static double getOptionRate(String option_type) {  
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection(); 
		double rate =0;
		
		String queryString = "SELECT rate FROM options where name='"+option_type+"';";
				
			try {
				stmt = conn.createStatement();
				ResultSet optionsList = stmt.executeQuery(queryString);
				
				if (optionsList.next()) {
					
					//get attribute value in DB
					rate = Double.parseDouble(optionsList.getString("rate"));
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				};
			}
	
		return rate;
	}
	

}
