package com.servicenow.testcases;

import org.testng.annotations.Test;

import com.servicenow.ExtentReportListener.ExtentReportListener;
import com.servicenow.pages.HomePage;
import com.servicenow.pages.LoginPage;

public class LoginPageTest extends BaseTest {

	
	@Test
	public void loginTest()
	{
		LoginPage loginpage = page.getInstance(LoginPage.class);
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		loginpage.login(username, password);
		
		page.getInstance(HomePage.class).verifyHomePageTitle();
		page.getInstance(HomePage.class).createNewFilter("Problem");
		
	}
	
}
