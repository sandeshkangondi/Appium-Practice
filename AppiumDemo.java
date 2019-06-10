package Sandesh_Appium_Practice.Sandesh_Appium_Practice;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class AppiumDemo {
	IOSDriver driver;

	@BeforeClass
	public void myBeforeClass() throws MalformedURLException
	{
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.3.1");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iphone");
	    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
	    capabilities.setCapability(MobileCapabilityType.UDID, "4e7e850e7dc52682e32d6aef638bcc7327faf7b7");
	    capabilities.setCapability(MobileCapabilityType.APP, "com.apple.mobilesafari");
	    URL sURL = new URL("http://127.0.0.1:4723/wd/hub");
	    driver = new IOSDriver(sURL, capabilities);
	    System.out.println("Capabilities have been set");
	}
	    
	@Test
	public void myTestClass()
	{
	    System.out.println("Starting test run");
	}
	    
	@AfterClass
	public void myAfterClass()
	{
	    System.out.println("Ending test run");
	    driver.quit();
	}    
}
