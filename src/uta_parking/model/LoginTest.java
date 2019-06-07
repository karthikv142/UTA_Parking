package uta_parking.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
@RunWith(JUnitParamsRunner.class)
public class LoginTest {
	private Login logintest;

	@Before
	public void setUp() throws Exception {
		logintest = new Login();
	}

	@Test
	@FileParameters("/src/BackEndTests/LoginTestCase.csv")
	public void test(String testcaseno, String userName, String password, String checkerror) {
		LoginErrorMsgs loginerror1 = new LoginErrorMsgs();
		String loginerror;
		logintest.setUsername(userName);
		logintest.setPassword(password);
		loginerror = logintest.validateLogin(logintest.getUsername(), logintest.getPassword());
		assertEquals(checkerror,loginerror);
	}

}
