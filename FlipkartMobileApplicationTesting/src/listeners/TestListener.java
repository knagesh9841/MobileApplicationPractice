package listeners;

import io.appium.java_client.AppiumDriver;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import pages.LoginPage;
import utilities.ExtentManager;
import utilities.ScreenshotCapture;
import utilities.ThreadLocalDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TestListener implements ITestListener{
	
	private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static Logger Log = Logger.getLogger(TestListener.class.getName());

	@Override
	synchronized public void onTestStart(ITestResult result) {
		Log.info(result.getMethod().getMethodName() + " started!");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        test.set(extentTest);
		
	}

	@Override
	synchronized public void onTestSuccess(ITestResult result) {
		
		Log.info(result.getMethod().getMethodName() + " passed!");
		test.get().pass("Test case is passed");
		
	}

	@Override
	synchronized public void onTestFailure(ITestResult result) {
		Log.info(result.getMethod().getMethodName() + " failed!");
		test.get().fail("Test Case is Failed.");
        test.get().fail(result.getThrowable());
      
	}

	@Override
	synchronized public void onTestSkipped(ITestResult result) {
		Log.info(result.getMethod().getMethodName() + " skipped!"); 
		test.get().info("Testcase is Skipped.");
		test.get().skip(result.getThrowable());
		
	}

	@Override
	synchronized public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		Log.info("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()); 
	
	}

	@Override
	synchronized public void onStart(ITestContext context) {
		
		Log.info("Test Suite "+context.getName()+" started!");
		
	}

	@Override
	synchronized public void onFinish(ITestContext context) {
		
		Log.info("Test Suite "+context.getName()+" is ending!");
		extent.flush();
	}
	
	
	public static void pass(String ExpectValue,String aValue, AppiumDriver<WebElement> driver)
	{
		//test.get().pass(ExpectValue+"::"+aValue);
		 try {
				String screenshotPath = ScreenshotCapture.getScreenshot(driver, "screeshot");
				//test.get().addScreenCaptureFromPath(screenshotPath);
				test.get().pass(ExpectValue+"::"+aValue, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	}
	
	public static void fail(String ExpectValue,String aValue, AppiumDriver<WebElement> driver)
	{
		//test.get().fail(ExpectValue+"::"+aValue);
		 try {
				String screenshotPath = ScreenshotCapture.getScreenshot(driver, "screeshot");
				//test.get().addScreenCaptureFromPath(screenshotPath);
				test.get().fail(ExpectValue+"::"+aValue, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	}
	

}
