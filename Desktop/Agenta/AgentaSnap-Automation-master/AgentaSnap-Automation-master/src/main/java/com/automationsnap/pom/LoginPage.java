package com.automationsnap.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automationsnap.Util.ListnerClass;
import com.automationsnap.Util.Util;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage {

	private static Logger logger = LogManager.getLogger(LoginPage.class.getName());

	protected WebDriver driver = null;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "textinput-username")
	private WebElement id;

	@FindBy(how = How.ID, using = "textinput-password")
	private WebElement pwd;

	@FindBy(how = How.ID, using = "textinput-extension")
	private WebElement ext;

	@FindBy(how = How.XPATH, using = "//agenta[@id='login-button']/a")
	public WebElement signIn;
	
	@FindBy(xpath = "//span[@data-notify='message']")
	WebElement logoutAlert;

	public void enterId(String userID) {
		id.sendKeys(userID);
		logger.debug("enter user id : " + userID);
	}

	public void enterPassword(String password) {
		pwd.sendKeys(password);
		logger.debug("enter pwd : " + password);
	}

	public void enterExtension(String extension) {
		ext.sendKeys(extension);
		logger.debug("enter Extension : " + extension);
	}

	public void clickSubmit() {
		signIn.click();
		logger.debug("click SignIn button");
	}
	
	public String logout_Alert() {
		return logoutAlert.getText();
	}
	
	public void loginWithValidCredentials(String userID, String password, String extension) {
		id.sendKeys(userID);
		pwd.sendKeys(password);
		ext.sendKeys(extension);
		signIn.click();
		logger.debug("Login with valid credentials");
	}
	
	public void isUserLogOut() {
		try {
			if ("Logged Out".equalsIgnoreCase(logout_Alert())) {
				ListnerClass.test.log(LogStatus.PASS, "Agent should logout");
				System.out.println("Agnet logout");
			} else {
				ListnerClass.test.log(LogStatus.FAIL, "Agent should logout");
			}
		} catch (Exception e) {
			logger.error(Util.getStackTrace(e));
		}
	}
}
