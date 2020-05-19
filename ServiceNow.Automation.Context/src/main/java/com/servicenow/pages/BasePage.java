package com.servicenow.pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.servicenow.ExtentReportListener.ExtentReportListener;

public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void ClickElement(By locator)
	{
		getElement(locator).click();
		
	}
	
	public void EnterText(By locator, String text)
	{
		getElement(locator).sendKeys(text);
	}
	public void selectLastOption(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> options = select.getOptions();
		
		select.selectByIndex(options.size() -1);
	}
	public void selectLongestOption(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> options = select.getOptions();
		WebElement actual = options.get(0);
		for(WebElement option : options) {
			if(option.getText().length() > actual.getText().length())
			{
				actual = option;
			}
		}
		
		select.selectByVisibleText(actual.getText());
	}
	public String getText(By locator) {
		return getElement(locator).getText();
	}
	public void verifyElementDisabled(By locator) {
		Assert.assertEquals(getElement(locator).getAttribute("readonly"), "readonly");
	}
	public void switchFrame(By locator) {
		driver.switchTo().frame(getElement(locator));
	}
	
	public void switchToNewWindow() {
		String parentWinHandle = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		
		for(String window : windows) {
			if(!window.equals(parentWinHandle))
			{
				driver.switchTo().window(window);
			}			
			
		}
		
	}
	
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
	public void waitforElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public void waitforElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public WebElement getElement(By locator) {
		
		return driver.findElement(locator);
	}
	
}
