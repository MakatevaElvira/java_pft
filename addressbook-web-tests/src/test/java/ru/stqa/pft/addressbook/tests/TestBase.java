package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appManager.ApplicationManager;

public class TestBase extends ApplicationManager {
  protected WebDriver wd;


  protected final ApplicationManager app = new ApplicationManager();

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }


  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }



  public ApplicationManager getApplicationManager() {
    return app;
  }

  private class MyApplicationManager extends ApplicationManager {
    public void logout() {
      wd.findElement(By.linkText("Logout")).click();
    }
  }
}
