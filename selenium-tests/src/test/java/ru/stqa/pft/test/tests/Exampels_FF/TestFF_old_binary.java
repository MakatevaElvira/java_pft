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

public class TestFF_old_binary {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void start(){
        //запускает по старой схеме в старом браузере(не находит)
        FirefoxOptions options = new FirefoxOptions()
                //.setBinary("C:\\Users\\e.makateva\\AppData\\Local\\Mozilla Firefox\\firefox.exe")//тут версия 45
                .setBinary("C:\\Program Files\\Firefox Nightly\\firefox.exe\\") ////тут версия Nigtly
                //.addPreference(FirefoxDriver.MARIONETTE,false);//для старого способа
               ;
         driver = new FirefoxDriver(options);
       tlDriver.set(driver);

         //старая схема
        //FirefoxOptions options1 = new FirefoxOptions().setLegacy(false);
        // driver = new FirefoxDriver(options1);
        //FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files (x86)\\Nightly\\firefox.exe"));
        //тут ошибка driver = new FirefoxDriver(bin, new FirefoxProfile());

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

