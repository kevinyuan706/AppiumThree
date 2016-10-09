package com.young.appiumDemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.tools.ant.taskdefs.Touch;
import org.apache.tools.ant.types.Description;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class AppiumSwipeTest {
	public AndroidDriver<WebElement> driver;
	
	@BeforeClass
	public void startTest() throws MalformedURLException{
		DesiredCapabilities desC = new DesiredCapabilities();
		desC.setCapability("deviceName", "appium-test-avd");
		desC.setCapability("automationName", "Appium");
		desC.setCapability("platformName", "Android");
		desC.setCapability("platformVersion", "5.0.1");
		desC.setCapability("appPackage", "com.android.contacts");
		desC.setCapability("appActivity", ".activities.PeopleActivity");
		desC.setCapability("sessionOverride", "true");
		desC.setCapability("unicodeKeyboard", "true");
		desC.setCapability("resetKeyboard", "false");
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),desC);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //设置等待加载时间
	}
	
	@Test
	public void swipe() throws InterruptedException{
		Thread.sleep(1000);
		TouchAction tAction = new TouchAction(driver);
		tAction.press(0, 50).waitAction(8000).moveTo(0, 1200).release().perform();
		Thread.sleep(3000);
	}
	@AfterClass
	public void afterclass(){
		driver.quit();
	}
	

}
