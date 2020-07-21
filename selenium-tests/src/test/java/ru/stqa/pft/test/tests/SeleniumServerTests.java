package ru.stqa.pft.test.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumServerTests {
    public WebDriver driver;
    public WebDriverWait wait;
    @Test
    public void testOnServer() throws MalformedURLException {
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"),
                new ChromeOptions());
        driver.get("https://www.selenium.dev/downloads");
        driver.quit();
    }
}
