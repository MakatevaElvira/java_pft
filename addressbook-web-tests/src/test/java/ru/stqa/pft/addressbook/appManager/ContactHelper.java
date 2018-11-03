package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.*;

public class ContactHelper extends HelperBase {

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

    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(groupGeneral.getLastname());
    wd.findElement(By.name("company")).click();
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys(groupCompanyInfo.getCompany());
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(groupCompanyInfo.getAddressCompany());
    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(groupNumber.getHomeNumber());
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(groupNumber.getModileNumber());
    wd.findElement(By.name("work")).click();
    wd.findElement(By.name("work")).clear();
    wd.findElement(By.name("work")).sendKeys(groupNumber.getWorkNumber());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(groupEmails.getEmail1());
    wd.findElement(By.name("email2")).click();
    wd.findElement(By.name("email2")).clear();
    wd.findElement(By.name("email2")).sendKeys(groupEmails.getEmail2());
    wd.findElement(By.name("email3")).click();
    wd.findElement(By.name("email3")).clear();
    wd.findElement(By.name("email3")).sendKeys(groupEmails.getEmail3());
    wd.findElement(By.name("bday")).click();
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(groupBirth.getBday());
    wd.findElement(By.xpath("//option[@value='1']")).click();
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(groupBirth.getDmonth());
    wd.findElement(By.xpath("//option[@value='December']")).click();
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(groupBirth.getByear());
    wd.findElement(By.name("new_group")).click();
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactsGroup);
    wd.findElement(By.xpath("(//option[@value='11'])[3]")).click();
    wd.findElement(By.name("address2")).click();
    wd.findElement(By.name("address2")).clear();
    wd.findElement(By.name("address2")).sendKeys(groupSecondary.getAddress2());
    wd.findElement(By.name("phone2")).click();
    wd.findElement(By.name("phone2")).clear();
    wd.findElement(By.name("phone2")).sendKeys(groupSecondary.getAddressHome());
    wd.findElement(By.name("notes")).click();
    wd.findElement(By.name("notes")).clear();
    wd.findElement(By.name("notes")).sendKeys(groupSecondary.getElses());
  }

  public void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }

 }
