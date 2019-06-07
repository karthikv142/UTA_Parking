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
public class SeleniumTC19 extends UtaParking_functions{
public SeleniumTC19() throws FileNotFoundException, IOException {
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
}
  
  @Test
  @FileParameters("src/ExcelTestCases/searchUserManager.csv")
  public void testSearchUser(int testCaseNumber, String userName, 
		  String expErrorMsg) throws Exception{
	  
	  driver.get(sAppURL);
	
	  UPA_BF_Login(driver, "manager_account", "managerpassword");
	  UPA_BF_searchUser(driver, userName);
	  

	  try {
		    String errorMsg = driver.findElement(By.id(prop.getProperty("Txt_SearchParkingUserManager_Error"))).getAttribute("value");
		    assertEquals(expErrorMsg, errorMsg);
		    driver.navigate().to("http://localhost:8080/UTAParking/managerHome.jsp");
		    UPA_BF_Logout(driver);
		    }
	    catch(Exception e) {
	    	System.out.println("End of all error generating tests; navigating to managerHome");
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
