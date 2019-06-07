package uta_parking.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import uta_parking.util.SQLConnection;
import uta_parking.model.ParkingSpot;
import uta_parking.model.Reservation;

public class ReservationDAO {

	/*
	 * Reservation table columns names parking_area_name type floor spot_num
	 * user_name start_time duration cost status cart camera history date
	 */

	static SQLConnection DBMgr = SQLConnection.getInstance();

	public static void insertReservation(Reservation reservation) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		// Construct the query for inserting a new user
		String insertReservation = "INSERT INTO RESERVATION (parking_area_name,type,floor,spot_num,user_name,start_time, duration, cost, status, cart,camera,history,date) ";
		insertReservation += " VALUES ('" + reservation.getParking_area_name() + "','" + reservation.getParking_type()
				+ "','" + reservation.getFloor() + "','" + reservation.getSpot_number() + "','"
				+ reservation.getUser_name() + "','" + reservation.getStart_time() + "','" + reservation.getDuration()
				+ "','" + reservation.getCost() + "'," + 1 + ",'" + reservation.getCart_option() + "','"
				+ reservation.getCamera_option() + "','" + reservation.getHistory_option() + "',CURDATE());";

		System.out.println("Reservation query:" + insertReservation);

		try {
			conn = SQLConnection.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(insertReservation);
			conn.commit();

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			;
		}

	}

	// check if user has made 3 reservations already.
	public static boolean maxedForTheDay(String user_name) {

		// check if user has already 3 reservations
		// SET SQL_SAFE_UPDATES = 0; for testing
		Statement stmt = null;
		Connection conn = null;
		boolean isMax = false;
		int isMaxReservation = 0;
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			// get local date
			ZoneId z = ZoneId.of("US/Central");
			LocalDate today = LocalDate.now(z);

			String searchMaxRerservations = " SELECT * from RESERVATION WHERE user_name = '" + user_name
					+ "' AND status= 1 AND date ='" + today + "';";

			ResultSet maxReservationList = stmt.executeQuery(searchMaxRerservations);
			while (maxReservationList.next()) {
				isMaxReservation++;

			}

			// For debugging
			System.out.println("Number of reservations: " + isMaxReservation);

			if (isMaxReservation == 3)
				isMax = true;
			else
				isMax = false;
			return isMax;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		;

		return false;
	}

	public static boolean hasOverlappingReservations(String user_name, String start_time, String duration) {

		// check if user has overlapping reservations
		// SET SQL_SAFE_UPDATES = 0; for testing
		Statement stmt = null;
		Connection conn = null;

		/*
		 * OVERLAPPING RESERVATIONS
		 * 
		 * Case 1 : start1 |-----------------| end1 start2 |------------|end2
		 * 
		 * 
		 * Case 2 : start1 |-----------------| end1 start2 |------------|end2
		 * 
		 * Case 3 : start1 |-----------------| end1 start2 |-------|end2
		 * 
		 * 
		 */

		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			// get local date
			ZoneId z = ZoneId.of("US/Central");
			LocalDate today = LocalDate.now(z);

			String overlap = "'" + start_time + "'< start_time + INTERVAL duration MINUTE AND CAST('" + start_time
					+ "' as TIME) + INTERVAL " + duration + " MINUTE > start_time";
			
			
			System.out.println("overlap : " + overlap );

			String searchOverlappingRerservations = " SELECT * from RESERVATION WHERE user_name = '" + user_name
					+ "' AND status= 1 AND date ='" + today + "' AND (" + overlap + ");";
			System.out.println("overlap query:" +  searchOverlappingRerservations);

			// System.out.println("OVERLAP SQL: " +
			// searchOverlappingRerservations);
			ResultSet searchOverlappingRerservationsList = stmt.executeQuery(searchOverlappingRerservations);

			if (searchOverlappingRerservationsList.next()) {
				// System.out.println("Found overlapping");
				return true;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		;

		return false;
	}

	public static void cancelExpiredReservations() {
		Statement stmt = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			String updateStatus = "UPDATE reservation SET status = 0 WHERE date=curdate() AND ((start_time + INTERVAL duration MINUTE) < curtime());";

			pstmt = (PreparedStatement) conn.prepareStatement(updateStatus);

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
			}
		}
		;

	}

	// Make expired reservations s status 0, then sort them and get the smallest
	// spot number
	public static String ReturnExpiredSpotNumber(String parking_area_name, String floor) {

		String parkingSpot = "";

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		String queryString = "select * from reservation where status = 0 AND parking_area_name = '" + parking_area_name
				+ "' AND " + "floor ='" + floor + "' ORDER BY spot_num ASC;";
		try {
			stmt = conn.createStatement();
			ResultSet expiredReservation = stmt.executeQuery(queryString);

			// get the first available number for that parking
			if (expiredReservation.next()) {
				parkingSpot = expiredReservation.getString("spot_num");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			;
		}
		return parkingSpot;
	}

	private static ArrayList<Reservation> ReturnUserReservationList(String queryString) {

		ArrayList<Reservation> reservationListInDB = new ArrayList<Reservation>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		try {
			stmt = conn.createStatement();
			ResultSet reservationList = stmt.executeQuery(queryString);

			// Looping through the whole result of query and creation an object
			// for each row of DB
			while (reservationList.next()) {

				Reservation reservation = new Reservation();

				// get attribute value in DB

				reservation.setReservationID(reservationList.getString("reserve_id"));
				reservation.setParking_area_name(reservationList.getString("parking_area_name"));
				reservation.setParking_type(reservationList.getString("type"));
				reservation.setFloor(reservationList.getString("floor"));
				reservation.setSpot_number(reservationList.getString("spot_num"));
				reservation.setUser_name(reservationList.getString("user_name"));
				reservation.setStart_time(reservationList.getString("start_time"));
				reservation.setDuration(reservationList.getString("duration"));
				reservation.setCost(reservationList.getString("cost"));
				reservation.setStatus(reservationList.getString("status"));
				reservation.setCart_option(reservationList.getString("cart"));
				reservation.setCamera_option(reservationList.getString("camera"));
				reservation.setHistory_option(reservationList.getString("history"));

				// add each parking row to the list
				reservationListInDB.add(reservation);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			;
		}
		return reservationListInDB;
	}

	// get available spots and order alphabetically by parking area name
	public static ArrayList<Reservation> listUserSpots(String user_name) {
		return ReturnUserReservationList(" SELECT * from RESERVATION where date = curdate() AND user_name ='"
				+ user_name + "' AND status =1 ORDER BY start_time ASC");
	}

	public static void updateReservationStatus(String reservationID, String status) {
		Statement stmt = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			String updateStatus = "UPDATE RESERVATION SET status = " + status + " WHERE reserve_id = '" + reservationID
					+ "';";

			pstmt = (PreparedStatement) conn.prepareStatement(updateStatus);

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
			}
		}
		;

	}

	// function for retriving reservation details

	public static ArrayList<Reservation> getReservationDetails() {
		ArrayList<Reservation> reservationDetails = new ArrayList<Reservation>();
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();
			String query = "SELECT * FROM RESERVATION;";
			ResultSet reservations = stmt.executeQuery(query);
			System.out.println(query);
			while (reservations.next()) {
				Reservation reservation = new Reservation();
				reservation.setParking_area_name(reservations.getString("parking_area_name"));
				reservation.setParking_type(reservations.getString("type"));
				reservation.setFloor(reservations.getString("floor"));
				reservation.setSpot_number(reservations.getString("spot_num"));
				reservation.setUser_id(reservations.getString("user_id"));
				reservation.setStart_time(reservations.getString("start_time"));
				reservation.setDuration(reservations.getString("duration"));
				reservation.setCost(reservations.getString("cost"));
				reservation.setCart_option(reservations.getString("cart"));
				reservation.setCamera_option(reservations.getString("camera"));
				reservation.setHistory_option(reservations.getString("history"));

				System.out.println(reservation.getUser_id());
				reservationDetails.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return reservationDetails;
	}

//	public static ArrayList<Reservation> getSpotDetails(ParkingSpot spot) {
//		ArrayList<Reservation> spotDetails = new ArrayList<Reservation>();
//		Statement stmt = null;
//		Connection conn = null;
//
//		System.out.println("reach1");
//		try {
//			conn = SQLConnection.getDBConnection();
//			stmt = conn.createStatement();
//			String query = "SELECT * FROM RESERVATION " + "WHERE spot_num = '" + spot.getSpot() + "' AND "
//					+ "parking_area_name ='" + spot.getParkingAreaName() + "' AND " + "floor = '" + spot.getFloor()
//					+ "' ;";
//			ResultSet reservations = stmt.executeQuery(query);
//			System.out.println(query);
//			while (reservations.next()) {
//				Reservation reservation = new Reservation();
//				reservation.setParking_area_name(reservations.getString("parking_area_name"));
//				reservation.setParking_type(reservations.getString("type"));
//				reservation.setFloor(reservations.getString("floor"));
//				reservation.setSpot_number(reservations.getString("spot_num"));
//				reservation.setUser_id(reservations.getString("user_id"));
//				reservation.setStart_time(reservations.getString("start_time"));
//				reservation.setDuration(reservations.getString("duration"));
//				reservation.setCost(reservations.getString("cost"));
//				reservation.setCart_option(reservations.getString("cart"));
//				reservation.setCamera_option(reservations.getString("camera"));
//				reservation.setHistory_option(reservations.getString("history"));
//
//				System.out.println(reservation.getUser_id());
//				spotDetails.add(reservation);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return spotDetails;
//	}
	
	public static ArrayList<Reservation> getSpotDetails(ParkingSpot spot){
		ArrayList<Reservation> spotDetails = new ArrayList<Reservation>();
		Statement stmt = null;   
		Connection conn = null; 
		
		System.out.println("reach1");
		try {
			conn = SQLConnection.getDBConnection();  
			stmt = conn.createStatement();
			String query = "SELECT * FROM RESERVATION "
					+ "WHERE spot_num = '" + spot.getSpot()+"' AND "
					+ "parking_area_name ='" + spot.getParkingAreaName()+"' AND "
					+ "floor = '" + spot.getFloor() + "' ;";
			ResultSet reservations = stmt.executeQuery(query);
			System.out.println(query);
			while (reservations.next()) {
				Reservation reservation = new Reservation();
				reservation.setParking_area_name(reservations.getString("parking_area_name"));				
				reservation.setParking_type(reservations.getString("type"));
				reservation.setFloor(reservations.getString("floor"));
				reservation.setSpot_number(reservations.getString("spot_num"));
				reservation.setUser_id(reservations.getString("user_id"));
				reservation.setStart_time(reservations.getString("start_time"));
				reservation.setDuration(reservations.getString("duration"));
				reservation.setCost(reservations.getString("cost"));
				reservation.setCart_option(reservations.getString("cart"));
				reservation.setCamera_option(reservations.getString("camera"));
				reservation.setHistory_option(reservations.getString("history"));
				
				System.out.println(reservation.getUser_id());
				spotDetails.add(reservation);					
			}			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return spotDetails;
	}
	

}