package com.agentaDemo.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
	
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
	
	public void keyboardClick(WebDriver driver) {
		keyboard.click();
	}
	
	public void signOutClick(WebDriver driver) {
		signout.click();
	}
	
	public String getAgentID(WebDriver driver) {
		return agentID.getText();
	}
	
	public String getAgentName(WebDriver driver) {
		String fullName = firstName.getText() + " " + lastName.getText();
		return fullName;
	}
	
	public String getAgentExtension(WebDriver driver) {
		return extension.getText();
	}
	
	public void homeDropDown_Click(WebDriver driver) {
		homeDropdown.click();
	}
	
	public void readyNotReadyDD_Click(WebDriver driver) {
		readyNotReadyDD.click();
	}
	
	
	

}
