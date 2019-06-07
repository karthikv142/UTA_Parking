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

public class SeleniumTC10 extends UtaParking_functions {
  public SeleniumTC10()throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

private WebDriver driver;
  private String  sAppURL, sSharedUIMapPath;
 // private boolean acceptNextAlert = true;
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
@FileParameters("src/ExcelTestCases/CancelReservationUserTestCases.csv")
  public void testCancelReservation(int testCaseNumber, String username, String password, 
			String parking_type, String start_time, String duration, String credit_card_type, String credit_card_number,
			String errorEmptyReservation, String errorSelection) throws Exception {	
  
  		driver.get(sAppURL);
  
	  //Login
	  	UPA_BF_Login(driver, username,  password);
	  	
	  	Thread.sleep(3000);
	  	
	  	//click on view reservations link
	  	driver.findElement(By.linkText(prop.getProperty("Lnk_ViewMyReservations_User"))).click();
	  	//go back to homepage
	  	
	  	Thread.sleep(3000);
	  	
	  	 try {
	  	      assertEquals(errorEmptyReservation, 
	  	    		  driver.findElement(By.name(prop.getProperty("Txt_ModifyCancel_ErrorMsg"))).getAttribute("value"));
	  	    } catch (Error e) {
	  	      verificationErrors.append(e.toString());
	  	    }
	  	 
	  	Thread.sleep(3000);
	  	
	  	driver.navigate().to("http://localhost:8080/UTAParking/userHome.jsp");

	  	Thread.sleep(3000);
	  	//RequestReservation
	  	UPA_BF_RequestReservation(driver,parking_type, start_time, duration);

	  	
	    //Confirm Reservation
	  	UPA_BF_ConfirmReservation(driver);
	  	
	  	
	  	//Make a payment
		UPA_BF_MakePayment(driver,credit_card_type,credit_card_number);
		
		
		Thread.sleep(3000);
		//click on view reservations link
		driver.findElement(By.linkText(prop.getProperty("Lnk_ViewMyReservations_User"))).click();
		 
		Thread.sleep(3000);
		driver.findElement(By.name(prop.getProperty("Btn_ViewMyReservations_Cancel"))).click();
	
		
		 try {
	  	      assertEquals(errorSelection, 
	  	    		  driver.findElement(By.name(prop.getProperty("Txt_ModifyCancel_ErrorMsg"))).getAttribute("value"));
	  	    } catch (Error e) {
	  	      verificationErrors.append(e.toString());
	  	    }
		 
		 
		 UPA_BF_Logout(driver);
		 
		 UPA_BF_Login(driver, "basic_account",  "basicpassword");
		 
		 //click on view reservations link
		 driver.findElement(By.linkText(prop.getProperty("Lnk_ViewMyReservations_User"))).click();
		 
		 UPA_BF_CancelReservation(driver);
		 

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
