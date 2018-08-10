package com.automationsnap.TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
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
public class NotReady_Conference_Popup extends baseClass {

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
	private JavascriptExecutor javascript_driver;
	private JavascriptExecutor javascript_FF;
	private JavascriptExecutor javascript_Chrome;
	private static Logger logger = LogManager
			.getLogger(NotReady_Conference_Popup.class.getName());

	@DataProvider(name = "conference")
	public Object[][] getDataforTest1() {
		Object[][] testData = ExcelUtility.getTestData("conference");
		logger.debug("data provider called for conference");
		return testData;
	}

	@Test(dataProvider = "conference")
	public void to_validate_Conference_for_three_User_in_Not_Ready_with_PopUp(
			String url, String username_ch1, String pwd_ch1, String ext_ch1,
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
			javascript_driver = (JavascriptExecutor) driver;
			baseClass.openApp(driver, url);
			javascript_driver
					.executeScript("alert('Chrome Browser instantiated');");
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			logger.debug(driver);
			Thread.sleep(8000);

			DriverInstantiate.newDriver("ff");
			baseClass.openApp(DriverInstantiate.ffdriver, url);
			loginPageFF = PageFactory.initElements(DriverInstantiate.ffdriver,
					LoginPage.class);
			mainPageFF = PageFactory.initElements(DriverInstantiate.ffdriver,
					MainPage.class);
			outboundCallAccept_FF = PageFactory.initElements(
					DriverInstantiate.ffdriver, OutboundCallAnsPage.class);
			javascript_FF = (JavascriptExecutor) DriverInstantiate.ffdriver;
			javascript_FF
					.executeScript("alert('Firefox Browser instantiated');");
			Thread.sleep(2000);
			DriverInstantiate.ffdriver.switchTo().alert().accept();
			logger.debug(DriverInstantiate.ffdriver);

			DriverInstantiate.newDriver("chrome");
			baseClass.openApp(DriverInstantiate.chromedriver, url);
			loginPageCh = PageFactory.initElements(
					DriverInstantiate.chromedriver, LoginPage.class);
			mainPageChrome = PageFactory.initElements(
					DriverInstantiate.chromedriver, MainPage.class);
			outboundCallAccept_Chrome = PageFactory.initElements(
					DriverInstantiate.chromedriver, OutboundCallAnsPage.class);
			javascript_Chrome = (JavascriptExecutor) DriverInstantiate.chromedriver;
			javascript_Chrome
					.executeScript("alert('Firefox Browser instantiated');");
			Thread.sleep(2000);
			DriverInstantiate.chromedriver.switchTo().alert().accept();
			logger.debug(DriverInstantiate.chromedriver);

			// Login with user
			loginPageChrome.loginWithValidCredentials(username_ch1, pwd_ch1,
					ext_ch1);
			Thread.sleep(30000); // utility.isElementVisible_Wait(driver,LoginPage.sighOutLoc);

			loginPageFF.loginWithValidCredentials(username_ff, pwd_ff, ext_ff);
			Thread.sleep(30000);

			loginPageCh.loginWithValidCredentials(username_ch2, pwd_ch2,
					ext_ch2);
			Thread.sleep(30000);

			// DialPadPage dialPad = PageFactory.initElements(driver,
			// //DialPadPage.class);

			javascript_driver
					.executeScript("alert('Agent 1 is clicking on Keyboard');");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			mainPage.keyboardClick();

			javascript_driver
					.executeScript("alert('Agent 1 is dialing number and click on call button to call another agent ');");
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			dialPad.dialNumber(counsultAgent1);

			dialPad.callBtn_Click();
			logger.debug("Click on call button test1");

			Thread.sleep(2000);

			javascript_FF
					.executeScript("alert('Agent 2 is answering the call');");
			Thread.sleep(3000);
			DriverInstantiate.ffdriver.switchTo().alert().accept();
			outboundCallAccept_FF.answer_Click();
			logger.debug("Clicked on FF answer");

			Thread.sleep(2000);

			javascript_driver
					.executeScript("alert('Agent 1 is clicking on consult button');");
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			outboundCallAccept_Driver.consultBtn_Click();

			javascript_driver
					.executeScript("alert('Agent 1 is dialing number and click on call button to call another agent ');");
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			dialPad.dialNumber(counsultAgent2);

			dialPad.callBtn_Click();

			Thread.sleep(3000);

			javascript_Chrome
					.executeScript("alert('Agent 3 is answering the call ');");
			Thread.sleep(5000);
			DriverInstantiate.chromedriver.switchTo().alert().accept();
			// Answer to call in Firefox
			outboundCallAccept_Chrome.answer_Click();

			javascript_driver
					.executeScript("alert('Agent 1 is clicking on first left tab ');");
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			// click on first user tab
			outboundCallAccept_Driver.firstUserForConference_Click();

			Thread.sleep(2000);

			javascript_driver
					.executeScript("alert('Agent 1 is clicking on conference button');");
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			outboundCallAccept_Driver.conferenceBtn_Click();

			javascript_driver
					.executeScript("alert('Agent 1 is ending conference');");
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			outboundCallAccept_Driver.endCallBtn_Click();

			Thread.sleep(2000);

			javascript_FF
					.executeScript("alert('Agent 2 is ending conference');");
			Thread.sleep(5000);
			DriverInstantiate.ffdriver.switchTo().alert().accept();
			outboundCallAccept_FF.endCallBtn_Click();

			Thread.sleep(7000);

			mainPage.signOutClick(signoutreasondriver);

			mainPageFF.signOutClick(signoutreasonFF);

			mainPageChrome.signOutClick(signoutreasonChrome);
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
