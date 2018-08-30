package Smaple;

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
import com.relevantcodes.extentreports.LogStatus;

@Listeners(value = ListnerClass.class)
public class LoginAndLogOutValidationScrollDownRC extends baseClass {

	private LoginPage loginPageChrome;
	private MainPage mainPage;
	private Util util;
	private static Logger logger = LogManager
			.getLogger(LoginAndLogOutValidationScrollDownRC.class.getName());

	@DataProvider(name = "LoginLogoutScrollRC")
	public Object[][] getDataforTest1() {
		Object[][] testData = ExcelUtility.getTestData("LoginLogoutScrollRC");
		logger.debug("data provider called for LoginLogout");
		return testData;
	}

	@Test(dataProvider = "LoginLogoutScrollRC", description = "validate SignOut feature with valid and invalid reason codes")
	public void to_Validate_SignOut_And_NotReady_Scroll_Select_ReasonCode(String url,
			String username, String pwd, String ext,
			String notReadyRC, String signOutRC) throws InterruptedException {

		try {
			
			baseClass.openApp(driver, url);
			
			loginPageChrome = PageFactory.initElements(driver, LoginPage.class);
			mainPage = PageFactory.initElements(driver, MainPage.class);
			util = PageFactory.initElements(driver, Util.class);
			
			Thread.sleep(10000);
			logger.debug(driver);

			// Login with user
			loginPageChrome.loginWithValidCredentials(username, pwd,
					ext);
			util.isElementClickable_Wait(mainPage.signout);

			if (ext.equalsIgnoreCase(mainPage.getAgentExtension())) {
				ListnerClass.test.log(LogStatus.PASS, "Agent should login");
			} else {
				ListnerClass.test.log(LogStatus.FAIL,
						"Agent should login");
			}
			
			mainPage.readyNotReadyDD_Click();
			
			util.scrollInto(driver, mainPage.element_SelectNot_Ready(notReadyRC));
			
			mainPage.selectNotReady_click(notReadyRC);
			
			mainPage.signOut_Click();
			Thread.sleep(2000);
			
			util.scrollInto(driver, mainPage.element_SignOutReasonCode(signOutRC));
			
			mainPage.select_SignOut_ReasonCode(signOutRC);

		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}
	}
}
