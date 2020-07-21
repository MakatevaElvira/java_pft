package ru.stqa.pft.test.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static ru.stqa.pft.test.tests.TestBase.tlDriver;

public class TestBaseFF {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void start(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-fullscreen");

        DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability("unexpectedAlertBehaviour","dismiss");
        caps.setCapability(FirefoxDriver.MARIONETTE,options);
        driver = new FirefoxDriver();
        tlDriver.set(driver);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver,10);


    }
   @AfterSuite
    public void stop(){
        driver.quit();
    }
}

