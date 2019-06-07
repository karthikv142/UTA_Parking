package uta_parking.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uta_parking.model.ModifyParkingAreaManager;
import uta_parking.model.ModifyParkingSpotsManager;
import uta_parking.util.SQLConnection;

public class ManagerDAO {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
private static String ModifyParkingArea(String queryString) {
		
		System.out.println(queryString);
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();			
			stmt.executeUpdate(queryString);
			
			System.out.println("Query Fired!");
			
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
//		return userListInDB;
		return "Updated!";
	}	

	public static String modifyParkingArea(String parking_area_name, String type, String floor, String capacity, String availability, String cart, String camera, String history) {
				
//		return ModifyParkingArea("update parking set availability =" + availability + ", capacity =" + capacity + ", cart =" + cart + ", camera = "+ camera + ", history =" + history + " WHERE parking_area_name ='" + parking_area_name +"' AND type ='" + type + "' AND floor =" + floor + ";");
		return ModifyParkingArea("update parking set availability ='" + availability + "', capacity ='" + capacity + "', cart ='" + cart + "', camera = '"+ camera + "', history ='" + history + "' WHERE parking_area_name ='" + parking_area_name +"' AND type ='" + type + "' AND floor ='" + floor + "';");
	}
	
private static String DeletePastReservations(String queryString) {
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();			
			stmt.executeUpdate(queryString);
			
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}

		return "Updated!";
	}	

	public static String deletePastReservations() {
				
		return DeletePastReservations("delete from reservation where date(now()) > date(date);");
		}
	
//	private static ArrayList<ModifyParkingAreaManager> ReturnMatchingSpotsList (String queryString) {
//		ArrayList<ModifyParkingAreaManager> spotsListInDB = new ArrayList<ModifyParkingAreaManager>();
//		
//			Statement stmt = null;
//			Connection conn = SQLConnection.getDBConnection();  
//		try {
//			stmt = conn.createStatement();
//			stmt.executeUpdate(queryString);
//			conn.commit();
////			while (spotList.next()) {
////				ModifyParkingAreaManager modifyparkingareamanager = new ModifyParkingAreaManager();
////				modifyparkingareamanager.setParking_area_name(spotList.getString("parking_area_name"));
////				modifyparkingareamanager.setType(spotList.getString("type"));
////				modifyparkingareamanager.setFloor(spotList.getInt("floor"));
////				modifyparkingareamanager.setSpot_num(spotList.getInt("spot_num"));
//					
////				spotsListInDB.add(modifyparkingareamanager);
////				userListInDB.add(searchusermanager);
//			
////			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.commit();
//				conn.close();
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			};
//		}
//		return spotsListInDB;
//	}	
//
//	public static ArrayList<ModifyParkingAreaManager> listParkingSpotDetails(String parking_area_name, String type, int floor,int spot_num) {
//		
////		System.out.println("update RESERVATION set availability = 1 where parking_area_name="+parking_area_name+" and type ="+type+" and floor ="+floor+";");
////		return ReturnMatchingSpotsList("update RESERVATION set availability = 1 where parking_area_name="+parking_area_name+" and type ="+type+" and floor ="+floor+";");
//		System.out.println("SELECT * FROM RESERVATION WHERE parking_area_name='"+parking_area_name+"' AND type = '"+type+"' AND floor = "+floor+";");
//		return ReturnMatchingSpotsList("update reservation set availability = 0 where parking_area_name ='"+parking_area_name+"' and type ='"+type+"' and floor ="+floor+" and spot_num ="+spot_num+";");
//	}


