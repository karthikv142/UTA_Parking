package functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UtaParking_functions {

	public static WebDriver driver;
	public static Properties prop;
	protected String test_delay;

	public UtaParking_functions() throws FileNotFoundException, IOException {
		Properties propDelay = new Properties();
		propDelay.load(new FileInputStream("./Configuration/UPA_Configuration.properties"));
		test_delay = propDelay.getProperty("test_delay");

	}
	
	
	/********** Houda's functions *********/

	public void UPA_BF_Register(WebDriver driver, String sUserName, String sUtaID, String sRole, String sPassword,
			String sPhone, String sEmail, String sLicensePlate) throws InterruptedException {

		// Click the register link in the index page
		driver.findElement(By.linkText(prop.getProperty("Lnk_Register_Register"))).click();

		// Provide profile information
		driver.findElement(By.name(prop.getProperty("Txt_Register_UserName"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_UserName"))).sendKeys(sUserName);
		driver.findElement(By.name(prop.getProperty("Txt_Register_UtaID"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_UtaID"))).sendKeys(sUtaID);
		new Select(driver.findElement(By.name(prop.getProperty("Lst_Register_Role")))).selectByValue(sRole);
		driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).sendKeys(sPassword);
		driver.findElement(By.name(prop.getProperty("Txt_Register_Phone"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Phone"))).sendKeys(sPhone);
		driver.findElement(By.name(prop.getProperty("Txt_Register_Email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Email"))).sendKeys(sEmail);
		driver.findElement(By.name(prop.getProperty("Txt_Register_LicensePlate"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_LicensePlate"))).sendKeys(sLicensePlate);

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// Click the register button
		driver.findElement(By.name(prop.getProperty("Btn_Register_Register"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}

	public void UPA_BF_Login(WebDriver driver, String sUserName, String sPassword) throws InterruptedException {

		// Provide username
		driver.findElement(By.name(prop.getProperty("Txt_Login_UserName"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Login_UserName"))).sendKeys(sUserName);
		// Provide password
		driver.findElement(By.name(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Login_Password"))).sendKeys(sPassword);

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// click on login button
		driver.findElement(By.name(prop.getProperty("Btn_Login_Login"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}

	public void UPA_BF_RequestReservation(WebDriver driver, String sParkingType, String sStart_time, String sDuration)
			throws InterruptedException {
		driver.findElement(By.linkText(prop.getProperty("Lnk_RequestReservation_RequestReservation"))).click();
		new Select(driver.findElement(By.name(prop.getProperty("Lst_RequestReservation_ParkingType"))))
				.selectByVisibleText(sParkingType);
		driver.findElement(By.name(prop.getProperty("Time_RequestReservation_StartTime"))).clear();
		driver.findElement(By.name(prop.getProperty("Time_RequestReservation_StartTime"))).sendKeys(sStart_time);
		driver.findElement(By.name(prop.getProperty("Txt_RequestReservation_Duration"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_RequestReservation_Duration"))).sendKeys(sDuration);
		driver.findElement(By.id(prop.getProperty("CheckBox_RequestReservation_Camera"))).click();
		driver.findElement(By.id(prop.getProperty("CheckBox_RequestReservation_Cart"))).click();
		driver.findElement(By.id(prop.getProperty("CheckBox_RequestReservation_History"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// click request button
		driver.findElement(By.name(prop.getProperty("Btn_RequestReservation_RequestReservation"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}

	public void UPA_BF_RequestReservationNoOptions(WebDriver driver, String sParkingType, String sStart_time,
			String sDuration, String options) throws InterruptedException {
		driver.findElement(By.linkText(prop.getProperty("Lnk_RequestReservation_RequestReservation"))).click();
		new Select(driver.findElement(By.name(prop.getProperty("Lst_RequestReservation_ParkingType"))))
				.selectByVisibleText(sParkingType);
		driver.findElement(By.name(prop.getProperty("Time_RequestReservation_StartTime"))).clear();
		driver.findElement(By.name(prop.getProperty("Time_RequestReservation_StartTime"))).sendKeys(sStart_time);
		driver.findElement(By.name(prop.getProperty("Txt_RequestReservation_Duration"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_RequestReservation_Duration"))).sendKeys(sDuration);

		if (options.equals("1")) {
			driver.findElement(By.id(prop.getProperty("CheckBox_RequestReservation_Camera"))).click();
			driver.findElement(By.id(prop.getProperty("CheckBox_RequestReservation_Cart"))).click();
			driver.findElement(By.id(prop.getProperty("CheckBox_RequestReservation_History"))).click();
		}

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// click request button
		driver.findElement(By.name(prop.getProperty("Btn_RequestReservation_RequestReservation"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}

	public void UPA_BF_ConfirmReservation(WebDriver driver) throws InterruptedException {
		driver.findElement(By.id(prop.getProperty("Rad_RequestReservation_RadioButton_1"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// Click the confirm reservation button
		driver.findElement(By.name(prop.getProperty("Btn_RequestReservation_ConfirmReservation"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}

	public void UPA_BF_MakePayment(WebDriver driver, String sCCType, String sCCNumber) throws InterruptedException {
		// Enter credit card information
		new Select(driver.findElement(By.name(prop.getProperty("Lst_RequestReservation_CreditCardType"))))
				.selectByValue(sCCType);
		driver.findElement(By.name(prop.getProperty("Txt_RequestReservation_CreditCardNumber"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_RequestReservation_CreditCardNumber"))).sendKeys(sCCNumber);

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// Click the pay button ie final step to reserve a parking spot
		driver.findElement(By.name(prop.getProperty("Btn_RequestReservation_MakePayment"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}

	public void UPA_BF_UpdateProfileUser(WebDriver driver, String sField, String sFieldValue)
			throws InterruptedException {

		// Click the update profile link in the user homepage
		driver.findElement(By.linkText(prop.getProperty("Lnk_UpdateProfile_UserHome"))).click();
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		new Select(driver.findElement(By.name(prop.getProperty("Lst_UpdateProfile_FieldToModify"))))
				.selectByValue(sField);
		driver.findElement(By.name(prop.getProperty("Txt_UpdateProfile_FieldToModify"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UpdateProfile_FieldToModify"))).sendKeys(sFieldValue);

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		driver.findElement(By.name(prop.getProperty("Btn_UpdateProfile_UpdateProfile"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}

	public void UPA_BF_ModifyReservation(WebDriver driver) throws InterruptedException {
		driver.findElement(By.id(prop.getProperty("Rad_ModifyReservation_RadioButton_1"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// Click the confirm reservation button
		driver.findElement(By.name(prop.getProperty("Btn_ViewMyReservations_Modify"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}

	public void UPA_BF_CancelReservation(WebDriver driver) throws InterruptedException {
		driver.findElement(By.id(prop.getProperty("Rad_ModifyReservation_RadioButton_1"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// Click the confirm reservation button
		driver.findElement(By.name(prop.getProperty("Btn_ViewMyReservations_Cancel"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}

	
	public void UPA_BF_Logout(WebDriver driver) throws InterruptedException {
		// Click the pay button ie final step to reserve a parking spot
		// driver.findElement(By.cssSelector("input[name=\"logout\"]")).click();
		driver.findElement(By.id(prop.getProperty("Btn_Logout_Logout"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}
	// *******************Venkats Business
	// function***********************************



	public void UPA_BF_SetViolationsManager(WebDriver driver, String sUsername, String sNoShows)
			throws InterruptedException {
		driver.findElement(By.linkText(prop.getProperty("Lnk_ManagerHome_SetViolations"))).click();
		driver.findElement(By.name(prop.getProperty("Txt_SetViolationsParkingUserManager_Search"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_SetViolationsParkingUserManager_Search"))).sendKeys(sUsername);
		
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
		
		driver.findElement(By.id(prop.getProperty("Btn_SetViolationsParkingUserManager_Search"))).click();
		
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
		new Select(driver.findElement(By.name(prop.getProperty("Lst_SetViolations_NoShows"))))
				.selectByVisibleText(sNoShows);
		driver.findElement(By.name(prop.getProperty("Rad_SetViolations_Overstay"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		driver.findElement(By.id(prop.getProperty("Btn_SetViolations_Update"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
	}


	// SEARCH AND EDIT USER BY ADMIN
	public void UPA_BF_SearchEditUser_Admin(WebDriver driver, String sUsername, String Role, String Phone, String Email,
			String License_Plate, String UserStatus, String Reason) throws InterruptedException {
		// ADMIN VIEW& EDIT USER

		driver.findElement(By.linkText(prop.getProperty("Txt_EditUserProfileAdmin_Link"))).click();

		driver.findElement(By.name(prop.getProperty("Txt_SearchParkingUserAdmin_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_SearchParkingUserAdmin_Username"))).sendKeys(sUsername);
		driver.findElement(By.name(prop.getProperty("Btn_SearchParkingUserAdmin_Search"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		driver.findElement(By.name(prop.getProperty("Rad_ViewParkingUserAdmin_Select"))).click();
		driver.findElement(By.id(prop.getProperty("Rad_ViewParkingUserAdmin_Edit"))).click();

		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_Role"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_Role"))).sendKeys(Role);
		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_Phone"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_Phone"))).sendKeys(Phone);
		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_Email"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_Email"))).sendKeys(Email);
		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_LicenseNumber"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_LicenseNumber"))).sendKeys(License_Plate);
		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_UserStatus"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_UserStatus"))).sendKeys(UserStatus);
		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_Reason"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_EditUserProfileAdmin_Reason"))).sendKeys(Reason);

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		driver.findElement(By.name(prop.getProperty("Btn_EditUserProfileAdmin_Submit"))).click();
		
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
	}


	/**
	 * A)& STARTS HERE MODIFY PARKING AREA
	 * @throws InterruptedException 
	 */

	public void UPA_BF_modifyParkingArea(WebDriver driver, String parkingArea, String parkingType, String floor,
			String capacity, String availability, String cart, String camera, String history) throws InterruptedException {

		driver.findElement(By.linkText(prop.getProperty("Lnk_ManagerHome_ModifyAParkingArea"))).click();

		new Select(driver.findElement(By.name(prop.getProperty("Lst_ModifyParkingArea_ParkingArea"))))
				.selectByVisibleText("Maverick");
		new Select(driver.findElement(By.name(prop.getProperty("Lst_ModifyParkingArea_ParkingType"))))
				.selectByVisibleText("Access");
		new Select(driver.findElement(By.name(prop.getProperty("Lst_ModifyParkingArea_Floor"))))
				.selectByVisibleText("1");
		new Select(driver.findElement(By.name(prop.getProperty("Lst_ModifyParkingArea_Capacity"))))
				.selectByVisibleText("0");
		new Select(driver.findElement(By.name(prop.getProperty("Lst_ModifyParkingArea_Availability"))))
				.selectByVisibleText("0");
		new Select(driver.findElement(By.name(prop.getProperty("Lst_ModifyParkingArea_Cart"))))
				.selectByVisibleText("0");
		new Select(driver.findElement(By.name(prop.getProperty("Lst_ModifyParkingArea_Camera"))))
				.selectByVisibleText("0");
		new Select(driver.findElement(By.name(prop.getProperty("Lst_ModifyParkingArea_History"))))
				.selectByVisibleText("0");

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
		
		driver.findElement(By.name(prop.getProperty("Btn_ModifyParkingArea_Submit"))).click();
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
		driver.findElement(By.linkText(prop.getProperty("Lnk_ConfirmationPage_BackToHome"))).click();
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
		driver.findElement(By.id(prop.getProperty("Btn_ManagerHome_Logout"))).click();
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
	}

	/**
	 * MODIFY RESERVATION MANAGER *
	 */
	public void UPA_BF_modifyReservation(WebDriver driver, String duration, String spotNumber, String startTime)
			throws Exception {

		// CLICK MODIFY RESERVATIONS
		driver.findElement(By.linkText(prop.getProperty("Lnk_ManagerHome_ModifyReservations"))).click();
		
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// MAKE A PARTICULAR RESERVATION UNAVAILABLE
		// USING A WEBDRIVER SCRIPT
		driver.findElement(By.xpath("(//input[@name='{item.reserve_id}'])[11]")).click();
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// PROVIDE INPUTS
		driver.findElement(By.name(prop.getProperty("Txt_ModifyReservation_Duration"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ModifyReservation_Duration"))).sendKeys(duration);

		driver.findElement(By.name(prop.getProperty("Txt_ModifyReservation_SpotNum"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ModifyReservation_SpotNum"))).sendKeys(spotNumber);

		driver.findElement(By.name(prop.getProperty("Txt_ModifyReservation_StartTime"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ModifyReservation_StartTime"))).sendKeys(startTime);

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
		// CLICK ON THE SUBMIT BUTTON
		driver.findElement(By.name(prop.getProperty("Btn_ModifyReservation_Submit"))).click();
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}


	/**
	 * SEARCH A USER BEFORE SETTING VIOLATIONS 

	 * @throws InterruptedException *
	 */
	public void UPA_BF_searchUser(WebDriver driver, String userName) {
		// CLICK VIEW PARKING USER DETAILS
		driver.findElement(By.linkText(prop.getProperty("Lnk_ManagerHome_ViewParkingUserDetails"))).click();

		// PROVIDE INPUTS
		driver.findElement(By.name(prop.getProperty("Txt_SearchParkingUserManager_Search"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_SearchParkingUserManager_Search"))).sendKeys(userName);

		// CLICK ON THE SEARCH BUTTON
		driver.findElement(By.name(prop.getProperty("Btn_SearchParkingUserManager_Search"))).click();
	}


	/**
	 * MANAGE PARKING SPOT AVAILABILITY 
	 * @throws InterruptedException *
	 */
	public void UPA_BF_manageParkingSpotAvailability(WebDriver driver, String parkingArea, String parkingType,
			String floor) throws InterruptedException {
		// CLiCK MANAGE PARKING SPOT AVAILABILITY
		driver.findElement(By.linkText(prop.getProperty("Lnk_ManagerHome_ManageParkingSpotAvailability"))).click();
		
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// SELECT PARKING AREA
		new Select(driver.findElement(By.name(prop.getProperty("Lst_ModifyParkingArea_ParkingAreaName"))))
				.selectByVisibleText(parkingArea);

		// SELECT PARKING TYPE
		new Select(driver.findElement(By.name(prop.getProperty("Lst_ModifyParkingArea_ParkingType"))))
				.selectByVisibleText(parkingType);

		// SELECT FLOOR
		new Select(driver.findElement(By.name(prop.getProperty("Lst_ModifyParkingArea_Floor"))))
				.selectByVisibleText(floor);

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
		// CLICK ON SUBMIT BUTTON
		driver.findElement(By.name(prop.getProperty("Btn_ModifyParkingArea_Submit"))).click();
		
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// MAKE A PARTICULAR SPOT UNAVAILABLE
		driver.findElement(By.xpath("(//input[@name='{item.reserve_id}'])[36]")).click();

		// IN CONFIRMATION PAGE
		driver.findElement(By.linkText(prop.getProperty("Lnk_ConfirmationPage_BackToHome"))).click();

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
		// CLICK LOGOUT IN MANAGER HOME
		driver.findElement(By.id(prop.getProperty("Btn_ManagerHome_Logout"))).click();
		
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

		// NAVIGATE TO MANAGER HOME
		// driver.navigate().to("http://localhost:8080/UTAParking/managerHome.jsp");
	}

	public void UPA_BF_deleteReservations(WebDriver driver) throws InterruptedException {
		// CLiCK DELETE RESERVATIONS
		driver.findElement(By.linkText(prop.getProperty("Lnk_ManagerHome_DeleteReservations"))).click();
		  if (test_delay.equals("delay"))
		    	Thread.sleep(2_000);

		// DELETE A PARTICULAR RESERVATION
		driver.findElement(By.xpath("(//input[@name='{item.reserve_id}'])[41]")).click();

		// IN CONFIRMATION PAGE- CHUCK IT
		// driver.findElement(By.linkText(prop.getProperty("Lnk_ConfirmationPage_BackToHome"))).click();

		// NAVIGATE TO MANAGER HOME
		driver.navigate().to("http://localhost:8080/UTAParking/managerHome.jsp");
		
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
	}

	//***************************** srihari functions*************************************************
//	**************************************************************************************************

	public void UPA_BF_ManageParkingArea(WebDriver driver, int basic, int midRange, int premium, int access,
			String areaName, String capacity, String floor) throws InterruptedException {
		
		driver.findElement(By.linkText(prop.getProperty("Lnk_Manage_parking_area"))).click();
		  if (test_delay.equals("delay"))
		    	Thread.sleep(2_000);
		driver.findElement(By.name(prop.getProperty("Txt_addParking_ParkingAreaName"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_addParking_ParkingAreaName"))).sendKeys(areaName);
		driver.findElement(By.name(prop.getProperty("Txt_addParking_capacity"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_addParking_capacity"))).sendKeys(capacity);
		driver.findElement(By.name(prop.getProperty("Txt_addParking_floors"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_addParking_floors"))).sendKeys(floor);
		
		  if (test_delay.equals("delay"))
		    	Thread.sleep(2_000);

		if (basic == 1) {
			driver.findElement(By.id("basic")).click();
		}
		if (midRange == 1) {
			driver.findElement(By.id("midRange")).click();
		}
		if (premium == 1) {
			driver.findElement(By.id("premium")).click();
		}
		if (access == 1) {
			driver.findElement(By.id("access")).click();
		}

		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
		
		driver.findElement(By.name(prop.getProperty("Btn_addparking_add"))).click();
		
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);

	}
	
	public void UPA_BF_ViewParkingSpotDetails(WebDriver driver, String spotNumber, String floorNumber,
			String parkingArea) throws InterruptedException {
		driver.findElement(By.linkText(prop.getProperty("Lnk_ManagerHome_ViewParkingAreaDetais"))).click();
		
		driver.findElement(By.id(prop.getProperty("Txt_areaName"))).clear(); //areaName
		driver.findElement(By.id(prop.getProperty("Txt_areaName"))).sendKeys(parkingArea);
		driver.findElement(By.id(prop.getProperty("Txt_spotNumber"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_spotNumber"))).sendKeys(spotNumber);
		driver.findElement(By.id(prop.getProperty("Txt_floorNumber"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_floorNumber"))).sendKeys(floorNumber);
		
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
		
		driver.findElement(By.name(prop.getProperty("Btn_viewParkingSpot"))).click();
		
		if (test_delay.equals("delay"))
			Thread.sleep(2_000);
		
	}
	
	//***************************** Prathim's functions*************************************************
//	**************************************************************************************************
	
//	/************* Manager Profile *********************/
	
	public void UPA_BF_ManagerProfile(WebDriver driver, String sPhoneNumber, String sEmail, String sPassword) throws InterruptedException{
		driver.findElement(By.linkText(prop.getProperty("Lnk_ManagerHome_UpdateProfile"))).click();
		if (test_delay.equals("delay"))
	    	Thread.sleep(2_000);
		
		driver.findElement(By.name(prop.getProperty("Txt_UpdateProfile_phoneNo"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UpdateProfile_phoneNo"))).sendKeys(sPhoneNumber);
		driver.findElement(By.name(prop.getProperty("Txt_UpdateProfile_email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UpdateProfile_email"))).sendKeys(sEmail);
		driver.findElement(By.name(prop.getProperty("Txt_UpdateProfile_password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UpdateProfile_password"))).sendKeys(sPassword);
	  
	    if (test_delay.equals("delay"))
	    	Thread.sleep(2_000);
	    
	    driver.findElement(By.name(prop.getProperty("Btn_UpdateProfile_Submit"))).click();
	    
		if (test_delay.equals("delay"))
	    	Thread.sleep(2_000);
	    
	}
	
	// VIEW AVAILABLE SPACES
		public void UPA_BF_ViewAvailableSpaces(WebDriver driver, String Availspaces) throws InterruptedException {
			
		    driver.findElement(By.linkText(prop.getProperty("Lnk_ManagerHome_ViewSpaces"))).click();
		    new Select(driver.findElement(By.name(prop.getProperty("DrpDwn_Manager_ParkingType")))).selectByVisibleText(Availspaces);
		    if (test_delay.equals("delay"))
				Thread.sleep(2_000);
		    driver.findElement(By.name(prop.getProperty("Btn_Manager_SearchUserAdminButton"))).click();
			
			if (test_delay.equals("delay"))
		    	Thread.sleep(2_000);
		}

}
