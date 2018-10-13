package pages;

import listeners.TestListener;
import io.appium.java_client.AppiumDriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.ScreenshotCapture;

public class LoginPage {
	
	//*********Page Variables*********
	
	public AppiumDriver<WebElement> driver;
	public WebDriverWait wait;
	private static Logger Log = Logger.getLogger(LoginPage.class.getName());
	
	//*********Constructor*********
	
    public LoginPage (AppiumDriver<WebElement> driver) {
    	 this.driver = driver;
         this.wait = new WebDriverWait(driver,50);
        
    }
    
    //*********Web Elements*********
    
    final By element_SignIn = By.id("com.flipkart.android:id/btn_mlogin");
	final By element_Name = By.id("com.flipkart.android:id/mobileNo");
	final By element_NoneoftheAbove = By.id("com.google.android.gms:id/button_area");
	final By element_Password = By.id("com.flipkart.android:id/et_password");
	final By element_searchBox = By.id("com.flipkart.android:id/search_widget_textbox");
  
	 //*********Page Methods*********
	
	 synchronized public void loginToFlipkartMobileApplication (String username, String password){
		 
		 	Log.info("Login Testcase Started");
		 	
			WebElement signin = wait.until(ExpectedConditions.elementToBeClickable(element_SignIn));
			signin.click();
			Log.info("Clicked on Signin button");

			WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(element_Name));
			name.click();
			WebElement noneoftheabove = wait.until(ExpectedConditions.visibilityOfElementLocated(element_NoneoftheAbove));
			noneoftheabove.click();

			name.sendKeys(username);
			Log.info("UserName Entered");
			WebElement pwd=driver.findElement(element_Password);
			pwd.sendKeys(password);
			Log.info("Password Entered");
			signin.click();
			Log.info("Clicked on Login button");
	    }
	 
	 public void verifyLoginisSuccessfull()
	 {
		 WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(element_searchBox));
		 verifyPageIsDisplayedOrNot(searchBox.isDisplayed());
		 Log.info("Login to Flipkart Application Sucessfuly.");
	 }
	 
	 public  void verifyPageIsDisplayedOrNot(boolean value)
		{
			if(value)
			{
				Assert.assertTrue(value);
				TestListener.pass("Home page should be Displayed Successfully.", "Home page is Displayed Successfully.", driver);
				 
			}else
			{
				TestListener.fail("Home page should be Displayed Successfully.", "Home page is not Displayed Successfully.", driver);
				 Assert.assertTrue(value);
			}
		}

}
