package com.agentaDemo.AgentaTestCase;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
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
public class NotReady_Wrapupreasoncode_PopUp extends baseClass {
	private LoginPage loginPageChrome;
	private LoginPage loginPageFF;
	private DialPadPage dialPad;
	private MainPage mainPage;
	private MainPage mainPageFF;
	private OutboundCallAnsPage outboundCallAccept_FF;
	private OutboundCallAnsPage outboundCallAccept_Driver;
	private JavascriptExecutor javascript_chrome;
	private JavascriptExecutor javascript_FF;
	private static Logger logger = Logger
			.getLogger(NotReady_Wrapupreasoncode_PopUp.class.getName());

	@DataProvider(name = "Wrapupreasoncode")
	public Object[][] getDataforTest1() {
		Object[][] testData = ExcelUtility.getTestData("Wrapupreasoncode");
		logger.debug("data provider called for Wrapupreasoncode");
		return testData;
	}

	@Test(dataProvider = "Wrapupreasoncode")
	public void to_validate_Wrapup_ReasonCode_Not_Ready_with_PopUp(String url,
			String username_ch1, String pwd_ch1, String ext_ch1,
			String username_ff, String pwd_ff, String ext_ff,
			String counsultAgent1, String wrapUpStatusValue,
			String signoutreasonChrome, String signoutreasonFF)
			throws InterruptedException {

		loginPageChrome = PageFactory.initElements(driver, LoginPage.class);
		dialPad = PageFactory.initElements(driver, DialPadPage.class);
		mainPage = PageFactory.initElements(driver, MainPage.class);
		outboundCallAccept_Driver = PageFactory.initElements(driver,
				OutboundCallAnsPage.class);
		javascript_chrome = (JavascriptExecutor) driver;

		baseClass.openApp(driver, url);
		javascript_chrome
				.executeScript("alert('Chrome Browser instantiated');");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		logger.debug(driver);
		Thread.sleep(8000);

		DriverInstantiate.newDriver("ff");
		baseClass.openApp(DriverInstantiate.ffdriver, url);
		loginPageFF = PageFactory.initElements(DriverInstantiate.ffdriver,
				LoginPage.class);
		mainPageFF = PageFactory.initElements(DriverInstantiate.ffdriver, MainPage.class);
		outboundCallAccept_FF = PageFactory.initElements(
				DriverInstantiate.ffdriver, OutboundCallAnsPage.class);
		javascript_FF = (JavascriptExecutor) DriverInstantiate.ffdriver;
		javascript_FF.executeScript("alert('Firefox Browser instantiated');");
		Thread.sleep(2000);
		DriverInstantiate.ffdriver.switchTo().alert().accept();

		logger.debug(DriverInstantiate.ffdriver);

		// Login with user
		loginPageChrome.loginWithValidCredentials(username_ch1, pwd_ch1,
				ext_ch1);
		javascript_chrome
				.executeScript("alert('First User is loging in Chrome Browser');");
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(27000);

		loginPageFF.loginWithValidCredentials(username_ff, pwd_ff, ext_ff);
		javascript_FF
				.executeScript("alert('Second User is loging in Firefox Browser');");
		Thread.sleep(3000);
		DriverInstantiate.ffdriver.switchTo().alert().accept();
		Thread.sleep(27000);

		javascript_chrome
				.executeScript("alert('Agent is clicking on Keyboard');");
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		mainPage.keyboardClick();

		javascript_chrome
				.executeScript("alert('Agent is dialing number and click on call button to call another agent ');");
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		dialPad.dialNumber(counsultAgent1);

		dialPad.callBtn_Click();
		logger.debug("Click on call button test1");

		Thread.sleep(2000);

		javascript_FF
				.executeScript("alert('Calling agent is answering the call');");
		Thread.sleep(3000);
		DriverInstantiate.ffdriver.switchTo().alert().accept();
		outboundCallAccept_FF.answer_Click();
		logger.debug("Clicked on FF answer");

		Thread.sleep(2000);

		javascript_chrome
				.executeScript("alert('Called agent is setting wrap-up reason.');");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		outboundCallAccept_Driver.wrapUpReason_Click(wrapUpStatusValue);

		javascript_chrome.executeScript("alert('click on apply button');");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		outboundCallAccept_Driver.applyBTN_Click();

		javascript_chrome
				.executeScript("alert('Called agent is ending call');");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Assert.assertEquals(outboundCallAccept_FF.wrapUpDD_Text(),
				wrapUpStatusValue);
		outboundCallAccept_Driver.endCallBtn_Click();

		Thread.sleep(7000);

		mainPage.signOutClick(signoutreasonChrome);
		
		mainPageFF.signOutClick(signoutreasonFF);
	}

}
