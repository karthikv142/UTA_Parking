//package com.example.tests;
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import functions.UtaParking_functions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class SeleniumTC22 extends UtaParking_functions{
	 public SeleniumTC22() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	private WebDriver driver;
	  private String  sAppURL, sSharedUIMapPath;
	  //private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  
	  prop = new Properties();
		prop.load(new FileInputStream("./Configuration/UPA_Configuration.properties"));
		
		/**************** geckodriver ****************/
		String myPath = "";
				
				if (prop.getProperty("os").equals("windows"))
						{myPath = "C:\\GeckoSelenium\\geckodriver.exe";}
				else
						{myPath = "/Users/houda/documents/GeckoSelenium/geckodriver";}
		
		System.setProperty("webdriver.firefox.marionette", myPath);
		
		/**********************************************/
		
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    
	    sAppURL = prop.getProperty("sAppURL");
	    sSharedUIMapPath = prop.getProperty("SharedUIMap");
	    
		prop.load(new FileInputStream(sSharedUIMapPath));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  @FileParameters("src/ExcelTestCases/UserAdminProfile.csv")
  public void testUserAdminProfile(int testCaseNumber,String sUsername ,String Role, String Phone, String Email, String License_Plate,String UserStatus,String Reason ,String expRoleErrorMsg,String expPhoneErrorMsg ,String expEmailErrorMsg,String expLicensePlateErrorMsg,String expUserStatusErrorMsg,String expReasonErrorMsg) throws Exception {
	  driver.get(sAppURL);
	    UPA_BF_Login(driver, "seleniumadmin", "adminpassword");

//    Search Parking User
//    UPA_BF_SearchEditUser_Admin(driver, "user_account", "manager", "1001001000",
//    		"changed@uta.edu", "ARF9999", "0", "overstay");

	UPA_BF_SearchEditUser_Admin( driver,sUsername,Role,Phone,Email,License_Plate,UserStatus,Reason);
	    
	if(!expEmailErrorMsg.equals("") || !expLicensePlateErrorMsg.equals("")|| !expPhoneErrorMsg.equals("")|| !expReasonErrorMsg.equals("")||!expRoleErrorMsg.equals("")|| !expUserStatusErrorMsg.equals("")) {    
	String reasonError = driver.findElement(By.name(prop.getProperty("Txt_EditUserProfileAdmin_ReasonError"))).getAttribute("value");
	String phoneError = driver.findElement(By.name(prop.getProperty("Txt_EditUserProfileAdmin_PhoneError"))).getAttribute("value");
	String emailError = driver.findElement(By.name(prop.getProperty("Txt_EditUserProfileAdmin_EmailError"))).getAttribute("value");
	String roleError = driver.findElement(By.name(prop.getProperty("Txt_EditUserProfileAdmin_RoleError"))).getAttribute("value");
	String licensePlateError = driver.findElement(By.name(prop.getProperty("Txt_EditUserProfileAdmin_LicensePlateError"))).getAttribute("value");
	String userStatusError = driver.findElement(By.name(prop.getProperty("Txt_EditUserProfileAdmin_StatusError"))).getAttribute("value");
	
//	assertEquals(expErrorMsg, errorMsg);
	assertEquals(expRoleErrorMsg, roleError);
	assertEquals(expPhoneErrorMsg, phoneError);
	assertEquals(expEmailErrorMsg, emailError);
	assertEquals(expReasonErrorMsg, reasonError);
	assertEquals(expLicensePlateErrorMsg, licensePlateError);
	assertEquals(expUserStatusErrorMsg, userStatusError);
	}else {
    //Logout
  	UPA_BF_Logout(driver);
	}

  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }


}
