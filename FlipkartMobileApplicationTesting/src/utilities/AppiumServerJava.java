package utilities;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServerJava {
	
	private static AppiumDriverLocalService service;
	private static AppiumServiceBuilder builder;
	private static DesiredCapabilities cap;
	static InetAddress ipAddr;
	public static String ipAddress;
	
	synchronized public static void startServer() {
		//Set Capabilities
		cap = new DesiredCapabilities();
		cap.setCapability("noReset", "false");
		
		//Build the Appium service
		
		try {
			ipAddr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			
		}
		
		ipAddress = ipAddr.getHostAddress().toString();
		
		builder = new AppiumServiceBuilder();
		builder.withIPAddress(ipAddress);
		builder.usingPort(4723);
		builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		builder.withAppiumJS(new File("C:\\Users\\nkadam\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"));
		//Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}
	
	synchronized public static void stopServer() {
		service.stop();
	}

	synchronized public static boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}	


}
