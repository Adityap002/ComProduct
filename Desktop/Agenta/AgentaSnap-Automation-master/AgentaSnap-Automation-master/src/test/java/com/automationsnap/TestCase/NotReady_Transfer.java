package com.automationsnap.TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automationsnap.Util.DriverInstantiate;
import com.automationsnap.Util.ExcelUtility;
import com.automationsnap.Util.ListnerClass;
import com.automationsnap.Util.Util;
import com.automationsnap.baseclass.baseClass;
import com.automationsnap.pom.DialPadPage;
import com.automationsnap.pom.LoginPage;
import com.automationsnap.pom.MainPage;
import com.automationsnap.pom.OutboundCallAnsPage;

@Listeners(value = ListnerClass.class)
public class NotReady_Transfer extends baseClass {

	private LoginPage loginPage_AgentA;
	private LoginPage loginPage_AgentB;
	private LoginPage loginPage_AgentC;
	private DialPadPage dialPad_AgentA;
	private DialPadPage dialPad_AgentB;
	private MainPage mainPage_AgentA;
	private MainPage mainPage_AgentB;
	private MainPage mainPage_AgentC;
	private OutboundCallAnsPage outboundCallAccept_AgentB;
	private OutboundCallAnsPage outboundCallAccept_AgentC;
	private Util util_AgentA;
	private Util util_AgentB;
	private Util util_AgentC;
	private static Logger logger = LogManager
			.getLogger(NotReady_Conference.class.getName());

	@DataProvider(name = "Transfer")
	public Object[][] getDataforTest1() {
		Object[][] testData = ExcelUtility.getTestData("Transfer");
		logger.debug("data provider called for Transfer");
		return testData;
	}

	@SuppressWarnings("static-access")
	@Test(dataProvider = "Transfer")
	public void to_validate_Transfer_for_three_User_in_Not_Ready(String url,
			String username_user1, String pwd_user1, String ext_user1,
			String username_user2, String pwd_user2, String ext_user2,
			String username_user3, String pwd_user3, String ext_user3,
			String counsultAgent1, String TransferNumber, String Not_Ready_RC,
			String signoutreasonUser1, String signoutreasonUser2,
			String signoutreasonUser3) throws InterruptedException {

		try {
			
			baseClass.openApp(driver, url);

			loginPage_AgentA = PageFactory
					.initElements(driver, LoginPage.class);
			dialPad_AgentA = PageFactory
					.initElements(driver, DialPadPage.class);
			mainPage_AgentA = PageFactory.initElements(driver, MainPage.class);
			util_AgentA = PageFactory.initElements(driver, Util.class);
			
			logger.debug(driver);

			DriverInstantiate.newDriver("ff");
			baseClass.openApp(DriverInstantiate.ffdriver, url);
			loginPage_AgentB = PageFactory.initElements(
					DriverInstantiate.ffdriver, LoginPage.class);
			dialPad_AgentB = PageFactory.initElements(
					DriverInstantiate.ffdriver, DialPadPage.class);
			mainPage_AgentB = PageFactory.initElements(
					DriverInstantiate.ffdriver, MainPage.class);
			outboundCallAccept_AgentB = PageFactory.initElements(
					DriverInstantiate.ffdriver, OutboundCallAnsPage.class);
			util_AgentB = PageFactory.initElements(DriverInstantiate.ffdriver, Util.class);

			logger.debug(DriverInstantiate.ffdriver);

			DriverInstantiate.newDriver("chrome");
			baseClass.openApp(DriverInstantiate.chromedriver, url);
			Thread.sleep(8000);
			loginPage_AgentC = PageFactory.initElements(
					DriverInstantiate.chromedriver, LoginPage.class);
			mainPage_AgentC = PageFactory.initElements(
					DriverInstantiate.chromedriver, MainPage.class);
			outboundCallAccept_AgentC = PageFactory.initElements(
					DriverInstantiate.chromedriver, OutboundCallAnsPage.class);
			util_AgentC = PageFactory.initElements(DriverInstantiate.chromedriver, Util.class);

			logger.debug(DriverInstantiate.chromedriver);

			// Login with user
			loginPage_AgentA.loginWithValidCredentials(username_user1, pwd_user1,
					ext_user1);

			loginPage_AgentB.loginWithValidCredentials(username_user2, pwd_user2,
					ext_user2);

			loginPage_AgentC.loginWithValidCredentials(username_user3, pwd_user3,
					ext_user3);
			util_AgentC.isElementClickable_Wait(mainPage_AgentC.signout);
			
			mainPage_AgentA.isUserLogIn(ext_user1);
			mainPage_AgentB.isUserLogIn(ext_user2);
			mainPage_AgentC.isUserLogIn(ext_user3);

			mainPage_AgentA.keyboardClick();

			dialPad_AgentA.dialNumber(counsultAgent1);

			dialPad_AgentA.callBtn_Click();
			logger.debug("Click on call button");

			util_AgentB.isElementClickable_Wait(outboundCallAccept_AgentB.answerBtn);

			outboundCallAccept_AgentB.answer_Click();
			logger.debug("AgentB answered the call.");

			util_AgentB.isElementClickable_Wait(outboundCallAccept_AgentB.forwardandTransferBtn);

			outboundCallAccept_AgentB.forwardandTransferBtn_Click();

			dialPad_AgentB.dialNumber(TransferNumber);

			outboundCallAccept_AgentB.consultTransferButton_Click();

			util_AgentC.isElementClickable_Wait(outboundCallAccept_AgentC.answerBtn);

			// Answer to call in Firefox
			outboundCallAccept_AgentC.answer_Click();
			
			Thread.sleep(2000);

			mainPage_AgentC.readyNotReadyDD_Click();

			mainPage_AgentC.selectNotReady_click(Not_Ready_RC);

			outboundCallAccept_AgentC.endCallBtn_Click();
			
			util_AgentA.isElementClickable_Wait(mainPage_AgentA.signout);
			
			//Logout all the agent

			util_AgentA.logout(mainPage_AgentA, loginPage_AgentA, signoutreasonUser1);
			
			util_AgentB.logout(mainPage_AgentB, loginPage_AgentB, signoutreasonUser2);

			util_AgentC.logout(mainPage_AgentC, loginPage_AgentC, signoutreasonUser2);
			
		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}

	}

}
