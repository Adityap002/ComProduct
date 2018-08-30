package com.automationsnap.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automationsnap.Util.ListnerClass;
import com.automationsnap.Util.Util;
import com.relevantcodes.extentreports.LogStatus;

public class MainPage {

	private static Logger logger = LogManager.getLogger(MainPage.class
			.getName());

	protected WebDriver driver = null;

	public MainPage(WebDriver driver) {
		this.driver = driver;
	}

	public MainPage() {
	}

	@FindBy(id = "outbound-call-button")
	private WebElement keyboard;

	@FindBy(xpath = "//span[contains(text(),'Sign Out')]")
	public WebElement signout;

	@FindBy(id = "text-userid")
	private WebElement agentID;

	@FindBy(id = "text-extension")
	private WebElement extension;

	@FindBy(id = "text-firstname")
	private WebElement firstName;

	@FindBy(id = "text-lastname")
	private WebElement lastName;

	@FindBy(id = "dropdown-home")
	private WebElement homeDropdown;

	@FindBy(id = "dropdown-notready")
	public WebElement readyNotReadyDD;

	@FindBy(xpath = "//button[@id='dropdown-notready']/following-sibling::ul")
	public static WebElement listNotreadyDropdown;

	@FindBy(id = "state-reason-text")
	public WebElement notReady_RC_Status;

	public WebElement element_SignOutReasonCode(String signoutreason) {
		return driver.findElement(By
				.xpath("//agenta[@id='dropdown-logout']//a[contains(text(),'"
						+ signoutreason + "')]"));
	}

	public WebElement element_SelectNot_Ready(String NotReady_RC) {
		WebElement element = driver.findElement(By
				.xpath("//a[contains(text(),'Not Ready - " + NotReady_RC
						+ "')]"));
		return element;
	}

	public void keyboardClick() {
		keyboard.click();
		logger.debug("Click on keyboard on Main page ");
	}

	public void signOut_Click() throws InterruptedException {
		signout.click();
		Thread.sleep(1000);
		logger.debug("Click on sign out on Main page ");
	}

	// By Jena: Without Reason Code
	public void signOut_Click_Without_RC() throws InterruptedException {
		signout.click();
		signout.click();
		logger.debug("Clicked on sign out without Reason Code");
	}

	public void select_SignOut_ReasonCode(String signoutreason) {
		WebElement element = element_SignOutReasonCode(signoutreason);
		element.click();
	}

	public String getAgentID() {
		logger.debug("Agent ID is : " + agentID.getText());
		return agentID.getText();
	}

	public String getAgentName() {
		String fullName = firstName.getText() + " " + lastName.getText();
		logger.debug("Agent name is : " + fullName);
		return fullName;
	}

	public String getAgentExtension() {
		logger.debug("Agent Extension is : " + extension.getText());
		return extension.getText();
	}

	public void homeDropDown_Click() {
		homeDropdown.click();
		logger.debug("Clicked on home dropdown");
	}

	public void readyNotReadyDD_Click() {
		readyNotReadyDD.click();
		logger.debug("Clicked on ready & not ready dropdown");
	}

	public void selectNotReady_click(String NotReady_RC) {
		element_SelectNot_Ready(NotReady_RC).click();
	}

	public String selectedNotReadyReasonCode() {
		return notReady_RC_Status.getText();
	}

	public void isUserLogIn(String ext) {
		try {
			if (ext.equalsIgnoreCase(getAgentExtension())) {
				ListnerClass.test.log(LogStatus.PASS, "Agent should login");
			} else {
				ListnerClass.test.log(LogStatus.FAIL, "Agent should login");
			}
		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}
	}

	public void isNotReadyRCMatched(String notReadyRC, String actualRes) {
		try {
			System.out.println(notReadyRC + " " + actualRes);
			if (notReadyRC.equalsIgnoreCase(actualRes)) {
				ListnerClass.test.log(LogStatus.PASS,
						"selected reason code should " + notReadyRC);
			} else {
				ListnerClass.test.log(LogStatus.FAIL,
						"selected reason code should " + notReadyRC);
			}
		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}
	}
}
