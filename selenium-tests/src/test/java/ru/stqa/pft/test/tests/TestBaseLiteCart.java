package ru.stqa.pft.test.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestBaseLiteCart {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void waitVisibilityOf(By locator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000)
                .withMessage("Element was not found").ignoring(NoSuchElementException.class);
        ;
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }
    public void waitToClick(By locator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000)
                .withMessage("Element was not found").ignoring(NoSuchElementException.class);
        ;
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator))).click();
    }
    public  void waitClickableOf(By locator){
        Wait<WebDriver>  wait = new WebDriverWait(driver, 5, 1000)
                .withMessage("Element was not found").ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class);;
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
    }

    @BeforeSuite
    public void start(){
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
        login();
    }

    public void login(){
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


