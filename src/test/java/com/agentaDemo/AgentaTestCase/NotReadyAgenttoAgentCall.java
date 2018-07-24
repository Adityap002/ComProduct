package com.agentaDemo.AgentaTestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agentaDemo.Util.DriverInstantiate;
import com.agentaDemo.Util.ExcelUtility;
import com.agentaDemo.baseclass.baseClass;
import com.agentaDemo.pom.DialPadPage;
import com.agentaDemo.pom.LoginPage;
import com.agentaDemo.pom.MainPage;
import com.agentaDemo.pom.OutboundCallAnsPage;

public class NotReadyAgenttoAgentCall extends baseClass {

	private LoginPage loginPageChrome;
	private LoginPage loginPageFF;
	private LoginPage loginPageCh;
	private DialPadPage dialPad;
	private MainPage mainPage;
	private OutboundCallAnsPage outboundCallAccept_FF;
	private OutboundCallAnsPage outboundCallAccept_Driver;
	private OutboundCallAnsPage outboundCallAccept_Chrome;

	@DataProvider(name = "test2")
	public Object[][] getDataforTest1() {

		Object[][] testData = ExcelUtility.getTestData("Test2");
		return testData;
	}

	@Test(dataProvider = "test2")
	public void agentToAgentCall(String url, String username_ch1,
			String pwd_ch1, String ext_ch1, String username_ff, String pwd_ff,
			String ext_ff, String username_ch2, String pwd_ch2, String ext_ch2)
			throws InterruptedException {
		loginPageChrome = PageFactory.initElements(driver, LoginPage.class);
		dialPad = PageFactory.initElements(driver, DialPadPage.class);
		mainPage = PageFactory.initElements(driver, MainPage.class);
		outboundCallAccept_Driver = PageFactory.initElements(driver,
				OutboundCallAnsPage.class);
		baseClass.openApp(driver, url);
		System.out.println(driver);

		DriverInstantiate.newDriver("ff");
		baseClass.openApp(DriverInstantiate.ffdriver, url);
		loginPageFF = PageFactory.initElements(DriverInstantiate.ffdriver,
				LoginPage.class);
		outboundCallAccept_FF = PageFactory.initElements(
				DriverInstantiate.ffdriver, OutboundCallAnsPage.class);
		System.out.println(DriverInstantiate.ffdriver);

		DriverInstantiate.newDriver("chrome");
		baseClass.openApp(DriverInstantiate.chromedriver, url);
		loginPageCh = PageFactory.initElements(DriverInstantiate.chromedriver,
				LoginPage.class);
		outboundCallAccept_Chrome = PageFactory.initElements(
				DriverInstantiate.chromedriver, OutboundCallAnsPage.class);
		System.out.println(DriverInstantiate.chromedriver);

		// Login with user
		loginPageChrome.enterId(driver, username_ch1);
		loginPageChrome.enterPassword(driver, pwd_ch1);
		loginPageChrome.enterExtension(driver, ext_ch1);
		loginPageChrome.clickSubmit();

		Thread.sleep(30000); // utility.isElementVisible_Wait(driver,LoginPage.sighOutLoc);

		loginPageFF.enterId(DriverInstantiate.ffdriver, username_ff);
		loginPageFF.enterPassword(DriverInstantiate.ffdriver, pwd_ff);
		loginPageFF.enterExtension(DriverInstantiate.ffdriver, ext_ff);
		loginPageFF.clickSubmit();

		Thread.sleep(30000);

		loginPageCh.enterId(DriverInstantiate.chromedriver, username_ch2);
		loginPageCh.enterPassword(DriverInstantiate.chromedriver, pwd_ch2);
		loginPageCh.enterExtension(DriverInstantiate.chromedriver, ext_ch2);
		loginPageCh.clickSubmit();

		Thread.sleep(30000);

		// DialPadPage dialPad = PageFactory.initElements(driver,
		// //DialPadPage.class);

		mainPage.keyboardClick(driver);

		dialPad.click_7(driver);
		dialPad.click_4(driver);
		dialPad.click_9(driver);
		dialPad.click_9(driver);
		dialPad.click_3(driver);
		dialPad.click_7(driver);

		dialPad.callBtn_Click(driver);
		System.out.println("Click on call button");

		Thread.sleep(3000);

		// Answer to call in Firefox outboundCallAccept_FF =
		PageFactory.initElements(DriverInstantiate.ffdriver,
				OutboundCallAnsPage.class);
		outboundCallAccept_FF.answer_Click(DriverInstantiate.ffdriver);
		System.out.println("Clicked on FF answer");

		Thread.sleep(3000);

		outboundCallAccept_Driver.consultBtn_Click(driver);

		dialPad.click_7(driver);
		dialPad.click_4(driver);
		dialPad.click_9(driver);
		dialPad.click_9(driver);
		dialPad.click_3(driver);
		dialPad.click_4(driver);

		dialPad.callBtn_Click(driver);

		Thread.sleep(3000);

		// Answer to call in Firefox outboundCallAccept_Chrome =
		PageFactory.initElements(DriverInstantiate.chromedriver,
				OutboundCallAnsPage.class);
		outboundCallAccept_Chrome.answer_Click(DriverInstantiate.chromedriver);

		// click on first user tab
		outboundCallAccept_Driver.firstUserForConference_Click(driver);

		Thread.sleep(2000);

		outboundCallAccept_Driver.conferenceBtn_Click(driver);

		outboundCallAccept_Driver.endCallBtn_Click(driver);

		Thread.sleep(2000);

		outboundCallAccept_FF.endCallBtn_Click(DriverInstantiate.ffdriver);

		Thread.sleep(2000);

	}

	@DataProvider(name = "test1")
	public Object[][] getDataforTest2() {

		Object[][] testData = ExcelUtility.getTestData("Test1");
		return testData;
	}

	@Test(dataProvider = "test1")
	public void agentToAgentCall1(String url, String username, String password,
			String Extension) throws InterruptedException {
		loginPageChrome = PageFactory.initElements(driver, LoginPage.class);
		dialPad = PageFactory.initElements(driver, DialPadPage.class);
		mainPage = PageFactory.initElements(driver, MainPage.class);
		outboundCallAccept_Driver = PageFactory.initElements(driver,
				OutboundCallAnsPage.class);
		baseClass.openApp(driver, url);
		System.out.println(driver);

		Thread.sleep(5000);

		/*
		 * login in chrome driver
		 */

		// Login with user
		loginPageChrome.enterId(driver, username);
		loginPageChrome.enterPassword(driver, password);
		loginPageChrome.enterExtension(driver, Extension);
		loginPageChrome.clickSubmit();
		System.out.println("Sign in button clicked for Chrome");
		Thread.sleep(30000);

		// Click on keypad button
		driver.findElement(By.id("outbound-call-button")).click();

		// Click on Cancel button
		driver.findElement(By.id("dialpad-cancel-button")).click();

		Thread.sleep(2000);
	}

}
