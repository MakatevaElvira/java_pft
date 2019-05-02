package ru.stqa.pft.test.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties properties;
  WebDriver wd;

  private String browser;
  private MenuHelper menuHelper;


  public ApplicationManager(String browser) {

    this.browser = browser;
    properties = new Properties();
  }

  public WebDriver getDriver() {
    if (wd == null){
      if (browser.equals(BrowserType.FIREFOX)){
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)){
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }
  //public void init() throws IOException  {
    //String target = System.getProperty("target", "local");
   // properties.load(new FileReader(new File( String.format("src/test/resources/%s.properties", target))));
   // wd.get(properties.getProperty("web.baseUrl"));
   // wd.findElement(By.cssSelector("button.close")).click();  }
  public void init() throws IOException  {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File( String.format("src/test/resources/%s.properties", target))));


    if (browser.equals(BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)){
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
   // WebDriver driver = new FirefoxDriver();
    //WebDriverWait wait = new WebDriverWait(wd, 10);
    wd.manage().timeouts().pageLoadTimeout(8, TimeUnit.SECONDS);
    //wd.switchTo().activeElement();
   // wd.findElement(By.cssSelector("button.close")).click();
    //wd.switchTo().frame("Undetected country");
    menu().close();
  }



  public void stop() {
    wd.quit();
  }
  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public MenuHelper menu() {
    if (menuHelper == null){
      menuHelper = new MenuHelper(this);
    }
    return menuHelper;
  }

}
