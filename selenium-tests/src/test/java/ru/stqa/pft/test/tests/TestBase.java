package ru.stqa.pft.test.tests;

import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestBase {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
   public void start(){
        if (tlDriver.get() != null) {
          driver = tlDriver.get();
           wait = new WebDriverWait(driver, 10);
            return;
       }
       ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("unexpectedAlertBehaviour","dismiss");
        caps.setCapability(ChromeOptions.CAPABILITY,options);
        driver = new ChromeDriver();
        tlDriver.set(driver);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver,10);
    }
   @AfterSuite
    public void stop(){
        driver.quit();
    }
}

