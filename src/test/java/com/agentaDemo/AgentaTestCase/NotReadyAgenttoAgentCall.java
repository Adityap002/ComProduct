package com.agentaDemo.AgentaTestCase;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agentaDemo.Util.DriverInstantiate;
import com.agentaDemo.Util.ExcelUtility;
import com.agentaDemo.Util.ListnerClass;
import com.agentaDemo.baseclass.baseClass;
import com.agentaDemo.pom.DialPadPage;
import com.agentaDemo.pom.LoginPage;
import com.agentaDemo.pom.MainPage;
import com.agentaDemo.pom.OutboundCallAnsPage;

@Listeners(value = ListnerClass.class)
public class NotReadyAgenttoAgentCall extends baseClass {

	private LoginPage loginPageChrome;
	private LoginPage loginPageFF;
	private LoginPage loginPageCh;
	private DialPadPage dialPad;
	private MainPage mainPage;
	private OutboundCallAnsPage outboundCallAccept_FF;
	private OutboundCallAnsPage outboundCallAccept_Driver;
	private OutboundCallAnsPage outboundCallAccept_Chrome;
	private static Logger logger = Logger
			.getLogger(NotReadyAgenttoAgentCall.class.getName());
	
	
	@Test
	public static void sampleRun(){
		baseClass.openApp("https://dzone.com/articles/creating-extent-reports-in-selenium-using-extent-a");
		
		Assert.assertEquals(true, true);
	}

	@DataProvider(name = "test2")
	public Object[][] getDataforTest1() {
		Object[][] testData = ExcelUtility.getTestData("Test2");
		logger.debug("data provider called for test2");
		return testData;
	}

	@Test(priority = 2, dataProvider = "test2", enabled = false)
	public void agentToAgentCall(String url, String username_ch1,
			String pwd_ch1, String ext_ch1, String username_ff, String pwd_ff,
			String ext_ff, String username_ch2, String pwd_ch2, String ext_ch2)
			throws InterruptedException {

		loginPageChrome = PageFactory.initElements(driver, LoginPage.class);
		dialPad = PageFactory.initElements(driver, DialPadPage.class);
		mainPage = PageFactory.initElements(driver, MainPage.class);
		outboundCallAccept_Driver = PageFactory.initElements(driver,
				OutboundCallAnsPage.class);
		baseClass.openApp(url);
		logger.debug(driver);

		DriverInstantiate.newDriver("ff");
		baseClass.openApp(url);
		loginPageFF = PageFactory.initElements(DriverInstantiate.ffdriver,
				LoginPage.class);
		outboundCallAccept_FF = PageFactory.initElements(
				DriverInstantiate.ffdriver, OutboundCallAnsPage.class);
		logger.debug(DriverInstantiate.ffdriver);

		DriverInstantiate.newDriver("chrome");
		baseClass.openApp(url);
		loginPageCh = PageFactory.initElements(DriverInstantiate.chromedriver,
				LoginPage.class);
		outboundCallAccept_Chrome = PageFactory.initElements(
				DriverInstantiate.chromedriver, OutboundCallAnsPage.class);
		logger.debug(DriverInstantiate.chromedriver);

		// Login with user
		loginPageChrome.enterId(username_ch1);
		loginPageChrome.enterPassword(pwd_ch1);
		loginPageChrome.enterExtension(ext_ch1);
		loginPageChrome.clickSubmit();

		Thread.sleep(30000); // utility.isElementVisible_Wait(driver,LoginPage.sighOutLoc);

		loginPageFF.enterId(username_ff);
		loginPageFF.enterPassword(pwd_ff);
		loginPageFF.enterExtension(ext_ff);
		loginPageFF.clickSubmit();

		Thread.sleep(30000);

		loginPageCh.enterId(username_ch2);
		loginPageCh.enterPassword(pwd_ch2);
		loginPageCh.enterExtension(ext_ch2);
		loginPageCh.clickSubmit();

		Thread.sleep(30000);

		// DialPadPage dialPad = PageFactory.initElements(driver,
		// //DialPadPage.class);

		mainPage.keyboardClick();

		dialPad.click_7();
		dialPad.click_4();
		dialPad.click_9();
		dialPad.click_9();
		dialPad.click_3();
		dialPad.click_3();

		dialPad.callBtn_Click();
		logger.debug("Click on call button test1");

		Thread.sleep(3000);

		outboundCallAccept_FF.answer_Click();
		logger.debug("Clicked on FF answer");

		Thread.sleep(3000);

		outboundCallAccept_Driver.consultBtn_Click();

		dialPad.click_7();
		dialPad.click_4();
		dialPad.click_9();
		dialPad.click_9();
		dialPad.click_3();
		dialPad.click_6();

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

		Thread.sleep(2000);

	}

	@DataProvider(name = "test1")
	public Object[][] getDataforTest2() {

		Object[][] testData = ExcelUtility.getTestData("Test1");
		return testData;
	}

