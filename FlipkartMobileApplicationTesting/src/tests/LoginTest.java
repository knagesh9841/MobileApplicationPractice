package tests;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.*;

import com.opencsv.CSVReader;


public class LoginTest extends BaseTest{
	
   Object loginDetail[][];
  
  
  
  @Test(dataProvider = "Authentication",priority=1, description="Login Scenario with Correct username and password.")
  public void flipkartLoginTest(String sUsername, String sPassword){
	  
	  loginPage.loginToFlipkartMobileApplication(sUsername, sPassword);
	  loginPage.verifyLoginisSuccessfull();
  }
  
  @Test(priority=3,description="Flipkart Cart Tab Verification")
  public void flipkartCartTab(){
	  homePage.cartTabVerification();
  }
  
  @Test(priority=2,description="Flipkart Notification Tab Verification")
  public void flipkartNotificationTab(){
	  homePage.notificationTabVerification();
	 
  }
  
  
  
  @DataProvider(name = "Authentication")
  public Object[][] credentials() throws IOException {
	  
	  	loginDetail = new Object[1][2];
 
	  	CSVReader reader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\LoginCredentials.csv"));

		List<String[]> listElement=reader.readAll();

		Iterator<String[]> iterator = listElement.iterator();

		String[] str=iterator.next();

		loginDetail[0][0] = str[0].trim();
		loginDetail[0][1] = str[1].trim();

		return loginDetail;
  }
}
