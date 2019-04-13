package ru.stqa.pft.mantis.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class HelperBase {
  protected ApplicationManager app;
  protected WebDriver wd;

  public HelperBase(ApplicationManager app) {
    this.app = app;
    this.wd = app.getDriver();

  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (! text.equals(existingText)){
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }
  public void attach (By locator, File file) {
    if (file != null) {
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
      }
    }
  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void select(By locator, String name, By xpath) {
    new Select(wd.findElement(locator)).selectByVisibleText(name);
    click(xpath);
  }
  public void selectByVisibleText(By locator, String name) {
    new Select(wd.findElement(locator)).selectByVisibleText(name);
  }
  public void selectByValue(By locator, String value) {
    new Select(wd.findElement(locator)).selectByValue(value);
  }
}
