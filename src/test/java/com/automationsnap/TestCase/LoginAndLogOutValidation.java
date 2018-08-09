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


@Listeners(value = ListnerClass.class)
public class LoginAndLogOutValidation extends baseClass {
	private LoginPage loginPageChrome;
	private MainPage mainPage;
	private static Logger logger = LogManager
			.getLogger(LoginAndLogOutValidation.class.getName());

	@DataProvider(name = "LoginLogout")
	public Object[][] getDataforTest1() {
		Object[][] testData = ExcelUtility.getTestData("LoginLogout");
		logger.debug("data provider called for LoginLogout");
		return testData;
	}

	@Test(dataProvider = "LoginLogout", description = "validate SignOut feature with valid and invalid reason codes")
	public void to_Validate_Logout_with_Reason_Code(String url,
			String username_ch1, String pwd_ch1, String ext_ch1,
			String wrapupReason) throws InterruptedException {
		loginPageChrome = PageFactory.initElements(driver, LoginPage.class);
		mainPage = PageFactory.initElements(driver, MainPage.class);
		baseClass.openApp(driver, url);
		Thread.sleep(10000);
		logger.debug(driver);

		// Login with user
		loginPageChrome.loginWithValidCredentials(username_ch1, pwd_ch1,
				ext_ch1);
		Thread.sleep(28000);

		if (ext_ch1.equalsIgnoreCase(mainPage.getAgentExtension())) {
			ListnerClass.test.log(LogStatus.PASS, "Agent is able to login");
		} else {
			ListnerClass.test.log(LogStatus.FAIL, "Agent is unable to login");
		}
		System.out.println(mainPage.getAgentExtension());
		Assert.assertEquals(mainPage.getAgentExtension(), ext_ch1);

		mainPage.signOutClick(wrapupReason);
		Thread.sleep(2000);
	}
}
