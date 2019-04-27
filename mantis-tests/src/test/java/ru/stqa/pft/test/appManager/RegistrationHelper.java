package ru.stqa.pft.test.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "signup_page.php");
    type(By.name("username"),username);
    type(By.name("email"),email);
    click(By.cssSelector("input[type='submit']"));
    Wait<WebDriver> wait = new WebDriverWait(wd, 35);
    //wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Продолжить'])[1]/preceding::div[2]"))));
    wait.until((ExpectedConditions.visibilityOf(wd.findElement(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='Продолжить']")))));
    //click(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='Продолжить']"));


  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("button[type='submit']"));
   // click(By.cssSelector("input[value='Изменить пароль']"));
    //click(By.cssSelector("input[value='Update User']"));
  }
}
