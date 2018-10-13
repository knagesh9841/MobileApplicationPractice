package pages;

import listeners.TestListener;
import io.appium.java_client.AppiumDriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
	
	//*********Page Variables*********
	
	public AppiumDriver<WebElement> driver;
	public WebDriverWait wait;
	private static Logger Log = Logger.getLogger(HomePage.class.getName());
	
	//*********Web Elements*********
	
	public final By element_Notification = By.id("com.flipkart.android:id/in_app_notification_bell");
	public final By element_Notify = By.id("com.flipkart.android:id/title_action_bar");
	public final By element_CartVerify = By.id("com.flipkart.android:id/cart_bg_icon");
	public final By element_Cart = By.id("com.flipkart.android:id/title_action_bar");
	public final By element_menu = By.className("android.widget.ImageButton");
	
	//*********Constructor*********
	
    public HomePage (AppiumDriver<WebElement> driver) {
    	 this.driver = driver;
         this.wait = new WebDriverWait(driver,50);
        
    }

    
    //*********Page Methods*********
    
    public void notificationTabVerification()
    {
    	wait = new WebDriverWait(driver, 50);
		WebElement notification = wait.until(ExpectedConditions.elementToBeClickable(element_Notification));
		notification.click();
		Log.info("Clicked on Notifcation Tab Button");

		WebElement notify = wait.until(ExpectedConditions.visibilityOfElementLocated(element_Notify));

		verifyPageIsDisplayedOrNot("Notifications (2)", notify.getText(), "Notification Tab");
		
		WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(element_menu));
		menu.click();
		wait.until(ExpectedConditions.elementToBeClickable(element_menu));
		menu.click();
		
    }
    
    
    public void cartTabVerification()
    {
    	
		wait = new WebDriverWait(driver, 50);
		WebElement cartverify = wait.until(ExpectedConditions.elementToBeClickable(element_CartVerify));
		cartverify.click();
		
		Log.info("Clicked on Cart Tab Button");
		
		WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(element_Cart));
		
		verifyPageIsDisplayedOrNot("My Cart", cart.getText(), "Cart Tab");
		
		
    }
    
    public  void verifyPageIsDisplayedOrNot(String evalue,String aValue,String pageName)
	{
		if(evalue.equals(aValue))
		{
			Assert.assertEquals(aValue, evalue);
			TestListener.pass(""+pageName+" should be Displayed Successfully.", ""+pageName+" is Displayed Successfully.", driver);
			 
		}else
		{
			TestListener.fail(""+pageName+" should be Displayed Successfully.", ""+pageName+" is not Displayed Successfully.", driver);
			Assert.assertEquals(aValue, evalue);
		}
	}
    
    
}
