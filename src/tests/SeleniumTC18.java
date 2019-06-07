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
public class SeleniumTC18 extends UtaParking_functions{
public SeleniumTC18() throws FileNotFoundException, IOException {
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
  @FileParameters("src/ExcelTestCases/modifyReservation.csv")
  public void testModifyReservation(int testCaseNumber, String duration, 
		  String spotNumber, String startTime, 
		  String expErrorMsg, String expDurationError, 
		  String expSpotError, String expStartTimeError) throws Exception{
	  
	  driver.get(sAppURL);
	
	  UPA_BF_Login(driver, "manager_account", "managerpassword");
	  UPA_BF_modifyReservation(driver, duration, spotNumber, startTime);
	  
	  String errorMsg = driver.findElement(By.xpath(prop.getProperty("Txt_ModifyReservation_Error"))).getAttribute("value");
	  String durationError = driver.findElement(By.id(prop.getProperty("Txt_ModifyReservation_DurationError"))).getAttribute("value");
	  String spotError = driver.findElement(By.id(prop.getProperty("Txt_ModifyReservation_SpotError"))).getAttribute("value");
	  String startTimeError = driver.findElement(By.id(prop.getProperty("Txt_ModifyReservation_TimeError"))).getAttribute("value");
	  
	  assertEquals(expErrorMsg, errorMsg);	  
	  assertEquals(expDurationError, durationError);
	  assertEquals(expSpotError, spotError);
	  assertEquals(expStartTimeError, startTimeError);
	  
	  driver.navigate().to("http://localhost:8080/UTAParking/managerHome.jsp");
	
	  
	  UPA_BF_Logout(driver);
	  
/**	  
	  if((errorMsg.length() == 0)) {		
		  driver.navigate().to("http://localhost:8081/UTAParking/managerHome.jsp");
		  driver.findElement(By.id(prop.getProperty("Btn_ManagerHome_Logout"))).click();
		  }
*/		  
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
