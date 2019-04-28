package ru.stqa.pft.mantis.appManager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void login() {
    wd.get(app.getProperty("web.baseUrl") + "login_page.php");
    type(By.name("username"), app.getProperty("web.adminLogin")); //administrator
    click(By.xpath("//input[@value='Войти']"));
    type(By.name("password"), app.getProperty("web.adminPassword"));// root
    click(By.xpath("//input[@value='Войти']"));
  }
}
