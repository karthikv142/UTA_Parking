package uta_parking.model;

import static org.junit.Assert.*;
//import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
@RunWith(JUnitParamsRunner.class)
public class SearchManagerTest {
	private SearchManager sManager;
//	private SearchManagerTestInterface mockObj;

	@Before
	public void setUp() throws Exception {
		sManager = new SearchManager();
//		mockObj = EasyMock.strictMock(SearchManagerTestInterface.class);
	}

	@Test
	@FileParameters("src/BackEndTests/SearchManagerTestCases.csv")
	public void test(int testcaseno, String userName, String uta_id ,String phone,String email, String password,String phoneError,String userEmailError, String passwordError,String errors )
	{	
		sManager = new SearchManager( userName,  phone,  uta_id,  email,  password);
		SearchManagerErrorMsgs messages = new SearchManagerErrorMsgs();
		sManager.setUserName(userName);
		sManager.setUta_id(uta_id);
		sManager.setPhone(phone);
		sManager.setPassword(password);
		sManager.setEmail(email);
		messages = sManager.validateUser(sManager, messages);
		assertEquals(errors,messages.getError());
		assertEquals(phoneError,messages.getPhoneError());
		assertEquals(userEmailError,messages.getUserEmailError());
		assertEquals(passwordError,messages.getPasswordError());	
	}

}