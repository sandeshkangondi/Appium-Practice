package Sandesh_Appium_Practice.Sandesh_Appium_Practice;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class macysCheckoutTest {
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
	    System.out.println("Starting test run");
	}
	    
	@Test
	public void purchaseTest() throws InterruptedException
	{
	    // Signing in
		driver.get("https://www.macys.com/account/signin");
		waitForPageReady();
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Sign In - Macy's\"]/XCUIElementTypeTextField").sendKeys("sanblr091@gmail.com");;
		driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Sign In - Macy's\"]/XCUIElementTypeSecureTextField").sendKeys("Sandesh2019");;
		driver.findElementById("sign in").click();
		System.out.println("Sign in complete");
		
		// Get product to purchase
		waitForPageReady();
		System.out.println("Test Login successful");
		driver.get("https://www.macys.com/shop/product/thirstystone-man-walks-coasters?ID=8823447&CategoryID=128251#fn=sp%3D1%26spc%3D108%26searchPass%3DmatchNone%26slotId%3D1");
		waitForPageReady();
		System.out.println("Opened the product for purchase");

		// Scroll and Add to bag
		scrollPage(1);
		driver.findElementById("ADD TO BAG").click();
		waitForPageReady();
		System.out.println("Added product to the bag");

		// View the bag and checkout
		driver.findElementById("VIEW BAG & CHECKOUT").click();	
		waitForPageReady();
		System.out.println("View purchase for checkout");

		// Scroll and Proceed to checkout
		scrollPage(2);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"PROCEED TO CHECKOUT\"]").click();
		waitForPageReady();
		System.out.println("Checking out");
		
		// Verifying for order pacement page with address/payment info
		assertTrue("Shipping address is not shown.", driver.findElementById("Shipping address").isDisplayed());
		scrollPage(2);
		assertTrue("Payment info is not shown.", driver.findElementById("Payment").isDisplayed());
		scrollPage(1);
		assertTrue("Order placement page hasn't displayed..",driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Scroll To Section\"]").isDisplayed());
		}
	
	public void scrollPage (int noOfPages){
		for (int pageNum=1;pageNum<=noOfPages;pageNum++){
		HashMap<String, String> scrollObject1 = new HashMap<String, String>();
		scrollObject1.put("direction", "down");
		driver.executeScript("mobile:scroll", scrollObject1);
		}
	}
	
	public void waitForPageReady(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	    
	@AfterClass
	public void myAfterClass()
	{
	    System.out.println("Ending test run");
	    driver.quit();
	}    
}