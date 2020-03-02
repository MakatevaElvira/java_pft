package ru.stqa.pft.test.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    public void selectFirstElm(){
        WebElement selectElem = driver.findElement(By.tagName("select"));
        Select select = new Select(selectElem);
        select.getFirstSelectedOption();
    }
    public void selectByText(String text){
        WebElement selectElem = driver.findElement(By.tagName("select"));
        Select select = new Select(selectElem);
        select.selectByValue(text);
    }
    public void waitInvisibility(By locator){
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public  void scrollToClick(By locator){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    public void attach (By locator, File file) {
        if (file != null) {
            driver.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    @BeforeSuite
   public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }
   @AfterSuite
    public void stop(){
        driver.quit();
    }

    public void options(){
            if (tlDriver.get() != null) {
                driver = tlDriver.get();
                wait = new WebDriverWait(driver, 10);
                return;
            }
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-fullscreen");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("unexpectedAlertBehaviour","dismiss");
            caps.setCapability(ChromeOptions.CAPABILITY,options);
            driver = new ChromeDriver();
            tlDriver.set(driver);
            System.out.println(((HasCapabilities) driver).getCapabilities());
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver,10);

    }
}

