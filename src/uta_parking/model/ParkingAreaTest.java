package uta_parking.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@SuppressWarnings("unused")
@RunWith(JUnitParamsRunner.class)
public class ParkingAreaTest {
	private ParkingArea parkingArea;
//	private ParkingAreaTestInterface mockObj;
	@Before
	public void setUp() throws Exception {
		parkingArea = new ParkingArea();
//		mockObj = EasyMock.strictMock(ParkingAreaTestInterface.class);
	}

//	@Test
//	@FileParameters("src/uta_parking/model/parkingAreaTestCapacity.csv")
//	public void testValidateCapacity(String capacity, String ExpOut) {
//		assertEquals(ExpOut,parkingArea.validateCapacity(capacity));		
//	}
//
//	@Test
//	@FileParameters("src/uta_parking/model/parkingAreaTestAreaName.csv")
//	public void testValidateArea(String area , String ExpOut) {
//		assertEquals(ExpOut,parkingArea.validateArea(area));		
//	}
//
//	@Test
//	@FileParameters("src/uta_parking/model/parkingAreaTestFloor.csv")
//	public void testValidateFloor(String floor, String ExpOut) { 
//		assertEquals(ExpOut,parkingArea.validateFloor(floor));
//	}
//
//	@Test
//	@FileParameters("src/uta_parking/model/parkingAreaTestType.csv")
//	public void testValidateType(String basic, String access, String midrange,String premium, String ExpOutput) {
//		parkingArea = new ParkingArea();
//		parkingArea.setAccess(access);
//		parkingArea.setBasic(basic);
//		parkingArea.setMidRange(midrange);
//		parkingArea.setPremium(premium);
//		assertEquals(ExpOutput, parkingArea.validateType(parkingArea));
//	}
	
	@Test
	@FileParameters("src/BackEndTests/parkingAreaTestCases.csv")
	public void testValidateParkingArea(String capacity, String floor, String areaName, String access, String basic,String midrange,String premium,
										String capError, String floorError, String nameErrors, String typeError,String genError) {
		parkingArea= new ParkingArea();
		ParkingAreaErrorMsgs errors = new ParkingAreaErrorMsgs();
		parkingArea.setCapacity(capacity);
		parkingArea.setFloor(floor);
		parkingArea.setParkingAreaName(areaName);
		parkingArea.setAccess(access);
		parkingArea.setBasic(basic);
		parkingArea.setMidRange(midrange);
		parkingArea.setPremium(premium);
		errors = parkingArea.validateParkingArea(parkingArea);		
		assertEquals(capError, errors.getCapacityError());
		assertEquals(floorError, errors.getFloorError());
		assertEquals(nameErrors, errors.getParking_areanameError());
		assertEquals(typeError, errors.getTypeError());
		assertEquals(genError, errors.getErrorMsg());
	}
}
