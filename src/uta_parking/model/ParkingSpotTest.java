package uta_parking.model;

import static org.junit.Assert.*;
//import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
@RunWith(JUnitParamsRunner.class)
public class ParkingSpotTest {
	private ParkingSpot parkingSpot;

	@Before
	public void setUp() throws Exception {
		parkingSpot = new ParkingSpot();
	}

	@Test
	@FileParameters("src/BackEndTests/ParkingSpotsTests.csv")
	public void test(int testcaseno, String parkingAreaName, String spot, String floor, String parkingAreaNameError, String spotError, String floorError, String errors )
	{	
		ParkingSpotErrorMsgs messages = new ParkingSpotErrorMsgs();
		parkingSpot.setParkingAreaName(parkingAreaName);
		parkingSpot.setSpot(spot);
		parkingSpot.setFloor(floor);
		messages = parkingSpot.validateParkingSpotDetails(parkingSpot);
		assertEquals(errors,messages.getParkingSpotErrors());
		assertEquals(parkingAreaNameError,messages.getAreaNameError());
		assertEquals(spotError,messages.getSpotError());
		assertEquals(floorError,messages.getFloorError());
	}

}