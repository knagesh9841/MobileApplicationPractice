package pages;

import java.util.List;

import listeners.TestListener;
import io.appium.java_client.AppiumDriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MenuPage {
	
	//*********Page Variables*********
	
		public AppiumDriver<WebElement> driver;
		public WebDriverWait wait;
		private static Logger Log = Logger.getLogger(MenuPage.class.getName());
		
   //*********Web Elements*********
		
		final By element_Notification = By.id("com.flipkart.android:id/in_app_notification_bell");
	    final By element_Notify = By.id("com.flipkart.android:id/title_action_bar");
		final By element_CartVerify = By.id("com.flipkart.android:id/cart_bg_icon");
		final By element_Cart = By.id("com.flipkart.android:id/title_action_bar");
		final By element_menu = By.className("android.widget.ImageButton");
		final By element_myAcct = By.id("com.flipkart.android:id/flyout_parent_title");
		final By element_myOrder = By.id("com.flipkart.android:id/category_title");
		final By element_subMenu = By.id("com.flipkart.android:id/title");
		
	//*********Constructor*********
		
	    public MenuPage (AppiumDriver<WebElement> driver) {
	    	 this.driver = driver;
	         this.wait = new WebDriverWait(driver,50);
	        
	    }
	    
	//*********Page Methods*********
	    
		public void myFashion()
		{
			
			wait = new WebDriverWait(driver, 30);
			boolean res=false;
			WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(element_menu));
			menu.click();
			

			wait.until(ExpectedConditions.elementToBeClickable(element_myAcct));
			List<WebElement> myAcct = driver.findElements(element_myAcct);
			for(int i=1;i<myAcct.size();i++)
			{
				myAcct.get(i).click();
				

				WebElement myOrder;
				String sValue = "";
				try {
					myOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(element_myOrder));
					sValue = myOrder.getText();
				} catch (Exception e) {

				}


				if(sValue.equals("Fashion"))
				{
					res=true;
					break;
				}
				
				menu = wait.until(ExpectedConditions.elementToBeClickable(element_menu));
				
				menu.click();
				
			}

			
			verifyPageIsDisplayedOrNot(res);
			
			menu = wait.until(ExpectedConditions.elementToBeClickable(element_menu));
			
			menu.click();
			
			menu = wait.until(ExpectedConditions.elementToBeClickable(element_menu));
			
			menu.click();


		}
		
		 public  void verifyPageIsDisplayedOrNot(boolean value)
			{
				if(value)
				{
					Assert.assertTrue(value);
					TestListener.pass("Fashion Page should be Displayed Successfully.", "Fashion page is Displayed Successfully.", driver);
					 
				}else
				{
					TestListener.fail("Fashion page should be Displayed Successfully.", "Fashion page is not Displayed Successfully.", driver);
					 Assert.assertTrue(value);
				}
			}
}
