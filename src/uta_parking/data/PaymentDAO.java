package uta_parking.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import uta_parking.model.ParkingArea;
import uta_parking.model.Payment;
import uta_parking.util.SQLConnection;

public class PaymentDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	/*
	 * Payment table columns names
	 * 
	 * card_type card_number amount
	 * 
	 */

	public static void insertPayment(Payment payment) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		// Construct the query for inserting a new payment
		String insertPayment = "INSERT INTO PAYMENT (card_type,card_number,amount) ";
		insertPayment += " VALUES ('" + payment.getCredit_card_type() + "','" + payment.getCredit_card_number() + "','"
				+ payment.getAmount() + "');";

		try {
			conn = SQLConnection.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(insertPayment);

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
		}
	}
	
}
