package uta_parking.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@SuppressWarnings("unused")
@RunWith(JUnitParamsRunner.class)
public class SearchUserAdminTest {
	SearchUserAdmin user;
	SearchUserErrorMsgs errors;
	

	@Before
	public void setUp() throws Exception {
		user = new SearchUserAdmin();
		errors = new SearchUserErrorMsgs();
	}

	@Test
	@FileParameters("src/BackEndTests/searchUserAdminTestCases.csv")
	public void testValidateUser(String license,String phone, String reason, 
								String role, String email, String status,
								String licenseError, String phoneError, String reasonError, 
								String roleError, String emailError,String statusError,
								String err) {
			user.setLicenseNo(license);
			user.setEmail(email);
			user.setPhone(phone); 	
			user.setStatus(status);
			user.setReason(reason);
			user.setRole(role);
			
			errors = user.validateUser(user, errors);
			assertEquals(licenseError,errors.getLicenseError());
			assertEquals(phoneError,errors.getPhoneError());
			assertEquals(reasonError,errors.getReasonError());
			assertEquals(roleError,errors.getRoleError());
			assertEquals(emailError,errors.getUserEmailError());
			assertEquals(statusError,errors.getUserStatusError());
			assertEquals(err,errors.getError());
	}

}
