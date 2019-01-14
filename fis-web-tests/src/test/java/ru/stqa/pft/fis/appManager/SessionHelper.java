package ru.stqa.pft.fis.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String username, String password) {
    click(By.linkText("Запустить приложение"));
    type(By.name("login"), username);
    type(By.name("password"), password);
    click(By.id("submit"));
  }

  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }
}
