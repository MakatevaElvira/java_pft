package ru.stqa.pft.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AuthTest {
    private WebDriver driver;
    private WebDriverWait wait;

@BeforeSuite
    public void start(){
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver,10);
}
@Test(enabled = true)
    public void testAuth(){
    driver.navigate().to("http://localhost:8080/litecart/admin/login.php");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
}
@AfterSuite
    public void stop(){
    driver.quit();
}
}
