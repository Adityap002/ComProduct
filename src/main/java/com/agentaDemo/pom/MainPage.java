package com.agentaDemo.pom;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
	
	private static Logger logger = Logger.getLogger(MainPage.class.getName());
	
	public WebDriver driver = null;

	public MainPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(id="outbound-call-button")
	private WebElement keyboard;
	
	@FindBy(xpath="//span[contains(text(),'Sign Out')]")
	private WebElement signout;
	
	@FindBy(id="text-userid")
	private WebElement agentID;
	
	@FindBy(id="text-extension")
	private WebElement extension;
	
	@FindBy(id="text-firstname")
	private WebElement firstName;
	
	@FindBy(id="text-lastname")
	private WebElement lastName;
	
	@FindBy(id="dropdown-home")
	private WebElement homeDropdown;
	
	@FindBy(id="dropdown-notready")
	private WebElement readyNotReadyDD;
	
	public void keyboardClick() {
		keyboard.click();
		logger.debug("Click on keyboard on Main page ");
	}
	
	public void signOutClick() {
		signout.click();
		logger.debug("Click on sign out on Main page ");
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
}
