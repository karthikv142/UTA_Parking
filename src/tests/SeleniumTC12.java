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
public class SeleniumTC12 extends UtaParking_functions {
  public SeleniumTC12()throws FileNotFoundException, IOException {
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
  @FileParameters("src/ExcelTestCases/ViewStatusTestCases.csv")
    public void testViewReservationStatus(int testCaseNumber,String username, String password, String status, String overstay, 
  		  String noshow) throws Exception {	
  
  		driver.get(sAppURL);
  
	  //Login
	  	UPA_BF_Login(driver, username,  password);
	  	
	  	//click on view reservations link
	  	driver.findElement(By.linkText(prop.getProperty("Lnk_ViewMyStatus_User"))).click();
	  	
	  	Thread.sleep(2000);

		
	  	assertEquals(status, driver.findElement(By.id(prop.getProperty("Txt_ViewMyStatus_Status"))).getAttribute("value")); 
	  	assertEquals(overstay, driver.findElement(By.id(prop.getProperty("Txt_ViewMyStatus_Overstay"))).getAttribute("value"));
	  	assertEquals(noshow, driver.findElement(By.id(prop.getProperty("Txt_ViewMyStatus_Noshow"))).getAttribute("value"));


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
