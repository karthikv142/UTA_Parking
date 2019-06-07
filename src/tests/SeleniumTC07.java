package tests;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import functions.UtaParking_functions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class SeleniumTC07 extends UtaParking_functions {
  public SeleniumTC07()throws FileNotFoundException, IOException {
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
@FileParameters("src/ExcelTestCases/ConfirmReservationTestCases.csv")
  public void testConfirmReservation(int testCaseNumber,String username, String password, String utaID, String parking_type, 
		  String start_time, String duration,String overlapError, String maxError) throws Exception {	
  
  		driver.get(sAppURL);
  
	  //Login
	  	UPA_BF_Login(driver, username,  password);

	  	//RequestReservation
	  	UPA_BF_RequestReservation(driver,parking_type, start_time, duration);

	  	
	    //Confirm Reservation
	  	UPA_BF_ConfirmReservation(driver);
	  	
	  	/******* ASSERT ERROR MESSAGES ********/
	  	if (!maxError.equals("") || !overlapError.equals(""))
		 {
	  		assertEquals(maxError, driver.findElement(By.id(prop.getProperty("Txt_ConfirmReservation_MaxError"))).getAttribute("value"));
	  		assertEquals(overlapError, driver.findElement(By.id(prop.getProperty("Txt_ConfirmReservation_OverlapError"))).getAttribute("value"));

		 }	
	  	
	  	else 
	  	//Make a payment
		  {UPA_BF_MakePayment(driver, "visa", "1001511875000000");}
	  	
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
