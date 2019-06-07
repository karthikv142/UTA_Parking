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
public class SeleniumTC08 extends UtaParking_functions {
  public SeleniumTC08() throws FileNotFoundException, IOException {
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
@FileParameters("src/ExcelTestCases/PaymentTestCases.csv")
  public void testMakeAPayment(String testCaseNumber, String parkingType, String start_time, String duration, String creditCardType, String creditCardNumber, String Options, String paymentError) throws Exception {	
	  
	  		driver.get(sAppURL);
	  
		  //Login
		  	UPA_BF_Login(driver, "midrange_account", "midrangepassword");

		  //RequestReservation
		  	UPA_BF_RequestReservationNoOptions(driver,parkingType, start_time, duration, Options);

		  //Confirm Reservation
		  	UPA_BF_ConfirmReservation(driver);
		  
		  //Make a payment
		  	UPA_BF_MakePayment(driver, creditCardType, creditCardNumber);
		  	
		  	if (!paymentError.equals(""))
		  	{assertEquals(paymentError, driver.findElement(By.name(prop.getProperty("Txt_MakePayment_ErrorMsg"))).getAttribute("value"));}

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
