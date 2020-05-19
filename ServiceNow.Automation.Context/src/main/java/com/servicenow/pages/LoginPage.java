package com.servicenow.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	By username = By.id("user_name");
	
	By password = By.id("user_password");
	
	By loginbtn = By.id("sysverb_login");
	
	By iFrame = By.id("gsft_main");
	
	public void login(String user, String pass)
	{
		switchFrame(iFrame);
		EnterText(username, user);
		EnterText(password, pass);
		ClickElement(loginbtn);
	}
}
