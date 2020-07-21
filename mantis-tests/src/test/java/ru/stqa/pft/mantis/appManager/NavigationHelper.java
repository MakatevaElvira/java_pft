package ru.stqa.pft.mantis.appManager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {
  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void userManagement(){
    if (isElementPresent(By.linkText("Создать учетную запись")) ) {
      return;
    }
    click(By.xpath("//span[contains(text(),'Управление')]/.."));
    click(By.linkText("Управление пользователями"));
  }
}
