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
public class SeleniumTC20 extends UtaParking_functions {
	public SeleniumTC20() throws FileNotFoundException, IOException {

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
	@FileParameters("src/ExcelTestCases/parkingSpotTestCases.csv")
	public void testParkingSpot(String spotNumber,String floorNumber, String parkingArea,
			String spotError,String floorError,String areaError,String error) throws Exception {

		driver.get(sAppURL);
		String userName = "manager_account";
		String password = "managerpassword";
		
//		Login
		UPA_BF_Login(driver,userName,password);
		UPA_BF_ViewParkingSpotDetails(driver,spotNumber,floorNumber,parkingArea);
		
		if(error.equals("")==false) {
			try {
				assertEquals(areaError, driver.findElement(By.id(prop.getProperty("Id_areaNameError"))).getAttribute("value"));
			} catch (Error e) {
				verificationErrors.append(e.toString());
			}
			try {
				assertEquals(spotError, driver.findElement(By.id(prop.getProperty("Id_spotError"))).getAttribute("value"));
			} catch (Error e) {
				verificationErrors.append(e.toString());
			}
			try {
				assertEquals(floorError, driver.findElement(By.id(prop.getProperty("Id_floorError"))).getAttribute("value"));
			} catch (Error e) {
				verificationErrors.append(e.toString());
			}
		}
		
//		Logout
		UPA_BF_Logout(driver);
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
