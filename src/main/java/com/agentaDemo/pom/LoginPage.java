package com.agentaDemo.pom;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

	private static Logger logger = Logger.getLogger(LoginPage.class.getName());

	protected WebDriver driver = null;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public static By sighOutLoc = By
			.xpath("//span[contains(text(),'Sign Out')]");

	@FindBy(how = How.ID, using = "textinput-username")
	private WebElement id;

	@FindBy(how = How.ID, using = "textinput-password")
	private WebElement pwd;

	@FindBy(how = How.ID, using = "textinput-extension")
	private WebElement ext;

	@FindBy(how = How.XPATH, using = "//agenta[@id='login-button']/a")
	private WebElement signIn;

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
	
	public void loginWithValidCredentials(String userID, String password, String extension) {
		id.sendKeys(userID);
		pwd.sendKeys(password);
		ext.sendKeys(extension);
		signIn.click();
		logger.debug("Login with valid credentials");
	}
}
