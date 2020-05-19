package com.servicenow.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.servicenow.util.Constants;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	By iFrame = By.id("gsft_main");
	
	By filter = By.id("filter");
	
	By createNew = By.xpath("(//*[text()='Create New'])[2]");
	
	By number = By.id("sys_readonly.problem.number");
	
	By lookupicon = By.id("lookup.problem.first_reported_by_task");
	
	By cell1 = By.xpath("//*[@id='task_table']//tr[2]/td[3]/a");
	
	By category = By.id("problem.category");
	
	By subcategory = By.id("problem.subcategory");
	
	By servidelookup = By.id("lookup.problem.business_service");
	
	
	
	public void verifyHomePageTitle() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "ServiceNow");
	}
	
	public void createNewFilter(String filtertext) {
		waitforElementVisible(filter);
		EnterText(filter, filtertext);
		waitforElementClickable(createNew);
		ClickElement(createNew);
		switchFrame(iFrame);
		verifyElementDisabled(number);
		Constants.problemNumber = getText(number);
		
		ClickElement(lookupicon);
		
		
		//Confirm new window opens
		Assert.assertEquals(driver.getWindowHandles().size(), 2); 
		
		switchToNewWindow();
		
		ClickElement(cell1);
		
		switchToDefaultContent();
		
		Assert.assertEquals(driver.getWindowHandles().size(), 1);
		
		selectLastOption(category);
		
		selectLongestOption(subcategory);
		
		ClickElement(servidelookup);
	}
}