	@Test(priority = 1, dataProvider = "test1", enabled = true)
	public void agentToAgentCall1(String url, String username, String password,
			String Extension) throws InterruptedException {
		loginPageChrome = PageFactory.initElements(driver, LoginPage.class);
		dialPad = PageFactory.initElements(driver, DialPadPage.class);
		mainPage = PageFactory.initElements(driver, MainPage.class);
		outboundCallAccept_Driver = PageFactory.initElements(driver,
				OutboundCallAnsPage.class);
		baseClass.openApp(url);
		logger.debug(driver);

		Thread.sleep(5000);

		/*
		 * login in chrome driver
		 */

		// Login with user
		loginPageChrome.enterId(username);
		loginPageChrome.enterPassword(password);
		loginPageChrome.enterExtension(Extension);
		loginPageChrome.clickSubmit();
		logger.debug("Sign in button clicked for Chrome");
		Thread.sleep(30000);

		// Click on keypad button
		driver.findElement(By.id("outbound-call-button")).click();

		// Click on Cancel button
		driver.findElement(By.id("dialpad-cancel-button")).click();

		Thread.sleep(2000);
	}

	@DataProvider(name = "test3")
	public Object[][] getDataforTest3() {

		Object[][] testData = ExcelUtility.getTestData("Test2");
		logger.debug("data provider called for test3");
		return testData;
	}

	@Test(priority = 3, dataProvider = "test3", enabled = false)
	public void wrapUp(String url, String username_ch1, String pwd_ch1,
			String ext_ch1, String username_ff, String pwd_ff, String ext_ff,
			String username_ch2, String pwd_ch2, String ext_ch2)
			throws InterruptedException {

		loginPageChrome = PageFactory.initElements(driver, LoginPage.class);
		dialPad = PageFactory.initElements(driver, DialPadPage.class);
		mainPage = PageFactory.initElements(driver, MainPage.class);
		outboundCallAccept_Driver = PageFactory.initElements(driver,
				OutboundCallAnsPage.class);
		baseClass.openApp(url);
		logger.debug(driver);

		DriverInstantiate.newDriver("ff");
		baseClass.openApp(url);
		loginPageFF = PageFactory.initElements(DriverInstantiate.ffdriver,
				LoginPage.class);
		outboundCallAccept_FF = PageFactory.initElements(
				DriverInstantiate.ffdriver, OutboundCallAnsPage.class);
		logger.debug(DriverInstantiate.ffdriver);

		DriverInstantiate.newDriver("chrome");
		baseClass.openApp(url);
		loginPageCh = PageFactory.initElements(DriverInstantiate.chromedriver,
				LoginPage.class);
		outboundCallAccept_Chrome = PageFactory.initElements(
				DriverInstantiate.chromedriver, OutboundCallAnsPage.class);
		logger.debug(DriverInstantiate.chromedriver);

		// Login with user
		loginPageChrome.enterId(username_ch1);
		loginPageChrome.enterPassword(pwd_ch1);
		loginPageChrome.enterExtension(ext_ch1);
		loginPageChrome.clickSubmit();

		Thread.sleep(30000); // utility.isElementVisible_Wait(driver,LoginPage.sighOutLoc);

		loginPageFF.enterId(username_ff);
		loginPageFF.enterPassword(pwd_ff);
		loginPageFF.enterExtension(ext_ff);
		loginPageFF.clickSubmit();

		Thread.sleep(30000);

		loginPageCh.enterId(username_ch2);
		loginPageCh.enterPassword(pwd_ch2);
		loginPageCh.enterExtension(ext_ch2);
		loginPageCh.clickSubmit();

		Thread.sleep(30000);

		// DialPadPage dialPad = PageFactory.initElements(driver,
		// //DialPadPage.class);

		mainPage.keyboardClick();

		dialPad.click_7();
		dialPad.click_4();
		dialPad.click_9();
		dialPad.click_9();
		dialPad.click_3();
		dialPad.click_3();

		dialPad.callBtn_Click();
		logger.debug("Click on call button test1");

		Thread.sleep(3000);

		// Answer to call in Firefox outboundCallAccept_FF =
		PageFactory.initElements(DriverInstantiate.ffdriver,
				OutboundCallAnsPage.class);
		outboundCallAccept_FF.answer_Click();
		logger.debug("Clicked on FF answer");

		Thread.sleep(3000);

		outboundCallAccept_Driver.consultBtn_Click();

		dialPad.click_7();
		dialPad.click_4();
		dialPad.click_9();
		dialPad.click_9();
		dialPad.click_3();
		dialPad.click_6();

		dialPad.callBtn_Click();

		Thread.sleep(3000);

		// Answer to call in Firefox 
		outboundCallAccept_Chrome.answer_Click();

		// click on first user tab
		outboundCallAccept_Driver.firstUserForConference_Click();

	}
}
