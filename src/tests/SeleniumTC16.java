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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import functions.UtaParking_functions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class SeleniumTC16 extends UtaParking_functions{
public SeleniumTC16() throws FileNotFoundException, IOException {
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
  @FileParameters("src/ExcelTestCases/modifyParkingSpot.csv")
  public void testSeleniumManageParkingSpot(int testCaseNo, String parkingArea,
		  String parkingType, String floor)throws Exception {
    driver.get(sAppURL);
    
	UPA_BF_Login(driver, "manager_account", "managerpassword");
    UPA_BF_manageParkingSpotAvailability(driver, parkingArea, parkingType, floor);  
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
