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
public class SeleniumTC01 extends UtaParking_functions {
  public SeleniumTC01() throws FileNotFoundException, IOException {
		
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
@FileParameters("src/ExcelTestCases/RegisterTestCases.csv")
  public void testSeleniumTC01(int testCaseNumber, String username, String utaID, String role, String password, String phone,
	  String email, String license_plate, String expErrorMsg, String expUserNameError, String expUtaIDError, String expRoleError, 
	  String expPasswordError, String expPhoneError, String expEmailError, String expLicensePlateError) throws Exception {
  
	driver.get(sAppURL);
	
	
	UPA_BF_Register(driver, username, utaID, role, password, phone, email, license_plate);
	
	String errorMsg = driver.findElement(By.name(prop.getProperty("Txt_Register_ErrorMsg"))).getAttribute("value");
	String userNameError = driver.findElement(By.name(prop.getProperty("Txt_Register_UserNameError"))).getAttribute("value");
	String utaIDError = driver.findElement(By.name(prop.getProperty("Txt_Register_UtaIDError"))).getAttribute("value");
	String roleError = driver.findElement(By.name(prop.getProperty("Txt_Register_RoleError"))).getAttribute("value");
	String passwordError = driver.findElement(By.name(prop.getProperty("Txt_Register_PasswordError"))).getAttribute("value");
	String phoneError = driver.findElement(By.name(prop.getProperty("Txt_Register_PhoneError"))).getAttribute("value");
	String emailError = driver.findElement(By.name(prop.getProperty("Txt_Register_EmailError"))).getAttribute("value");
	String licensePlateError = driver.findElement(By.name(prop.getProperty("Txt_Register_LicensePlateError"))).getAttribute("value");
  
  	
	
	assertEquals(expErrorMsg, errorMsg);
	assertEquals(expUserNameError, userNameError);
	assertEquals(expUtaIDError, utaIDError);
	assertEquals(expRoleError,roleError);
	assertEquals(expPasswordError, passwordError);
	assertEquals(expPhoneError, phoneError);
	assertEquals(expEmailError, emailError);
	assertEquals(expLicensePlateError, licensePlateError);
	
	driver.findElement(By.linkText(prop.getProperty("Lnk_Register_GoBackToLogin"))).click();
	
	 if (test_delay.equals("delay"))
	    	Thread.sleep(2_000);
	
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
