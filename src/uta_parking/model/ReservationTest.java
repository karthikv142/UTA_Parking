package uta_parking.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;


@RunWith(JUnitParamsRunner.class)
public class ReservationTest {
	Reservation reservation;
	ReservationErrorMsgs errorMsgs;

	@Before
	public void setUp() throws Exception {
		reservation = new Reservation();
		errorMsgs = new ReservationErrorMsgs();
	}

	@Test
	@FileParameters("src/BackEndTests/ReservationTestCases.csv")
	public void test(int testCaseNumber,String currentUtaID, String parking_type, int currentHours, int currentMinutes,
			String start_time, String duration, String errorMsg, String parking_typeError,
			String start_timeError, String durationError) {
		
			
		reservation.setReservation("", parking_type, "", "", "", start_time, duration, "", "", "","", "");
		
		reservation.validateReservation(reservation, errorMsgs, currentUtaID, currentHours, currentMinutes);

		assertEquals(errorMsg, errorMsgs.getErrorMsg());
		assertEquals(parking_typeError, errorMsgs.getParking_typeError());
		assertEquals(start_timeError, errorMsgs.getStart_timeError());
		assertEquals(durationError, errorMsgs.getDurationError());
	
}
	
}
