package com.automationsnap.TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automationsnap.Util.ExcelUtility;
import com.automationsnap.Util.ListnerClass;
import com.automationsnap.baseclass.baseClass;
import com.automationsnap.pom.LoginPage;
import com.automationsnap.pom.MainPage;
import com.relevantcodes.extentreports.LogStatus;

/**********************************************************************
 * Test Suite
 * 
 * Description : To validate SignOut with Reason
 * 
 * Pre-Requisite - 1. Application should be running. 2. Test Data should be
 * ready. 3. SSL certificate should be added.
 * 
 * Test Steps: * Login with valid agents. * Click on * End the agent call *
 * Logout.
 * 
 * Expected Result: 1. Successful login of the 3 agents 2. Successful Conference
 * among 3 agents 3. Successful end of conference call 4. Successful log out.
 **********************************************************************/

@Listeners(value = ListnerClass.class)
public class LoginAndLogOutValidation extends baseClass {
	private LoginPage loginPage;
	private MainPage mainPage;
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
			logger.error(e);
		}
		return null;
	}

	/*********************************************************************
	 * Test Suite ==> Login and Logout with valid reason code 1.
	 *********************************************************************/

	@Test(dataProvider = "LoginLogout", description = "validate SignOut feature with valid and invalid reason codes")
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

			/*********************************************************************
			 * Login with user ==> enter valid user name, password and ext
			 * number
			 *********************************************************************/
			
			loginPage.loginWithValidCredentials(username, pwd, ext);
			Thread.sleep(28000);

			/********************************************************************
			 * <======= Expected Result ====>
			 *
			 * 1.Verify Agent Extension log test step pass
			 * 
			 * 2. Verify Agent Extension in different way where it will not show
			 * about report status
			 *********************************************************************/
			
			if (ext.equalsIgnoreCase(mainPage.getAgentExtension())) {
				ListnerClass.test.log(LogStatus.PASS, "Agent is able to login");
			} else {
				ListnerClass.test.log(LogStatus.FAIL,
						"Agent is unable to login");
			}

			Assert.assertEquals(mainPage.getAgentExtension(), ext);

			// User is logging out and providing sign out reason

			mainPage.signOutClick(wrapupReason);
			Thread.sleep(2000);
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
