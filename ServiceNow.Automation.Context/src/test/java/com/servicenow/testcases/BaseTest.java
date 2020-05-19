package com.servicenow.testcases;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.servicenow.ExtentReportListener.ExtentReportListener;
import com.servicenow.pages.Page;
import com.servicenow.util.Constants;

public class BaseTest {

	public WebDriver driver;
	
	public Page page;
	public static Properties prop;
	
	public BaseTest()
	{
		try {
			prop = new Properties();
			FileInputStream stream = new FileInputStream(Constants.CONFIG_PATH);
			prop.load(stream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void initialization()
	{
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVERPATH);
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVERPATH);
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		page = new Page(driver);
		
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		//driver.quit();
		
	}
}
