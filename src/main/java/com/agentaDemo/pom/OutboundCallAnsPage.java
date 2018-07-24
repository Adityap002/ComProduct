package com.agentaDemo.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OutboundCallAnsPage {
	@FindBy(id="answer-button2")
	private WebElement answerBtn;
	
	public void answer_Click(WebDriver driver) {
		answerBtn.click();
	}
	
	//After accepting call footer feature
	@FindBy(id="btn-dtmf")
	private WebElement keypad_feature;
	
	public void keypad_Click(WebDriver driver) {
		keypad_feature.click();
	}
	
	@FindBy(id="btn-hold")
	private WebElement holdBtn;
	
	public void holdBtn_Click(WebDriver driver) {
		holdBtn.click();
	}
	
	@FindBy(id="btn-consult")
	private WebElement consultBtn;
	
	public void consultBtn_Click(WebDriver driver) {
		consultBtn.click();
	}
	
	@FindBy(id="btn-xfer")
	private WebElement forwardandTransferBtn;
	
	public void forwardandTransferBtn_Click(WebDriver driver) {
		forwardandTransferBtn.click();
	}
	
	@FindBy(id="btn-drop")
	private WebElement endCallBtn;
	
	public void endCallBtn_Click(WebDriver driver) {
		endCallBtn.click();
	}
	
	@FindBy(id="button-applywrapup")
	private WebElement applywrapupBtn;
	
	public void applywrapupBtn_Click(WebDriver driver) {
		applywrapupBtn.click();
	}
	

	@FindBy(id="dialpad-call-button")
	private WebElement consultButton; 
	
	public void consultButton_Click(WebDriver driver) {
		consultButton.click();
	}
	
	@FindBy(id="dialpad-cancel-button")
	private WebElement consultCancelBtn; 
	
	public void consultCancelBtn_Click(WebDriver driver) {
		consultCancelBtn.click();
	}
	
	@FindBy(xpath="//ul[@id='dialog-tabs']//li[1]")
	private WebElement firstUser; 
	
	public void firstUserForConference_Click(WebDriver driver) {
		firstUser.click();
	}
	
	@FindBy(id="btn-retrive")
	private WebElement retriveBtn; 
	
	public void retriveBtn_Click(WebDriver driver) {
		retriveBtn.click();
	}
	
	@FindBy(id="btn-conference")
	private WebElement conferenceBtn; 
	
	public void conferenceBtn_Click(WebDriver driver) {
		conferenceBtn.click();
	}
}
