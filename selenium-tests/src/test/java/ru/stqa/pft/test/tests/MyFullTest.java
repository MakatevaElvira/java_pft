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

public class MyFullTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void start(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability("unexpectedAlertBehaviour","dismiss");
        caps.setCapability(ChromeOptions.CAPABILITY,options);
        driver = new ChromeDriver(caps);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver,10);
    }

    @Test(enabled = true)
    public void testMyFirstTest(){
        driver.navigate().to("https://www.google.ru/");
    }

    @AfterSuite
    public void stop(){
        driver.quit();
    }
}