private static ArrayList<ModifyParkingSpotsManager> ReturnMatchingSpotsList (String queryString) {
		ArrayList<ModifyParkingSpotsManager> spotsListInDB = new ArrayList<ModifyParkingSpotsManager>();
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet spotList = stmt.executeQuery(queryString);
			
			System.out.println("Query fired!");
			
			while (spotList.next()) {
				ModifyParkingSpotsManager modifyparkingspotsmanager = new ModifyParkingSpotsManager();				
				
				modifyparkingspotsmanager.setReserve_id(spotList.getString("reserve_id"));
				modifyparkingspotsmanager.setParking_area_name(spotList.getString("parking_area_name"));
				modifyparkingspotsmanager.setType(spotList.getString("type"));
				modifyparkingspotsmanager.setFloor(spotList.getString("floor"));
				modifyparkingspotsmanager.setCapacity(spotList.getString("spot_num"));
				modifyparkingspotsmanager.setAvailability(spotList.getString("availability"));
				modifyparkingspotsmanager.setCart(spotList.getString("cart"));
				modifyparkingspotsmanager.setCamera(spotList.getString("camera"));
				modifyparkingspotsmanager.setHistory(spotList.getString("history"));				
					
				spotsListInDB.add(modifyparkingspotsmanager);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return spotsListInDB;
	}	

	public static ArrayList<ModifyParkingSpotsManager> listParkingSpotDetails(String parking_area_name, String type, String floor) {
		
//		System.out.println("update RESERVATION set availability = 1 where parking_area_name="+parking_area_name+" and type ="+type+" and floor ="+floor+";");
//		return ReturnMatchingSpotsList("update RESERVATION set availability = 1 where parking_area_name="+parking_area_name+" and type ="+type+" and floor ="+floor+";");
		System.out.println("SELECT * FROM RESERVATION WHERE parking_area_name='"+parking_area_name+"' AND type = '"+type+"' AND floor = "+floor+";");
		return ReturnMatchingSpotsList("SELECT * FROM RESERVATION WHERE parking_area_name='"+parking_area_name+"' AND type = '"+type+"' AND floor = "+floor+";");
//		return ReturnMatchingSpotsList("update reservation set availability = 0 where parking_area_name ='"+parking_area_name+"' and type ='"+type+"' and floor ="+floor+" and spot_num ='"+spot_num+"';");
	}
	
	private static ArrayList<ModifyParkingAreaManager> ReturnReservations (String queryString) {
		ArrayList<ModifyParkingAreaManager> reservationsInDB = new ArrayList<ModifyParkingAreaManager>();
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet reserveList = stmt.executeQuery(queryString);
			
			while(reserveList.next()) {
				ModifyParkingAreaManager modifyparkingareamanager = new ModifyParkingAreaManager();
				modifyparkingareamanager.setReserve_id(reserveList.getString("reserve_id"));
				modifyparkingareamanager.setParking_area_name(reserveList.getString("parking_area_name"));
				modifyparkingareamanager.setType(reserveList.getString("type"));
				modifyparkingareamanager.setFloor(reserveList.getString("floor"));
				modifyparkingareamanager.setSpot_num(reserveList.getString("spot_num"));
				
//				CHANGED ON APR 18
				
//				modifyparkingareamanager.setUser_id(reserveList.getString("user_id"));
				modifyparkingareamanager.setDuration(reserveList.getString("duration"));
				modifyparkingareamanager.setCost(reserveList.getString("cost"));
				modifyparkingareamanager.setStatus(reserveList.getString("status"));
				modifyparkingareamanager.setCart(reserveList.getString("cart"));
				modifyparkingareamanager.setCamera(reserveList.getString("camera"));
				modifyparkingareamanager.setHistory(reserveList.getString("history"));
				
//				CHANGED ON APR 18
				
//				modifyparkingareamanager.setAvailability(reserveList.getString("availability"));
				
				reservationsInDB.add(modifyparkingareamanager);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return reservationsInDB;
	}	

	public static ArrayList<ModifyParkingAreaManager> listReservationsInDB() {
		
//		System.out.println("update RESERVATION set availability = 1 where parking_area_name="+parking_area_name+" and type ="+type+" and floor ="+floor+";");
//		return ReturnMatchingSpotsList("update RESERVATION set availability = 1 where parking_area_name="+parking_area_name+" and type ="+type+" and floor ="+floor+";");
//		System.out.println("SELECT * FROM RESERVATION WHERE parking_area_name='"+parking_area_name+"' AND type = '"+type+"' AND floor = "+floor+";");
//		return ReturnReservations("update reservation set availability = 0 where parking_area_name ='"+parking_area_name+"' and type ='"+type+"' and floor ="+floor+" and spot_num ='"+spot_num+"';");
//		return ReturnReservations("SELECT * FROM RESERVATION WHERE AVAILABILITY = '1' ORDER BY RESERVE_ID;");		
//		CHANGED ON APR 18
		return ReturnReservations("SELECT * FROM RESERVATION WHERE STATUS = '1' ORDER BY RESERVE_ID;");
	}
	
private static String DeletePastReservationsManager(String queryString) {
		
		System.out.println(queryString);
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();			
			stmt.executeUpdate(queryString);
			
			System.out.println("Query Fired!");
			
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
//		return userListInDB;
		return "Updated!";
	}	

	public static String deletePastReservations(String reserve_id) {
				
//		return ModifyParkingArea("update parking set availability =" + availability + ", capacity =" + capacity + ", cart =" + cart + ", camera = "+ camera + ", history =" + history + " WHERE parking_area_name ='" + parking_area_name +"' AND type ='" + type + "' AND floor =" + floor + ";");
//		return ModifyParkingArea("update parking set availability ='" + availability + "', capacity ='" + capacity + "', cart ='" + cart + "', camera = '"+ camera + "', history ='" + history + "' WHERE parking_area_name ='" + parking_area_name +"' AND type ='" + type + "' AND floor ='" + floor + "';");
		System.out.println("UPDATE RESERVATION SET AVAILABILITY = 0 WHERE RESERVE_ID = '" + reserve_id + "';");
//		return DeletePastReservationsManager("UPDATE RESERVATION SET AVAILABILITY = 0 WHERE RESERVE_ID = '" + reserve_id + "';");
//		CHANGED ON APR 18
		return DeletePastReservationsManager("UPDATE RESERVATION SET STATUS = 0 WHERE RESERVE_ID = '" + reserve_id + "';");
	}	
	
	private static String NewReservationInDB(String queryString) {
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();			
		stmt.executeUpdate(queryString);
		
		conn.commit();

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			conn.commit();
			
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}

	return "Updated!";
}	

	public static String newReservationInDB(String reserve_id, String parking_area_name, String type , String floor, String spot_num, String start_time, String duration, String cart, String camera, String history) {
		
		System.out.println("INSERT INTO RESERVATION (parking_area_name, type, floor, spot_num, start_time, duration, cart, camera, history) values ('"+ parking_area_name +"', '"+ type +"', '"+ floor +"', '"+ spot_num +"', '"+ start_time +"', '"+ duration +"', '"+ cart +"', '"+ camera +"', '"+ history +"') ;");
		return NewReservationInDB("INSERT INTO RESERVATION (parking_area_name, type, floor, spot_num, start_time, duration, cart, camera, history) values ('"+ parking_area_name +"', '"+ type +"', '"+ floor +"', '"+ spot_num +"', '"+ start_time +"', '"+ duration +"', '"+ cart +"', '"+ camera +"', '"+ history +"') ;");
//		System.out.println("update RESERVATION set parking_area_name ='" + parking_area_name + "', type ='" + type + "', floor ='" + floor + "', spot_num = '"+ spot_num + "', start_time ='" + start_time + "' , duration ='" + duration+ "' , cart ='" + cart+ "', camera ='" + camera+ "', history ='" + history+ "'	where reserve_id ='" + reserve_id+"';");
//		return NewReservationInDB("update RESERVATION set parking_area_name ='" + parking_area_name + "', type ='" + type + "', floor ='" + floor + "', spot_num = '"+ spot_num + "', start_time ='" + start_time + "' , duration ='" + duration+ "' , cart ='" + cart+ "', camera ='" + camera+ "', history ='" + history+ "' where reserve_id ='" + reserve_id+"';");

	}
	private static String MakeSpotUnavailable(String queryString) {
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();			
		stmt.executeUpdate(queryString);
		
		conn.commit();

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			conn.commit();
			
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}

	return "Updated!";
}	

	public static String makeSpotUnavailable(String reserve_id) {
		
//		System.out.println("INSERT INTO RESERVATION (parking_area_name, type, floor, spot_num, start_time, duration, cart, camera, history) values ('"+ parking_area_name +"', '"+ type +"', '"+ floor +"', '"+ spot_num +"', '"+ start_time +"', '"+ duration +"', '"+ cart +"', '"+ camera +"', '"+ history +"') ;");
//		return NewReservationInDB("INSERT INTO RESERVATION (parking_area_name, type, floor, spot_num, start_time, duration, cart, camera, history) values ('"+ parking_area_name +"', '"+ type +"', '"+ floor +"', '"+ spot_num +"', '"+ start_time +"', '"+ duration +"', '"+ cart +"', '"+ camera +"', '"+ history +"') ;");
//		System.out.println("update RESERVATION set parking_area_name ='" + parking_area_name + "', type ='" + type + "', floor ='" + floor + "', spot_num = '"+ spot_num + "', start_time ='" + start_time + "' , duration ='" + duration+ "' , cart ='" + cart+ "', camera ='" + camera+ "', history ='" + history+ "'	where reserve_id ='" + reserve_id+"';");
//		return MakeSpotUnavailable("update RESERVATION set parking_area_name ='" + parking_area_name + "', type ='" + type + "', floor ='" + floor + "', spot_num = '"+ spot_num + "', start_time ='" + start_time + "' , duration ='" + duration+ "' , cart ='" + cart+ "', camera ='" + camera+ "', history ='" + history+ "' where reserve_id ='" + reserve_id+"';");
		System.out.println("UPDATE RESERVATION SET AVAILABILITY = 0 WHERE RESERVE_ID = '" + reserve_id + "';");
		return MakeSpotUnavailable("UPDATE RESERVATION SET AVAILABILITY = 0 WHERE RESERVE_ID = '" + reserve_id + "';");		
	}
}
