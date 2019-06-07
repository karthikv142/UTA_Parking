package uta_parking.model;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;


@RunWith(JUnitParamsRunner.class)
public class OptionsTest {
	
	Options options;

	@Before
	public void setUp() throws Exception {
		 options = new Options();
	}

	@Test
	@FileParameters("src/BackEndTests/OptionsTestCases.csv")
	public void test(int testCaseNumber, String camera_option, String cart_option, String history_option, String dayName, String start_time, String duration, String tax, double totalCost, String comments) {
	
	assertEquals(totalCost,options.calculateTotalCost(dayName, start_time, duration, camera_option, cart_option, history_option),0.01);
	
}
	
}
