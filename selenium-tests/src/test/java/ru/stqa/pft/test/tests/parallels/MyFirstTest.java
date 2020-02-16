package ru.stqa.pft.test.tests.parallels;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.stqa.pft.test.tests.TestBase;

public class MyFirstTest extends TestBase {


    @Test
    public void testMyFirstTest() {
        driver.navigate().to("https://www.google.ru/");
    }


    @Test
    public void testMyFirstTest1() {
        driver.navigate().to("https://www.google.ru/");
    }

}
