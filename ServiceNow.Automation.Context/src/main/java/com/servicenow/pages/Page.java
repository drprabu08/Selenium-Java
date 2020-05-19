package com.servicenow.pages;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	public WebDriver driver;
	
	public Page(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
		
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
