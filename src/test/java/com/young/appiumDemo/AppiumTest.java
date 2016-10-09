package com.young.appiumDemo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.tools.ant.types.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AppiumTest {
	public AppiumDriver<WebElement> driver;
	
	@BeforeClass
	public void startTest() throws MalformedURLException{
		File classpathRoot = new File(System.getProperty("usr.dir"));
		File app = new File(classpathRoot,"res/app/AndroidUI.apk");
		DesiredCapabilities desca = new DesiredCapabilities();
		desca.setCapability("deviceName","appium-test-avd");
		desca.setCapability("automationName", "Appium");
		desca.setCapability("platformName", "Android");
		desca.setCapability("platformVersion", "5.0.1");
		desca.setCapability("app",app.getAbsolutePath());
		desca.setCapability("appPackage", "com.android.androidui");
		desca.setCapability("appActivity", "com.android.androidui.MainActivity");
		desca.setCapability("sessionOverride", "true");
		desca.setCapability("unicodeKeyboard", "true");
		desca.setCapability("resetKeyboard", "true");
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), desca);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	
	@Test
	public void handleAlert() throws InterruptedException{
		WebElement showAlert = driver.findElement(By.name("Show Alert"));
		showAlert.click();
		WebElement yes = driver.findElement(By.name("Yes"));
		yes.click();
		Thread.sleep(4000);
	}
	@AfterClass
	public void afterClass(){
		driver.close();
	}
	
	
}
