package utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ThreadLocalDriver {

	  private static ThreadLocal<AppiumDriver> tlDriver = new ThreadLocal<>();
	  
	  
	  public synchronized static void setDriver () throws MalformedURLException {

		  URL url = new URL("http://"+AppiumServerJava.ipAddress+":4723/wd/hub");
			DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("BROWSER_NAME", "Android");
				

				capabilities.setCapability("VERSION", "6.0.1"); 
				capabilities.setCapability("deviceName", "Moto G Turbo Edition");
				capabilities.setCapability("platformName","Android");
				capabilities.setCapability("udid","ZY22372D29");

				capabilities.setCapability("appPackage", "com.flipkart.android");

				capabilities.setCapability("appActivity","com.flipkart.android.SplashActivity");

		       
				tlDriver = ThreadLocal.withInitial(() -> new AndroidDriver<WebElement>(url, capabilities));
					
		        
		    }

		    //Get driver from tlDriver
		    public synchronized static AppiumDriver getDriver () {
		        return tlDriver.get();
		    }



	  
}
