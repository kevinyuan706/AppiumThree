/*
 * 报错时截图
 * **/


package com.young.appiumDemo;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//监听报错截图
@Listeners({ScreenShotListener.class})
public class AppiumScreenShotOnFailureTest {
	static AppiumDriver<WebElement> driver;
	
	@BeforeClass
	public void startTest() throws MalformedURLException{
		File classpath = new File(System.getProperty("user.dir"));
		File app = new File(classpath,"res/app/AndroidUI.apk");
		DesiredCapabilities desc = new DesiredCapabilities();
		desc.setCapability("deviceName", "appium-test-avd");
		desc.setCapability("automationName", "Appium");
		desc.setCapability("platformName", "Android");
		desc.setCapability("platformVersion", "5.0.1");
		desc.setCapability("app", app.getAbsolutePath());
		desc.setCapability("appPackage", "com.android.androidui");
		desc.setCapability("appActivity", "com.android.androidui.MainActivity");
		desc.setCapability("sessionOverride", true);
		desc.setCapability("unicodeKeyboard", true);
		desc.setCapability("resetKeyboard", false);
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),desc);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
	}
	
	@Test
	public void ScreenshotFailure() throws InterruptedException{
		
		WebElement showAlert = driver.findElement(By.id("com.android.androidui:id/buttonAlert"));
		
		Assert.assertEquals(showAlert.getText().trim(), "shw Alert");
		
		
	}
	
	@AfterClass
	public void endTest(){
		driver.close();
	}
	
	public static AppiumDriver<WebElement> getDriver(){
		return driver;
	}

}
