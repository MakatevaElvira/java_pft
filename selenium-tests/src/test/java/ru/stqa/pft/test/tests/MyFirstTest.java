package ru.stqa.pft.test.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

@BeforeSuite
    public void start(){
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver,10);
}
@Test
    public void testMyFirstTest(){
    driver.navigate().to("https://www.google.ru/");
}
@AfterSuite
    public void stop(){
    driver.quit();
}
}
