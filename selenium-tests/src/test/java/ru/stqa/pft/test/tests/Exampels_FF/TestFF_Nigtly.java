package ru.stqa.pft.test.tests.Exampels_FF;

import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static ru.stqa.pft.test.tests.TestBase.tlDriver;

public class TestFF_Nigtly {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void start(){
        FirefoxOptions options = new FirefoxOptions()
                .setBinary("C:\\Program Files\\Firefox Nightly\\firefox.exe\\") ////тут версия Nigtly
               ;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE,options);
        driver = new FirefoxDriver();
        tlDriver.set(driver);



        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver,10);




    }
    @Test
    public void testMyFirstTest() {
        driver.navigate().to("https://www.google.ru/");
    }
   @AfterSuite
    public void stop(){
        driver.quit();
    }
}

