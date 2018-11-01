package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {


  public NavigationHelper(FirefoxDriver wd) {
    super(wd);



  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void goToContactPage() {
    wd.findElement(By.linkText("home")).click();
  }
}
