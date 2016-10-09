package com.young.appiumDemo;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.tools.ant.taskdefs.Touch;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class AppiumSpinnerTest {
	public AppiumDriver<WebElement> driver;
	
	@BeforeClass
	public void startTest()throws MalformedURLException{
		File classpathRoot= new File(System.getProperty("user.dir"));
		File app = new File(classpathRoot,"res/app/androidUI.apk");
		DesiredCapabilities  cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "appium-test-avd");
		cap.setCapability("automationName", "Appium");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.1.0");
		cap.setCapability("app",app.getAbsolutePath());
		cap.setCapability("appPackage", "com.android.androidui");
		cap.setCapability("appActivity", "com.android.androidui.MainActivity");
		cap.setCapability("sessionOverride", true);
		cap.setCapability("unicodeKeyboard", true);
		cap.setCapability("resetKeyboard", false);
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	
	@Test
	public void testSpinner()throws InterruptedException {
		WebElement spinner =driver.findElementById("android:id/text1");
		spinner.click();
		//滑动直到找到India
		driver.scrollToExact("India");
		WebElement optionIndia = driver.findElement(By.name("India"));
		optionIndia.click();
		//停留时间
		Thread.sleep(8000);
		
		
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	@Test
	public void testSlier() throws InterruptedException{
		
		WebElement slider = driver.findElementById("com.android.androidui:id/seekBar1");   //获取焦点
		
		int xActionpoint = slider.getLocation().getX(); //获取X起点位置
		
		int xEndPoint = xActionpoint + slider.getSize().getWidth(); 
		//获取拖动条的结束点的x坐标  = 开始x坐标+滑动条元素的宽度
		
		int YActionPoint = slider.getLocation().getY();
		
		TouchAction action = new TouchAction(driver);
		
		action.press(xActionpoint, YActionPoint).waitAction(800).moveTo(xActionpoint + 1,YActionPoint).release().perform();
	}
	
	@Test
	public void screenshot() throws IOException,InterruptedException{
		//截屏
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File location = new File("screenShot");
		
		//判断当前创建路径
		if(!location.exists()){
			location.mkdir();
		}
		//获取图片的绝对路径以及名称
		String screenShotName = location.getAbsolutePath()+File.separator+"HOME.png";
		//copy出当前图片
		FileUtils.copyFile(screenshot, new File(screenShotName));
		
	}
	
	@AfterClass
	public void endTest(){
		driver.closeApp();
	}
}
