package uta_parking.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import uta_parking.model.*;
import uta_parking.util.SQLConnection;

public class UserDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();

	/*
	 * USER table column names : user_name uta_id phone email license_no noshow
	 * overstay reason role status default 1 deleted default 0
	 */

	/*
	 * CREDENTIALS table column names : user_name password role
	 */

	// Insert new user
	public static void insertUser(User user) {
		Statement stmt1 = null, stmt2 = null;
		Connection conn = SQLConnection.getDBConnection();

		/*
		 * FOR DEBBUGING System.out.println("BEFORE CREATING QUERY");
		 * 
		 * System.out.println("username: " + user.getUsername());
		 * System.out.println("password: " +user.getPassword());
		 * System.out.println("role: " +user.getRole());
		 * System.out.println("utaid: " +user.getUtaID());
		 * System.out.println("phone: " +user.getPhone());
		 * System.out.println("email: " +user.getEmail());
		 * System.out.println("license plate: " +user.getLicense_plate());
		 * 
		 */

		// Construct the query for inserting a new user
		String insertUser = "INSERT INTO USER (user_name,uta_id,phone,email,license_no,noshow,overstay,reason, role, type, status) ";
		insertUser += " VALUES ('" + user.getUsername() + "','" + user.getUtaID() + "','" + user.getPhone() + "','"
				+ user.getEmail() + "','" + user.getLicense_plate() + "'," + 0 + "," + 0 + "," + "NULL" + ",'"
				+ user.getRole() + "','" + UserDAO.getUserParkingType(user.getUtaID()) + "'," + 1 + ");";

		// Construct the query for inserting a new user credentials
		String insertUserCredentials = "INSERT INTO CREDENTIALS (user_name, password, role)";
		insertUserCredentials += " VALUES ('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getRole()
				+ "');";

		// For debugging
		System.out.println("hello");
		System.out.println(insertUser);
		System.out.println(insertUserCredentials);

		try {
			conn = SQLConnection.getDBConnection();
			conn.setAutoCommit(false);
			stmt1 = conn.createStatement();
			stmt1.executeUpdate(insertUser);
			stmt2 = conn.createStatement();
			stmt2.executeUpdate(insertUserCredentials);
			conn.commit();

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} finally {
			try {
				conn.close();
				stmt1.close();
				stmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			;
		}
	}

	// unique username
	public static boolean uniqueUsername(String check_username) {
		Statement stmt = null;
		Connection conn = null;
		boolean isUnique = false;
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			// Create query that checks if username is already in database

			String searchUsername = " SELECT * from USER WHERE USER_NAME = '" + check_username
					+ "' ORDER BY user_name;";
			ResultSet usersList = stmt.executeQuery(searchUsername);
			if (usersList.next())
				isUnique = false;
			else
				isUnique = true;
			return (isUnique);

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

	// check if utaID is associated with 3 accounts already ie max number of
	// accounts
	public static boolean maxRolesForUser(String utaID) {
		// SET SQL_SAFE_UPDATES = 0; for testing
		Statement stmt = null;
		Connection conn = null;
		boolean isMax = false;
		int isMaxRoles = 0;
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			String searchMaxRoles = " SELECT * from USER WHERE uta_id = '" + utaID + "' ORDER BY user_name;";
			ResultSet rolesList = stmt.executeQuery(searchMaxRoles);
			while (rolesList.next()) {
				isMaxRoles++;

			}

			// For debugging
			System.out.println("Number of accounts: " + isMaxRoles);

			if (isMaxRoles == 3)
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

	// Checks that utaid is not linked to more than one of each role type
	public static boolean uniqueRoleForUsername(String utaID, String role) {
		Statement stmt = null;
		Connection conn = null;
		boolean roleExists = false;

		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			String searchRoleExists = " SELECT * from USER WHERE uta_id = '" + utaID + "' AND role = '" + role
					+ "'ORDER BY role";

			ResultSet roleList = stmt.executeQuery(searchRoleExists);

			if (roleList.next())
				roleExists = false;
			else
				roleExists = true;

			return (roleExists);

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

	// checks that license plate number is not already associated with another
	// parking user account
	public static boolean uniqueLicensePlate(String license_plate) {
		Statement stmt = null;
		Connection conn = null;
		boolean licensePlateIsUnique = false;

		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			String role = "user";

			String uniqueLicensePlate = " SELECT * from USER WHERE license_no = '" + license_plate + "' AND role = '"
					+ role + "'ORDER BY role";

			ResultSet licensePlateList = stmt.executeQuery(uniqueLicensePlate);

			if (licensePlateList.next())
				licensePlateIsUnique = false;
			else
				licensePlateIsUnique = true;

			return licensePlateIsUnique;

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

	// get the type of parking of a given user
	public static String getUserParkingType(String current_user_utaID) {

		String result = "";

		// SET SQL_SAFE_UPDATES = 0; for testing
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			String searchUserParkingType = " SELECT * from USER_PARKING_TYPE WHERE uta_ID = '" + current_user_utaID
					+ "';";
			ResultSet userParkingTypeList = stmt.executeQuery(searchUserParkingType);
			if (userParkingTypeList.next()) {
				result = userParkingTypeList.getString("parking_type");
			}

			// for debugging
			System.out.println("From db: " + result);

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
		return result;
	}

	// return true is user is active, false if user is revoked
	public static boolean isActiveUser(String current_user_name) {

		String result = "";

		// SET SQL_SAFE_UPDATES = 0; for testing
		Statement stmt = null;
		Connection conn = null;
		boolean isActive = false;

		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			String searchUserParkingStatus = " SELECT * from USER WHERE user_name = '" + current_user_name + "';";
			ResultSet userParkingStatusList = stmt.executeQuery(searchUserParkingStatus);

			if (userParkingStatusList.next()) {
				result = userParkingStatusList.getString("status");

				if (result.equals("1"))
					isActive = true;
				else
					isActive = false;

				return isActive;
			}

			// for debugging
			System.out.println("From db: " + result);

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
		return false;
	}

	private static ArrayList<SearchUserManager> ReturnMatchingUsersList(String queryString) {
		ArrayList<SearchUserManager> userListInDB = new ArrayList<SearchUserManager>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				SearchUserManager searchusermanager = new SearchUserManager();
				searchusermanager.setUser_name(userList.getString("user_name"));
				searchusermanager.setRole(userList.getString("role"));
				searchusermanager.setUta_id(userList.getString("uta_id"));
				searchusermanager.setPhone(userList.getString("phone"));
				searchusermanager.setEmail(userList.getString("email"));
				searchusermanager.setLicense_no(userList.getString("license_no"));
				searchusermanager.setType(userList.getString("type"));
				searchusermanager.setStatus(userList.getString("status"));
				searchusermanager.setNoshow(userList.getInt("noshow"));
				searchusermanager.setOverstay(userList.getInt("overstay"));

				userListInDB.add(searchusermanager);
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
		return userListInDB;
	}

	public static ArrayList<SearchUserManager> listParkingUserDetails(String username) {

		// System.out.println(" SELECT * from USER WHERE user_name = " +
		// "'username';");
		return ReturnMatchingUsersList("SELECT * from USER WHERE user_name ='" + username + "';");
	}

	public static boolean getLogin(String username, String password) {
		boolean isRegistered = false;

		// SET SQL_SAFE_UPDATES = 0; for testing
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			String searchLogin = "SELECT * from CREDENTIALS WHERE user_name = '" + username + "' AND password = '"
					+ password + "';";

			ResultSet userCredentials = stmt.executeQuery(searchLogin);
			if (userCredentials.next()) {
				isRegistered = true;
			}

			// for debugging
			System.out.println("User is registered: " + isRegistered);

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

		return isRegistered;
	}

	public static String getLoginRole(String username) {
		String role = "";

		// SET SQL_SAFE_UPDATES = 0; for testing
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			String searchRole = " SELECT * from CREDENTIALS WHERE user_name = '" + username + "';";

			ResultSet userRole = stmt.executeQuery(searchRole);
			if (userRole.next()) {

				role = userRole.getString("role");
			}

			// for debugging
			System.out.println("User role: " + role);

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

		return role;

	}



	private static ArrayList<SearchUserAdmin> ReturnUser(String queryString) {
		ArrayList<SearchUserAdmin> userListInDB = new ArrayList<SearchUserAdmin>();

		Statement stmt = null;
		Connection conn = null;

		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);

			while (userList.next()) {
				SearchUserAdmin admin = new SearchUserAdmin();

				admin.setUserName(userList.getString("user_name"));
				admin.setUta_id(userList.getString("uta_id"));
				admin.setRole(userList.getString("role"));
				admin.setPhone(userList.getString("phone"));
				admin.setEmail(userList.getString("email"));
				admin.setStatus(userList.getInt("status") == 1 ? "Active" : "Revoked");
				admin.setReason(userList.getString("reason"));
				admin.setLicenseNo(userList.getString("license_no"));

				userListInDB.add(admin);
				System.out.println(admin.getUserName());
			}
		} catch (Exception e) {
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
		return userListInDB;
	}

	public static ArrayList<SearchUserAdmin> parkingUserDetails(String username) {
		return ReturnUser(" SELECT * from USER WHERE user_name = '" + username + "'");
	}


	public static void updateProfileField(String column, String value, String username) {

		Statement stmt = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String updateUser = "";

		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			if (column.equals("password"))
				updateUser = "UPDATE CREDENTIALS SET " + column + "= '" + value + "' WHERE user_name = '" + username
						+ "';";

			else
				updateUser = "UPDATE USER SET " + column + "= '" + value + "' WHERE user_name = '" + username + "';";

			pstmt = (PreparedStatement) conn.prepareStatement(updateUser);

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

	}

	public static void updateUser(SetViolations setviolations) {
		String query = "UPDATE USER SET noshow = " + setviolations.getNoShow() + "," + "overstay = "
				+ setviolations.getOverStay() + " WHERE user_name ='" + setviolations.getUserName() + "';";
		
		System.out.println("Query in UserDAO: " + query);

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void updateManagerProfile(String userName, String utaID, String phone, String userEmail,
			String password) {
		String query1 = "UPDATE USER " + "SET " + "phone = '" + phone + "', " + "email = '" +
						userEmail + "'"+" "+"WHERE uta_id ='" + utaID + "'" + ";";

		String query2 = "UPDATE credentials " + "SET " + "password = '" + password + "WHERE user_name = '" + userName
				+ "'" + ";";
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query1);
			conn.commit();
			stmt.executeUpdate(query2);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

//	public static boolean UpdateUserDetails(SearchUserAdmin user) {
//		boolean queryStatus = false;
//		String query = "UPDATE USER " + "SET " + "role = '" + user.getRole() + "', " + "phone = '" + user.getPhone()
//				+ "', " + "email = '" + user.getEmail() + "', " + "license_no = '" + user.getLicenseNo() + "', "
//				+ "reason = '" + user.getReason() + "' " + "WHERE uta_id = '" + user.getUta_id() + "'" + ";";
//		System.out.println(query);
//
//		Statement stmt = null;
//		Connection conn = SQLConnection.getDBConnection();
//		try {
//			stmt = conn.createStatement();
//			stmt.executeUpdate(query);
//			conn.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//				stmt.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//		return queryStatus;
//	}

	private static ArrayList<Reservation> ReturnReservedSpotList (String queryString) {
		ArrayList<Reservation> userListInDB = new ArrayList<Reservation>();
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet spotList = stmt.executeQuery(queryString);			
		
			
			while (spotList.next()) {
				Reservation viewreservedspotsuser = new Reservation();
				
				//viewreservedspotsuser.setReserve_id(spotList.getString("reserve_id"));
				
				viewreservedspotsuser.setParking_area_name(spotList.getString("parking_area_name"));
				viewreservedspotsuser.setFloor(spotList.getString("floor"));
//				viewreservedspotsuser.setSpot_num(spotList.getInt("spot_num"));
				viewreservedspotsuser.setSpot_number(spotList.getString("spot_num"));
				viewreservedspotsuser.setParking_type(spotList.getString("type"));
				viewreservedspotsuser.setStart_time(spotList.getString("start_time"));
				viewreservedspotsuser.setDuration(spotList.getString("duration"));
				
				viewreservedspotsuser.setCart_option(spotList.getString("cart"));
				viewreservedspotsuser.setCamera_option(spotList.getString("camera"));
				viewreservedspotsuser.setHistory_option(spotList.getString("history"));
				
				userListInDB.add(viewreservedspotsuser);				
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
		return userListInDB;
	}
	
	public static ArrayList<Reservation> listReservedSpotDetails() {		

		System.out.println(" SELECT * from RESERVATION ORDER BY START_TIME; ");

		return ReturnReservedSpotList(" SELECT * from RESERVATION WHERE STATUS <> 0 ORDER BY START_TIME; ");
	}

	public static String getUTAID(String username) {
		String utaid = "";

		// SET SQL_SAFE_UPDATES = 0; for testing
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = SQLConnection.getDBConnection();
			stmt = conn.createStatement();

			String searchuser = "SELECT * from USER WHERE user_name = '" + username + "';";

			ResultSet userRole = stmt.executeQuery(searchuser);
			if (userRole.next()) {

				utaid = userRole.getString("uta_id");
				
				System.out.println("uta id DAO:" + utaid);
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

		return utaid;

	}
	
	
	public static boolean UpdateUserDetails(SearchManager editedUser) {
		boolean queryStatus = false;
		String query = "UPDATE USER " + "SET " + "role = '" + "phone = '" + editedUser.getPhone()
				+ "', " + "email = '" + editedUser.getEmail() + "' " + "WHERE uta_id = '" + editedUser.getUta_id() + "'" + ";";
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return queryStatus;
	}
		
	public static boolean UpdateUserDetails(SearchUserAdmin user) {
			boolean queryStatus = false;
			String query = "UPDATE USER " + "SET " + "role = '" + user.getRole() + "', " + "phone = '" + user.getPhone()
					+ "', " + "email = '" + user.getEmail() + "', " + "license_no = '" + user.getLicenseNo() + "', "
					+ "reason = '" + user.getReason() + "' " + "WHERE user_name = '" + user.getUserName() + "'" + ";";
			System.out.println(query);
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return queryStatus;
	}
	
	
}
