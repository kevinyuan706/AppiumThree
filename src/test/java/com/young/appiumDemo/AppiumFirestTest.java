package com.young.appiumDemo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.apache.tools.ant.types.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class AppiumFirestTest {
	
  public AppiumDriver<WebElement> driver;
  
  @Test
  public void addContact() {
	  WebElement checkbox = driver.findElement(By.id("com.example.android.contactmanager:id/showInvisible"));
	  checkbox.isSelected(); //判断单选框是否被勾选，若有返回true
	  WebElement el = driver.findElement(By.xpath(".//*[@text='Add Contact']"));
	  el.click();
	  List<WebElement> testfieldsList = driver.findElementsByClassName("android.widget.EditText");  
	  testfieldsList.get(0).sendKeys("你好");
	  testfieldsList.get(1).sendKeys("18500099999");
	  testfieldsList.get(2).sendKeys("some@example.com");
	  WebElement getuser = driver.findElement(By.id("Save"));
	  getuser.getText();
	  getuser.isDisplayed();
	  driver.swipe(100, 500, 100, 100, 2);
	  
	  driver.findElementByXPath(".//*[@text='Save']").click();
  }
  @BeforeClass
  public void startTest() throws MalformedURLException {
	  File classpathRoot= new File(System.getProperty("user.dir"));
	  File appDir= new File(classpathRoot, "res/app");
	  File app= new File(appDir, "ContactManager.apk");
	  DesiredCapabilities dc  = new DesiredCapabilities();
	  //获取当前设备参数信息
	  dc.setCapability("automationName", "Appium");
	  dc.setCapability("platformName", "Android");
	  dc.setCapability("deviceName", "Android Emulator");
	  dc.setCapability("platformVersion", "5.1.0");
	  dc.setCapability("app", app.getAbsolutePath());
	  dc.setCapability("appPackage", "com.example.android.contactmanager");
	  dc.setCapability("appActivity", ".ContactManager");
	  dc.setCapability("unicodeKeyboard", "true"); //支持中文输入
	  driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
