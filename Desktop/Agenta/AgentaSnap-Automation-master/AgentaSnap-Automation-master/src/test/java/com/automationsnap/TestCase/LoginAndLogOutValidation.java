package com.automationsnap.TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automationsnap.Util.ExcelUtility;
import com.automationsnap.Util.ListnerClass;
import com.automationsnap.Util.Util;
import com.automationsnap.baseclass.baseClass;
import com.automationsnap.pom.LoginPage;
import com.automationsnap.pom.MainPage;

/**********************************************************************
 * Test Suite
 * 
 * Description1 : To validate SignOut with Reason
 * 
 * Pre-Requisite - 1. Application should be running. 2. Test Data should be
 * ready. 3. SSL certificate should be added.
 * 
 * Test Steps: * Login with valid agent. * Click on SignOut button * Select the
 * Reason Code and logout
 * 
 * Expected Result: 1. Successful login 2. should able to click on SignOut
 * button 3. should select the reason code. 4. Successfully logout
 * ========================================================================
 * Description2 : To validate SignOut and NotReady with Reason code when scroll
 * is activated for more RC.
 * 
 * Pre-Requisite - 1. Application should be running. 2. Test Data should be
 * ready. 3. SSL certificate should be added.
 * 
 * Test Steps: * Login with valid agent. * Click on NotReady dropdown(dd)
 * *Scroll Down * Select the Reason Code * Click on SignOut button * Select the
 * Reason Code and logout
 * 
 * Expected Result: 1. Successful login 2. should able to click on DD 3. able to
 * scroll down and select reason code 4. should able to click on SignOut button
 * 5. should select the reason code. 6. Successfully logout
 * ========================================================================
 * Description3 : To validate SignOut w/o RC
 * 
 * Pre-Requisite - 1. Application should be running. 2. Test Data should be
 * ready. 3. SSL certificate should be added.
 * 
 * Test Steps: * Login with valid agent. Click on SignOut button * logout
 * 
 * Expected Result: 1. Successful login 2. should able to click on SignOut
 * button 3. Successfully logout
 **********************************************************************/

@Listeners(value = ListnerClass.class)
public class LoginAndLogOutValidation extends baseClass {
	private LoginPage loginPage;
	private MainPage mainPage;
	private Util util;
	private static Logger logger = LogManager
			.getLogger(LoginAndLogOutValidation.class.getName());

	/**********************************************************************
	 * To get the data set from the Excel file we are using @DataProvider
	 * annotation. Type should be double array.
	 * 
	 * Whatever name providing inside the annotation same value need to set
	 * where we are going to use that data set.
	 **********************************************************************/

	@DataProvider(name = "LoginLogout")
	public Object[][] getDataforTest1() {
		try {
			Object[][] testData = ExcelUtility.getTestData("LoginLogout");
			logger.debug("data provider called for LoginLogout");
			return testData;
		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}
		return null;
	}

	/*********************************************************************
	 * Test Suite ==> Login and Logout with valid reason code 1.
	 *********************************************************************/

	@Test(dataProvider = "LoginLogout", description = "validate SignOut feature with valid and invalid reason codes", enabled = true)
	public void to_Validate_Logout_with_Reason_Code(String url,
			String username, String pwd, String ext, String wrapupReason)
			throws InterruptedException {
		try {
			// Chrome browser will open.
			baseClass.openApp(driver, url);
			Thread.sleep(10000);
			logger.debug(driver);

			/*********************************************************************
			 * Creating POM object for desired class for login feature ==> need
			 * to create object referring LoginPage class for sign out feature
			 * ==> create object referring MainPage class
			 *********************************************************************/

			loginPage = PageFactory.initElements(driver, LoginPage.class);
			mainPage = PageFactory.initElements(driver, MainPage.class);
			util = PageFactory.initElements(driver, Util.class);

			/*********************************************************************
			 * Login with user ==> enter valid user name, password and ext
			 * number
			 *********************************************************************/

			loginPage.loginWithValidCredentials(username, pwd, ext);
			util.isElementClickable_Wait(mainPage.signout);

			/********************************************************************
			 * <======= Expected Result ====>
			 *
			 * 1.Verify Agent Extension log test step pass
			 * 
			 * 2. Verify Agent Extension in different way where it will not show
			 * about report status
			 *********************************************************************/

			mainPage.isUserLogIn(ext);

			// User is logging out and providing sign out reason

			mainPage.signOut_Click();

			mainPage.select_SignOut_ReasonCode(wrapupReason);
			
			loginPage.isUserLogOut();
		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}
	}

	@DataProvider(name = "LoginLogoutScrollRC")
	public Object[][] getDataforTest2() {
		Object[][] testData = ExcelUtility.getTestData("LoginLogoutScrollRC");
		logger.debug("data provider called for LoginLogout");
		return testData;
	}

	@Test(dataProvider = "LoginLogoutScrollRC", enabled = true)
	public void to_Validate_SignOut_And_NotReady_Scroll_Select_ReasonCode(
			String url, String username, String pwd, String ext,
			String notReadyRC, String signOutRC) throws InterruptedException {

		try {

			baseClass.openApp(driver, url);

			loginPage = PageFactory.initElements(driver, LoginPage.class);
			mainPage = PageFactory.initElements(driver, MainPage.class);
			util = PageFactory.initElements(driver, Util.class);

			Thread.sleep(10000);
			logger.debug(driver);

			// Login with user
			loginPage.loginWithValidCredentials(username, pwd, ext);
			util.isElementClickable_Wait(mainPage.signout);

			mainPage.isUserLogIn(ext);

			mainPage.readyNotReadyDD_Click();

			util.scrollInto(driver,
					mainPage.element_SelectNot_Ready(notReadyRC));

			mainPage.selectNotReady_click(notReadyRC);

			Thread.sleep(2000);
			
			String actualRes = mainPage.selectedNotReadyReasonCode();
			System.out.println(actualRes);

			mainPage.isNotReadyRCMatched(notReadyRC, actualRes);

			// Click on Sign out button
			mainPage.signOut_Click();

			// Scroll move into SignOutRC element
			util.scrollInto(driver,
					mainPage.element_SignOutReasonCode(signOutRC));

			// Click on SignOut Reason Code
			mainPage.select_SignOut_ReasonCode(signOutRC);

			loginPage.isUserLogOut();

		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}
	}

	@DataProvider(name = "WithoutReasonCode")
	public Object[][] getDatafromExcel() {
		Object[][] testData = ExcelUtility
				.getTestData("LogoutWithoutReasonCode");
		logger.debug("data provider called for LogoutWithoutReasonCode");
		return testData;
	}

	@Test(dataProvider = "WithoutReasonCode", description = "validate SignOut feature without Reason Code", enabled = true)
	public void to_Validate_Logout_without_Reason_Code(String url,
			String username, String pwd, String ext)
			throws InterruptedException {

		try {

			baseClass.openApp(driver, url);

			loginPage = PageFactory.initElements(driver, LoginPage.class);
			mainPage = PageFactory.initElements(driver, MainPage.class);
			util = PageFactory.initElements(driver, Util.class);

			Thread.sleep(10000);
			logger.debug(driver);

			// Login with user
			loginPage.loginWithValidCredentials(username, pwd, ext);
			util.isElementClickable_Wait(mainPage.signout);

			mainPage.isUserLogIn(ext);

			mainPage.signOut_Click_Without_RC();
			util.isElementClickable_Wait(loginPage.signIn);

			loginPage.isUserLogOut();
		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}

	}
}
