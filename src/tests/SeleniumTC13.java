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
public class SeleniumTC13 extends UtaParking_functions{
	 public SeleniumTC13() throws FileNotFoundException, IOException {
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
  @FileParameters("src/ExcelTestCases/UpdateManagerProfile.csv")
  public void testUpdateManagerProfile(int testCaseNumber, String phone, String email, String password, String expPhoneErrorMsg, String expEmailErrorMsg, String expPasswordErrorMsg) throws Exception {
    driver.get(sAppURL);
    
    UPA_BF_Login(driver, "manager_account", "managerpassword");
    
    UPA_BF_ManagerProfile(driver, phone, email, password);
    
    if(!expEmailErrorMsg.equals("") || !expPasswordErrorMsg.equals("") || !expPhoneErrorMsg.equals("")) {
	String passwordError = driver.findElement(By.name(prop.getProperty("Txt_UpdateProfile_PasswordError"))).getAttribute("value");
	String phoneError = driver.findElement(By.name(prop.getProperty("Txt_UpdateProfile_PhoneError"))).getAttribute("value");
	String emailError = driver.findElement(By.name(prop.getProperty("Txt_UpdateProfile_EmailError"))).getAttribute("value");

	assertEquals(expPhoneErrorMsg, phoneError);
	assertEquals(expPasswordErrorMsg, passwordError);
	assertEquals(expEmailErrorMsg, emailError);
    }else {
//	driver.findElement(By.name(prop.getProperty("Btn_UpdateProfile_Submit"))).click();
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
