package com.automationsnap.TestCase;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import com.relevantcodes.extentreports.LogStatus;

/**********************************************************************
 * Test Suite
 * 
 * Description 1: To validate NotReady with Reason code
 *
 * Pre-Requisite - 1. Application should be running. 2. Test Data should be
 * ready. 3. SSL certificate should be added.
 *
 * Test Steps: * Login with valid agent. * Click on NotReady reson codes list*
 * Select any NotReady reason code *
 *
 *
 * Expected Result: 1. Successful login of the agent 2. Selected Not ready
 * reason code should be disabled and rest of all reason codes should be
 * enabled.
 * ======================================================================== *
 * Description 2: To validate NotReady Reason code after refresh
 *
 * Pre-Requisite - 1. Application should be running. 2. Test Data should be
 * ready. 3. SSL certificate should be added.
 *
 * Test Steps: 1. Login with valid agents. 2 Select any Not Ready Reason code
 * 3.Refresh the application 4. Validate the reason code. 5.Logout
 *
 * Expected Result: 1. Successful login of the agent 2. Selected reason code
 * before and after refresh should be same. 3.Successful log out.
 * 
 **********************************************************************/

@Listeners(value = ListnerClass.class)
public class NotReadyReasonCodeValidation extends baseClass {

	private LoginPage loginPage;
	private MainPage mainPage;
	private Util util;
	private static Logger logger = LogManager
			.getLogger(NotReadyReasonCodeValidation.class.getName());

	@DataProvider(name = "NotReadyResaonCodeSelect")
	public Object[][] getDataforTest1() {
		try {
			Object[][] testData = ExcelUtility
					.getTestData("NotReadyResaonCodeSelect");
			logger.debug("data provider called for NotReadyRC validation");
			return testData;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Test(dataProvider = "NotReadyResaonCodeSelect", description = "validate SignOut feature with valid and invalid reason codes", enabled = true)
	public void to_Validate_Selected_NotReady_RC_State(String url,
			String username, String pwd, String ext, String notReadyRC,
			String signOut_RC) throws InterruptedException {
		try {

			// Open app
			baseClass.openApp(driver, url);
			Thread.sleep(10000);

			// Creating object for POM classes
			loginPage = PageFactory.initElements(driver, LoginPage.class);
			mainPage = PageFactory.initElements(driver, MainPage.class);
			loginPage.loginWithValidCredentials(username, pwd, ext);
			util = PageFactory.initElements(driver, Util.class);

			// added wait condition
			util.isElementClickable_Wait(mainPage.signout);
			
			mainPage.isUserLogIn(ext);

			// click on ready and not ready reason code list tab
			mainPage.readyNotReadyDD_Click();

			/*
			 * driver.findElement( By.xpath("//a[contains(text(),'Not Ready - "
			 * + notReadyRC + "')]")).click();
			 */
			mainPage.selectNotReady_click(notReadyRC);

			Thread.sleep(1000);

			mainPage.readyNotReadyDD_Click();
			List<WebElement> values = MainPage.listNotreadyDropdown
					.findElements(By.tagName("a"));
			int count = 0;
			for (WebElement value : values) {
				if ((value.getAttribute("class").equalsIgnoreCase("disabled"))) {
					if (value.getText().contains(notReadyRC)) {
						logger.debug("test case pass");
						count++;
					}
				} else {
					logger.debug("Test case is executing");
				}
			}

			if (count != 0 && count == 1) {
				ListnerClass.test.log(LogStatus.PASS, "only " + notReadyRC
						+ " reason code should disable");
			} else {
				ListnerClass.test.log(LogStatus.FAIL, "only " + notReadyRC
						+ " reason code should disable");
			}

			mainPage.signOut_Click();
			mainPage.select_SignOut_ReasonCode(signOut_RC);

			loginPage.isUserLogOut();

		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));

		}
	}

	@DataProvider(name = "NotReadyRefresh")
	public Object[][] getDataforTest2() {
		Object[][] testData = ExcelUtility.getTestData("NotReadyRefresh");
		logger.debug("data provider called for NotReady_Refresh");
		return testData;
	}

	@Test(dataProvider = "NotReadyRefresh", description = "validate sign in and apply reason code and reload", enabled = true)
	public void to_Validate_NotReady_RC_AfterRefresh(String url, String username,
			String pwd, String ext, String notReadyRC, String signOutRC)
			throws InterruptedException {
		try {
			baseClass.openApp(driver, url);
			
			Thread.sleep(10000);

			loginPage = PageFactory.initElements(driver, LoginPage.class);
			mainPage = PageFactory.initElements(driver, MainPage.class);
			util = PageFactory.initElements(driver, Util.class);
			logger.debug(driver);

			// Login with user
			loginPage.loginWithValidCredentials(username, pwd, ext);
			// added wait condition
			util.isElementClickable_Wait(mainPage.signout);

			mainPage.isUserLogIn(ext);

			mainPage.readyNotReadyDD_Click();
			mainPage.selectNotReady_click(notReadyRC);
			Thread.sleep(3000);

			// Refresh the page
			driver.navigate().refresh();
			util.isElementClickable_Wait(mainPage.signout);

			// wait.isElementClickable_Wait(driver, mainPage.signout);
			String actualRes = mainPage.selectedNotReadyReasonCode();
			mainPage.isNotReadyRCMatched(notReadyRC, actualRes);
			
			Util.logout(mainPage, loginPage, signOutRC);

		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}
	}

}
