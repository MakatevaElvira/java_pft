package ru.stqa.pft.test.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
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
    public void select(By locator, String name, By xpath) {
        new Select(driver.findElement(locator)).selectByVisibleText(name);
        click(xpath);
    }
    public void click(By locator) {
        driver.findElement(locator).click();
    }
    public void waitInvisibility(By locator){
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void presenceOfAllElementsLocatedBy(By locator ){
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void waitInvisibilityOf(WebElement element) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOf(element));
    }
    public void waitStalenessOf(WebElement element){
        new WebDriverWait(driver, 10).until(ExpectedConditions.stalenessOf(element));
    }
    public void waitVisibilityBy(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000)
                .withMessage("Element was not found").ignoring(NoSuchElementException.class);
        ;
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitElementPresent(By locator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000)
                .withMessage("Element was not found").ignoring(NoSuchElementException.class);
        ;
        wait.until(ExpectedConditions.presenceOfElementLocated((locator)));
    }
    public void waiteClickable(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000)
                .withMessage("Element was not found").ignoring(NoSuchElementException.class);
        ;
        wait.until(ExpectedConditions.elementToBeClickable((element)));
    }
    public void waitTextOfElementPresent(By locator, String text) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000)
                .withMessage("Element was not found").ignoring(NoSuchElementException.class);
        ;
        wait.until(ExpectedConditions.textToBePresentInElementLocated((locator),text));
    }
    public void waitAttributeToBe(By locator, String attribute,String value ) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000)
                .withMessage("Element was not found").ignoring(NoSuchElementException.class);
        ;
        wait.until(ExpectedConditions.attributeToBe((locator),attribute, value));
    }
    public boolean waitIsElementPresent(By locator) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000)
                    .withMessage("Element was not found").ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.presenceOfElementLocated((locator)));
            return true;
        } catch (NoSuchElementException e) {
        return false;
        }
    }
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
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
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }
   @AfterSuite(alwaysRun = true)
    public void stop(){
        driver.quit();
    }

    public void options(){
            if (tlDriver.get() != null) {
                driver = tlDriver.get();
                wait = new WebDriverWait(driver, 5);
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
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver,5);

    }
}

