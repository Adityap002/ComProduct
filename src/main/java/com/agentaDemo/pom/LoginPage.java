package com.agentaDemo.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

	public static By sighOutLoc = By.xpath("//span[contains(text(),'Sign Out')]");

	@FindBy(how = How.ID, using = "textinput-username")
	private WebElement id;

	@FindBy(how = How.ID, using = "textinput-password")
	private WebElement pwd;

	@FindBy(how = How.ID, using = "textinput-extension")
	private WebElement ext;

	@FindBy(how = How.XPATH, using = "//agenta[@id='login-button']/a")
	private WebElement signIn;

	public void enterId(WebDriver driver, String userID) {
		id.sendKeys(userID);
	}

	public void enterPassword(WebDriver driver,String password) {
			pwd.sendKeys(password);
	}

	public void enterExtension(WebDriver driver, String extension) {
		ext.sendKeys(extension);
	}

	public void clickSubmit() {
		signIn.click();
	}
}
