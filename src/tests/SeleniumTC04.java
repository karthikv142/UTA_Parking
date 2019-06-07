//package com.example.tests;
package tests;

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

public class SeleniumTC04 extends UtaParking_functions{
	 public SeleniumTC04() throws FileNotFoundException, IOException {
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
  @FileParameters("src/ExcelTestCases/TC04.csv")
  public void testSeleniumTC04(int testCaseNumber, String username, String utaID, String role, String password,
		  String phone,String email, String license_plate, String userToModify, String noShows) throws Exception {
    driver.get(sAppURL);
    
//		Registration for Manager
    UPA_BF_Register(driver,  username,  utaID,  role,  password, phone, email,  license_plate);
    
    driver.findElement(By.linkText(prop.getProperty("Lnk_Register_GoBackToLogin"))).click();
    
    UPA_BF_Login(driver, username, password);
//    Search Parking User
    UPA_BF_searchUser(driver, userToModify);
    
    
    driver.navigate().to("http://localhost:8080/UTAParking/managerHome.jsp");
 
//    Set Violations
    UPA_BF_SetViolationsManager(driver,userToModify, noShows);
    
    //Logout
  	UPA_BF_Logout(driver);


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
