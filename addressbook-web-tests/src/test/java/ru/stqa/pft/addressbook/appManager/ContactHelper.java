package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.*;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {
  private boolean acceptNextAlert = true;

  public ContactHelper(FirefoxDriver wd) {
   super(wd);

  }

  public void returnToContactPage() {
    click(By.linkText("home page"));

  }

  public void submintContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactGeneral groupGeneral,
                              ContactCompanyInfo groupCompanyInfo, ContactNumber groupNumber,
                              ContactEmails groupEmails, ContactBirth groupBirth, String contactsGroup,
                              ContactSecondary groupSecondary) {
    type(By.name("firstname"),groupGeneral.getName());
    type(By.name("lastname"),groupGeneral.getLastname());
    type(By.name("company"),groupCompanyInfo.getCompany());
    type(By.name("address"),groupCompanyInfo.getAddressCompany());
    type(By.name("home"),groupNumber.getHomeNumber());
    type(By.name("mobile"),groupNumber.getModileNumber());
    type(By.name("work"),groupNumber.getWorkNumber());
    type(By.name("email"),groupEmails.getEmail1());
    type(By.name("email2"),groupEmails.getEmail2());
    type(By.name("email3"),groupEmails.getEmail3());
    click(By.name("bday"));
    select(By.name("bday"), groupBirth.getBday(), By.xpath("//option[@value='1']"));
    click(By.name("bmonth"));
    select(By.name("bmonth"), groupBirth.getDmonth(), By.xpath("//option[@value='December']"));
    type(By.name("byear"),groupBirth.getByear());
    click(By.name("new_group"));
    select(By.name("new_group"), contactsGroup, By.xpath("(//option[@value='19'])[3]"));
    type(By.name("address2"),groupSecondary.getAddress2());
    type(By.name("phone2"),groupSecondary.getAddressHome());
    type(By.name("notes"),groupSecondary.getElses());

  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }


  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void initContactModification() {
    click(By.cssSelector("img[alt=\"Edit\"]"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void fillContactFormGeneral(ContactGeneral groupGeneral) {
    type(By.name("firstname"),groupGeneral.getName());
    type(By.name("lastname"),groupGeneral.getLastname());
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    acceptNextAlert = true;
  }
  private String closeAlertAndGetItsText() {
    try {
      Alert alert = wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
