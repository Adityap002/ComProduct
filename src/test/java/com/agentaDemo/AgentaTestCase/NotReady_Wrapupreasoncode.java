package com.agentaDemo.AgentaTestCase;

import org.apache.log4j.Logger;
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
public class NotReady_Wrapupreasoncode extends baseClass {
	private LoginPage loginPageChrome;
	private LoginPage loginPageFF;
	private DialPadPage dialPad;
	private MainPage mainPage;
	private MainPage mainPageFF;
	private OutboundCallAnsPage outboundCallAccept_FF;
	private OutboundCallAnsPage outboundCallAccept_Driver;
	private static Logger logger = Logger
			.getLogger(NotReady_Wrapupreasoncode.class.getName());

	@DataProvider(name = "Wrapupreasoncode")
	public Object[][] getDataforTest1() {
		Object[][] testData = ExcelUtility.getTestData("Wrapupreasoncode");
		logger.debug("data provider called for Wrapupreasoncode");
		return testData;
	}

	@Test(dataProvider = "Wrapupreasoncode")
	public void to_validate_Wrapup_ReasonCode_Not_Ready(String url,
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

		baseClass.openApp(driver, url);
	
		logger.debug(driver);
		

		DriverInstantiate.newDriver("ff");
		baseClass.openApp(DriverInstantiate.ffdriver, url);
		
		
		loginPageFF = PageFactory.initElements(DriverInstantiate.ffdriver,
				LoginPage.class);
		mainPageFF = PageFactory.initElements(DriverInstantiate.ffdriver, MainPage.class);
		outboundCallAccept_FF = PageFactory.initElements(
				DriverInstantiate.ffdriver, OutboundCallAnsPage.class);
		Thread.sleep(10000);
		logger.debug(DriverInstantiate.ffdriver);

		// Login with user
		loginPageChrome.loginWithValidCredentials(username_ch1, pwd_ch1,
				ext_ch1);

		loginPageFF.loginWithValidCredentials(username_ff, pwd_ff, ext_ff);
		
		Thread.sleep(30000);

		mainPage.keyboardClick();

		dialPad.dialNumber(counsultAgent1);

		dialPad.callBtn_Click();
		logger.debug("Click on call button test1");

		Thread.sleep(2000);

		outboundCallAccept_FF.answer_Click();
		logger.debug("Clicked on FF answer");

		Thread.sleep(2000);

		outboundCallAccept_Driver.wrapUpReason_Click(wrapUpStatusValue);

		outboundCallAccept_Driver.applyBTN_Click();
		
		Thread.sleep(4000);

		Assert.assertEquals(outboundCallAccept_FF.wrapUpDD_Text(),
				wrapUpStatusValue);
		outboundCallAccept_Driver.endCallBtn_Click();

		Thread.sleep(7000);

		mainPage.signOutClick(signoutreasonChrome);
		
		mainPageFF.signOutClick(signoutreasonFF);
	}

}
