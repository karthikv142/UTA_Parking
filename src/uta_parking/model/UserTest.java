package uta_parking.model;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;


@RunWith(JUnitParamsRunner.class)
public class UserTest {
	User user;
	UserErrorMsgs errorMsgs;

	@Before
	public void setUp() throws Exception {
		user = new User();
		errorMsgs = new UserErrorMsgs();
	}

	@Test
	@FileParameters("src/BackEndTests/UserTestCases.csv")
	public void test(int testCaseNumber, String username, String utaID, String role, String password,
			String phone, String email, String license_plate, String errorMsg, 
			String usernameError, String utaIDError, String roleError, String passwordError,
			String phoneError, String emailError, String license_plateError) {
		
		
		user.setUser(username, utaID, role, password, phone, email, license_plate);
		
		user.validateUser(user, errorMsgs);

		assertEquals(errorMsg, errorMsgs.getErrorMsg());
		assertEquals(usernameError, errorMsgs.getUsernameError());
		assertEquals(utaIDError, errorMsgs.getUtaIDError());
		assertEquals(roleError, errorMsgs.getRoleError());
		assertEquals(passwordError, errorMsgs.getPasswordError());
		assertEquals(phoneError, errorMsgs.getPhoneError());
		assertEquals(emailError, errorMsgs.getEmailError());
		assertEquals(license_plateError, errorMsgs.getLicense_plateError());
	
}
	
}
