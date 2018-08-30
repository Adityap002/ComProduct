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
import com.automationsnap.baseclass.baseClass;
import com.automationsnap.pom.DialPadPage;
import com.automationsnap.pom.LoginPage;
import com.automationsnap.pom.MainPage;
import com.automationsnap.pom.OutboundCallAnsPage;

@Listeners(value = ListnerClass.class)
public class NotReady_Conference extends baseClass {

	private LoginPage loginPageChrome;
	private LoginPage loginPageFF;
	private LoginPage loginPageCh;
	private DialPadPage dialPad;
	private MainPage mainPage;
	private MainPage mainPageFF;
	private MainPage mainPageChrome;
	private OutboundCallAnsPage outboundCallAccept_FF;
	private OutboundCallAnsPage outboundCallAccept_Driver;
	private OutboundCallAnsPage outboundCallAccept_Chrome;
	private static Logger logger = LogManager
			.getLogger(NotReady_Conference.class.getName());

	@DataProvider(name = "conference")
	public Object[][] getDataforTest1() {
		Object[][] testData = ExcelUtility.getTestData("conference");
		logger.debug("data provider called for conference");
		return testData;
	}

	@Test(dataProvider = "conference")
	public void to_validate_Conference_for_three_User_in_Not_Ready(String url,
			String username_ch1, String pwd_ch1, String ext_ch1,
			String username_ff, String pwd_ff, String ext_ff,
			String username_ch2, String pwd_ch2, String ext_ch2,
			String counsultAgent1, String counsultAgent2,
			String signoutreasondriver, String signoutreasonFF,
			String signoutreasonChrome) throws InterruptedException {

		try {
			loginPageChrome = PageFactory.initElements(driver, LoginPage.class);
			dialPad = PageFactory.initElements(driver, DialPadPage.class);
			mainPage = PageFactory.initElements(driver, MainPage.class);
			outboundCallAccept_Driver = PageFactory.initElements(driver,
					OutboundCallAnsPage.class);

			baseClass.openApp(driver, url);

			logger.debug(driver);

			DriverInstantiate.newDriver("ff");
			baseClass.openApp(DriverInstantiate.ffdriver, url);
			loginPageFF = PageFactory.initElements(DriverInstantiate.ffdriver,
					LoginPage.class);
			mainPageFF = PageFactory.initElements(DriverInstantiate.ffdriver,
					MainPage.class);
			outboundCallAccept_FF = PageFactory.initElements(
					DriverInstantiate.ffdriver, OutboundCallAnsPage.class);

			logger.debug(DriverInstantiate.ffdriver);

			DriverInstantiate.newDriver("chrome");
			baseClass.openApp(DriverInstantiate.chromedriver, url);
			Thread.sleep(8000);
			loginPageCh = PageFactory.initElements(
					DriverInstantiate.chromedriver, LoginPage.class);
			mainPageChrome = PageFactory.initElements(
					DriverInstantiate.chromedriver, MainPage.class);
			outboundCallAccept_Chrome = PageFactory.initElements(
					DriverInstantiate.chromedriver, OutboundCallAnsPage.class);

			logger.debug(DriverInstantiate.chromedriver);

			// Login with user
			loginPageChrome.loginWithValidCredentials(username_ch1, pwd_ch1,
					ext_ch1);
			// utility.isElementVisible_Wait(driver,LoginPage.sighOutLoc);

			loginPageFF.loginWithValidCredentials(username_ff, pwd_ff, ext_ff);

			loginPageCh.loginWithValidCredentials(username_ch2, pwd_ch2,
					ext_ch2);
			Thread.sleep(30000);

			// DialPadPage dialPad = PageFactory.initElements(driver,
			// //DialPadPage.class);

			mainPage.keyboardClick();

			dialPad.dialNumber(counsultAgent1);

			dialPad.callBtn_Click();
			logger.debug("Click on call button test1");

			Thread.sleep(3000);

			outboundCallAccept_FF.answer_Click();
			logger.debug("Clicked on FF answer");

			Thread.sleep(2000);

			outboundCallAccept_Driver.consultBtn_Click();

			dialPad.dialNumber(counsultAgent2);

			dialPad.callBtn_Click();

			Thread.sleep(3000);

			// Answer to call in Firefox
			outboundCallAccept_Chrome.answer_Click();

			// click on first user tab
			outboundCallAccept_Driver.firstUserForConference_Click();

			Thread.sleep(2000);

			outboundCallAccept_Driver.conferenceBtn_Click();

			outboundCallAccept_Driver.endCallBtn_Click();

			Thread.sleep(2000);

			outboundCallAccept_FF.endCallBtn_Click();

			Thread.sleep(7000);
			
			mainPage.signOut_Click();
			mainPage.select_SignOut_ReasonCode(signoutreasondriver);

			mainPageFF.signOut_Click();
			mainPageFF.select_SignOut_ReasonCode(signoutreasonFF);
			
			mainPageChrome.signOut_Click();
			mainPageChrome.select_SignOut_ReasonCode(signoutreasonChrome);
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
