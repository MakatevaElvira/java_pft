package ru.stqa.pft.test.tests;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.stqa.pft.test.listener.MyListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestBaseLiteCartProxy {
    public static ThreadLocal<EventFiringWebDriver> tlDriver = new ThreadLocal<>();
    public EventFiringWebDriver driver;
    public WebDriverWait wait;
    public  BrowserMobProxy proxy;


    ;

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

    public void waitInvisibility(By locator){
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(locator));
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
    public void assertListIsSortedByText(By selector){
        ArrayList<String> сountriesList = new ArrayList<>();
        List<WebElement> сountries = driver.findElements(selector);
        for (WebElement сountry : сountries) {
            сountriesList.add(сountry.getText());
            String text = сountry.getText();
            System.out.println("text= "+text);
        }
        System.out.println("сountriesList= "+сountriesList);
        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : сountriesList) {
            sortedList.add(s);
            System.out.println("s= "+s);
        }
        Collections.sort(sortedList);
        System.out.println("sortedList= "+sortedList);
        //Assert.assertTrue(sortedList.equals(сountriesList));
        Assert.assertEquals(sortedList,сountriesList);
    }

    public void assertListIsSortedByValue(By selector){
        ArrayList<String> сountriesList = new ArrayList<>();
        List<WebElement> сountries = driver.findElements(selector);
        for (WebElement сountry : сountries) {
            сountriesList.add(сountry.getAttribute("value"));
            String text = сountry.getAttribute("value");
            System.out.println("text= "+text);
        }
        System.out.println("сountriesList= "+сountriesList);
        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : сountriesList) {
            sortedList.add(s);
            System.out.println("s= "+s);
        }
        Collections.sort(sortedList);
        System.out.println("sortedList= "+sortedList);
        //Assert.assertTrue(sortedList.equals(сountriesList));
        Assert.assertEquals(sortedList,сountriesList);
    }
    public void attach (By locator, File file) {
        if (file != null) {
            driver.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }
    public  void scrollToClick(By locator){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    public ExpectedCondition<String> anyWindowOtherThan(Set<String> existingWindows) {
        return new ExpectedCondition<String>() {
            @NullableDecl
            @Override
            public String apply(@NullableDecl WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(existingWindows);
                return handles.size()>0? handles.iterator().next():null;
            }
        };

    }
    public void waitStalenessOf(WebElement element){
        new WebDriverWait(driver, 10).until(ExpectedConditions.stalenessOf(element));
    }


    @BeforeSuite
    public void start(){
        proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        //свой прокси:
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("myProxy:8080");//и его передаем в capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        driver = new EventFiringWebDriver(new ChromeDriver(capabilities));
        driver.register(new MyListener());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
        login();
    }

    public void login(){
        driver.navigate().to("http://localhost:8080/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
    @AfterSuite(alwaysRun = true)
    public void stop(){
        driver.quit();
    }
}


