package tests;


import java.net.MalformedURLException;

import io.appium.java_client.AppiumDriver;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import pages.HomePage;
import pages.LoginPage;
import pages.MenuPage;
import utilities.AppiumServerJava;
import utilities.ClearMemoryOfAppllication;
import utilities.ThreadLocalDriver;

public class BaseTest {
  
	AppiumDriver<WebElement> driver;
	LoginPage loginPage;
	HomePage homePage;
	MenuPage menuPage;
	int port = 4723;
	private static Logger Log = Logger.getLogger(BaseTest.class.getName());
	
	
  @BeforeClass
  public void pageInitialize() throws MalformedURLException {
	  ThreadLocalDriver.setDriver();
	  driver =  ThreadLocalDriver.getDriver();
	  loginPage = new LoginPage(driver);
	  Log.info("Login Page is initialized Successfully");
	  homePage = new HomePage(driver);
	  Log.info("Home Page is initialized Successfully");
	  menuPage = new MenuPage(driver);
	  Log.info("Menu Page is initialized Successfully");
  }

  @AfterClass
  public void closeDriver() {
	  ClearMemoryOfAppllication.clearAppMemory("com.flipkart.android");
	  driver.quit();
	  Log.info("Flipkart Application is Closed Successfully");
  }
  
  @BeforeSuite
  public void startAppiumServer()
  {
	  DOMConfigurator.configure("log4j.xml");

	  if(!AppiumServerJava.checkIfServerIsRunnning(port)) {
		  AppiumServerJava.startServer();
		}else 
		{
			Log.info("Appium Server is Allready Running.");
		}
	  
	  Log.info("Appium server is Started Successfully");
  }
  
  @AfterSuite
  public void stopAppiumServer()
  {
	  AppiumServerJava.stopServer();
	  Log.info("Appium server is Stopped Successfully");
  }

}
