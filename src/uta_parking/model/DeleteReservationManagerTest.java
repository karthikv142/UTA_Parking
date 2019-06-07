package uta_parking.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class DeleteReservationManagerTest {
	DeleteReservationsManager deletereservationsmanager;
	DeleteReservationsManagerErrorMessages deletereservationsmanagererrormessages; 

	@Before
	public void setUp() throws Exception {
		deletereservationsmanager = new DeleteReservationsManager();
		deletereservationsmanagererrormessages = new DeleteReservationsManagerErrorMessages();
	}

	@Test
	@FileParameters("src/BackEndTests/ModifyReservationTestCases.csv")
	public void test(int testcaseNo, String action, String duration, String spot_num, 
			String start_time, String errorMsg, String durationError, String spotError, 
			String timeError)
	{	
//		
		deletereservationsmanager.setDuration(duration);
		deletereservationsmanager.setSpot_num(spot_num);
		deletereservationsmanager.setStart_time(start_time);
		deletereservationsmanagererrormessages = deletereservationsmanager.validateDeleteReservation();
		assertEquals(errorMsg,deletereservationsmanagererrormessages.getErrorExist());
		assertEquals(durationError,deletereservationsmanagererrormessages.getDuration());
		assertEquals(spotError,deletereservationsmanagererrormessages.getSpot_num());
		assertEquals(timeError,deletereservationsmanagererrormessages.getStart_time());
	}
}