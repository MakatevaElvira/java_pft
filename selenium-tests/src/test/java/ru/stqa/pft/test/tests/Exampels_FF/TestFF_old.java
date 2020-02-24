package ru.stqa.pft.test.tests.Exampels_FF;

import com.google.common.collect.ImmutableMap;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;

import static org.openqa.selenium.firefox.FirefoxDriver.MARIONETTE;
import static ru.stqa.pft.test.tests.TestBase.tlDriver;

public class TestFF_old {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void start(){
        //запускает по старой схеме в новом браузере
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE,false);
        driver = new FirefoxDriver(caps);
        //FirefoxOptions options = new FirefoxOptions()
                //.setBinary("C:\\Users\\e.makateva\\AppData\\Local\\Mozilla Firefox\\firefox.exe")//тут версия 45
               // .setBinary("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs")//тут версия Nigtly
               // .addPreference(FirefoxDriver.MARIONETTE,false)//для старого способа
               // .addPreference(FirefoxDriver.MARIONETTE,);//для нового способа
        // driver = new FirefoxDriver(options);
       // tlDriver.set(driver);



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

