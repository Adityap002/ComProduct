package com.automationsnap.pom;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OutboundCallAnsPage {

	private static Logger logger = LogManager.getLogger(OutboundCallAnsPage.class
			.getName());
	
	protected WebDriver driver = null;

	public OutboundCallAnsPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "answer-button2")
	private WebElement answerBtn;

	public void answer_Click() {
		answerBtn.click();
		logger.debug("Click on call answer button");
	}

	// After accepting call footer feature
	@FindBy(id = "btn-dtmf")
	private WebElement keypad_feature;

	public void keypad_Click() {
		keypad_feature.click();
		logger.debug("Click on keypad button");
	}

	@FindBy(id = "btn-hold")
	private WebElement holdBtn;

	public void holdBtn_Click() {
		holdBtn.click();
		logger.debug("Click on call hold button");
	}

	@FindBy(id = "btn-consult")
	private WebElement consultBtn;

	public void consultBtn_Click() {
		consultBtn.click();
		logger.debug("Click on consult button");
	}

	@FindBy(id = "btn-xfer")
	private WebElement forwardandTransferBtn;

	public void forwardandTransferBtn_Click() {
		forwardandTransferBtn.click();
		logger.debug("Click on call Forward and Transfer button");
	}

	@FindBy(id = "btn-drop")
	private WebElement endCallBtn;

	public void endCallBtn_Click() {
		endCallBtn.click();
		logger.debug("Click on call end Call button");
	}

	@FindBy(id = "button-applywrapup")
	private WebElement applywrapupBtn;

	public void applywrapupBtn_Click() {
		applywrapupBtn.click();
		logger.debug("Click on call apply wrapup button");
	}

	@FindBy(id = "dialpad-call-button")
	private WebElement consultButton;

	public void consultButton_Click() {
		consultButton.click();
		logger.debug("Click on call consult button");
	}

	@FindBy(id = "dialpad-cancel-button")
	private WebElement consultCancelBtn;

	public void consultCancelBtn_Click() {
		consultCancelBtn.click();
		logger.debug("Click on consult Cancel button");
	}

	@FindBy(xpath = "//ul[@id='dialog-tabs']//li[1]")
	private WebElement firstUser;

	public void firstUserForConference_Click() {
		firstUser.click();
		logger.debug("Click on first tab to do conference");
	}

	@FindBy(id = "btn-retrive")
	private WebElement retriveBtn;

	public void retriveBtn_Click() {
		retriveBtn.click();
		logger.debug("Click on retrive button");
	}

	@FindBy(id = "btn-conference")
	private WebElement conferenceBtn;

	public void conferenceBtn_Click() {
		conferenceBtn.click();
		logger.debug("Click on Conference button");
	}
	
	@FindBy(id = "wrapup-label")
	private WebElement wrapUpDD;
	
	public String wrapUpDD_Text(){
		return wrapUpDD.getText();
	}

	public void wrapUpDD_Click() {
		wrapUpDD.click();
		logger.debug("Click on wrap Up dropdown");
	}
	
	@FindBy(id = "button-applywrapup")
	private WebElement applyBTN;

	public void applyBTN_Click() {
		applyBTN.click();
		logger.debug("Click on apply button");
	}
	
	public void wrapUpReason_Click(String wrapUpStatusValue) {
		wrapUpDD_Click();
		WebElement wrapUpStatus = driver.findElement(By.xpath("//a[contains(text(),'"+wrapUpStatusValue+"')]"));
		wrapUpStatus.click();
		logger.debug("Select wrap up reason code");
	}
	
}
