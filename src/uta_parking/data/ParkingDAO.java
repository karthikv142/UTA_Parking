package uta_parking.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import uta_parking.util.SQLConnection;
import uta_parking.model.Parking;
import uta_parking.model.ParkingArea;

public class ParkingDAO {
	

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<Parking> ReturnMatchingParkingList (String queryString) {
			
			ArrayList<Parking> parkingListInDB = new ArrayList<Parking>();
			
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
			
		try {
			stmt = conn.createStatement();
			ResultSet parkingList = stmt.executeQuery(queryString);
			
			//Looping through the whole result of query and creation an object for each row of DB 
			while (parkingList.next()) {
				
				Parking parking = new Parking(); 
				
				//get attribute value in DB
				
				parking.setParking_area_name(parkingList.getString("parking_area_name"));
				parking.setParking_type(parkingList.getString("type"));
				parking.setFloor(parkingList.getString("floor"));
				parking.setCapacity(parkingList.getString("capacity"));
				parking.setAvailability(parkingList.getString("availability"));
				parking.setCart(parkingList.getString("cart"));
				parking.setCamera(parkingList.getString("camera"));
				parking.setHistory(parkingList.getString("history"));

				//Print each reservation details
				System.out.println();
				
				//add each parking row to the list
				parkingListInDB.add(parking);	
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
		return parkingListInDB;
	}
	
	//get available spots and order alphabetically by parking area name
	public static ArrayList<Parking>  listAvailableSpots(String parking_type) {  	
		return ReturnMatchingParkingList(" SELECT * from PARKING where type ='"+ parking_type+"' AND availability > 0 ORDER BY parking_area_name");
}
	
	
	public static String checkParkingCapacity(String parking_area_name, String floor)
	{ 
			String capacity = "";
				
			Statement stmt = null;   
			Connection conn = null; 
			
			try {   
				conn = SQLConnection.getDBConnection();  
				stmt = conn.createStatement();
		
				
				String searchAvailability = " SELECT * from PARKING WHERE parking_area_name = '"+parking_area_name+"' AND floor ='" + floor + "';";
				
				ResultSet result = stmt.executeQuery(searchAvailability);	
				
				if (result.next())
					{
						capacity= result.getString("capacity");
						System.out.println("Capacity for "+ parking_area_name + ", floor "+ floor+ " is: "+capacity );
					
					}
				else
					System.out.println("Not found");
				
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							try {
								conn.close();
								stmt.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}};
				
			
			return capacity;
	}
	

	//Check parking name/floor availability
	
	public static String checkParkingAvailability(String parking_area_name, String floor)
	{ 
			String numberOfAvailableSpots = "";
				
			Statement stmt = null;   
			Connection conn = null; 
			
			try {   
				conn = SQLConnection.getDBConnection();  
				stmt = conn.createStatement();
		
				
				String searchAvailability = " SELECT * from PARKING WHERE parking_area_name = '"+parking_area_name+"' AND floor ='" + floor + "';";
				
				ResultSet result = stmt.executeQuery(searchAvailability);	
				
				if (result.next())
					{
						numberOfAvailableSpots= result.getString("availability");
						System.out.println("Availability for "+ parking_area_name + ", floor "+ floor+ " is: "+numberOfAvailableSpots );
					
					}
				else
					System.out.println("Not found");
				
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							try {
								conn.close();
								stmt.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}};
				
			
			return numberOfAvailableSpots;
	}
	

	
	//Update parking name/floor availability
	public static void updateAvailability(String parking_area_name, String floor, String parking_type, String operation, int operationValue)
	{ 
			int newAvailability = 0;
				
			Statement stmt = null;   
			Connection conn = null; 
			PreparedStatement pstmt = null;
			
			try {   
				conn = SQLConnection.getDBConnection();  
				stmt = conn.createStatement();
				//Check operation 
				if(operation.equals("+"))
					newAvailability = Integer.valueOf(checkParkingAvailability(parking_area_name,floor)) + operationValue;
				
				else if(operation.equals("-"))
					newAvailability = Integer.valueOf(checkParkingAvailability(parking_area_name,floor)) - operationValue;
				
				else
					System.out.println("Not a valid operation.");

				

				String updateAvailability  = "UPDATE PARKING SET availability = '"  +newAvailability +
				"' WHERE type ='" + parking_type+"' AND parking_area_name = '"+parking_area_name+"' AND floor ='" + floor + "';";
				 
				pstmt = (PreparedStatement) conn.prepareStatement(updateAvailability);
				
				int result = pstmt.executeUpdate();	
				conn.commit();
				
				System.out.println("Rows affected:" + result);
				
			
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							try {
								conn.close();
								stmt.close();
								
								pstmt.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}};
				
			
			
	}

	public static void addNewParkingArea(String query) {
		System.out.println(query);
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();		
		}finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}

	}
	
	public static void addParkingArea(ParkingArea parkingArea) {
		String query="";

		if(!parkingArea.getAccess().equals("0")) {
			query= "INSERT INTO PARKING (parking_area_name, type, floor, capacity, availability, cart, camera, history)";
			query += " VALUES ('"  
					+ parkingArea.getParkingAreaName()  + "','"
					+ "access"  + "','"
					+ parkingArea.getFloor()  + "','"
					+ parkingArea.getCapacity() + "','"
					+ parkingArea.getCapacity() + "','"
					+ parkingArea.getCart() + "','"
					+ parkingArea.getCamera()  + "','"
					+ parkingArea.getHistory() + "');";
			addNewParkingArea(query);
		}
		if(!parkingArea.getBasic().equals("0")) {
			query= "INSERT INTO PARKING (parking_area_name, type, floor, capacity, availability, cart, camera, history)";
			query += " VALUES ('"  
					+ parkingArea.getParkingAreaName()  + "','"
					+ "basic"  + "','"
					+ parkingArea.getFloor()  + "','"
					+ parkingArea.getCapacity() + "','"
					+ parkingArea.getCapacity() + "','"
					+ parkingArea.getCart() + "','"
					+ parkingArea.getCamera()  + "','"
					+ parkingArea.getHistory() + "');";
			System.out.println(query);
			addNewParkingArea(query);
		}

		if(!parkingArea.getPremium().equals("0")) {
			query= "INSERT INTO PARKING (parking_area_name, type, floor, capacity, availability, cart, camera, history)";
			query += " VALUES ('"  
					+ parkingArea.getParkingAreaName()  + "','"
					+ "premium"  + "','"
					+ parkingArea.getFloor()  + "','"
					+ parkingArea.getCapacity() + "','"
					+ parkingArea.getCapacity() + "','"
					+ parkingArea.getCart() + "','"
					+ parkingArea.getCamera()  + "','"
					+ parkingArea.getHistory() + "');";
			addNewParkingArea(query);
		}
		if(!parkingArea.getMidRange().equals("0")) {
			query= "INSERT INTO PARKING (parking_area_name, type, floor, capacity, availability, cart, camera, history)";
			query += " VALUES ('"  
					+ parkingArea.getParkingAreaName()  + "','"
					+ "midrange"  + "','"
					+ parkingArea.getFloor()  + "','"
					+ parkingArea.getCapacity() + "','"
					+ parkingArea.getCapacity() + "','"
					+ parkingArea.getCart() + "','"
					+ parkingArea.getCamera()  + "','"
					+ parkingArea.getHistory() + "');";	
			addNewParkingArea(query);
		}				
	}	

	
	
}