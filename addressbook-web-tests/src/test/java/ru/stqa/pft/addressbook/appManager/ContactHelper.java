package ru.stqa.pft.addressbook.appManager;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {
  private boolean acceptNextAlert = true;

  public ContactHelper(WebDriver wd) {
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
                              ContactSecondary groupSecondary, boolean creation) {
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

    if (creation)  {
      if (isElementPresent(By.xpath("(//option[@value='36'])")) ){
        click(By.name("new_group"));
        select(By.name("new_group"), contactsGroup, By.xpath("(//option[@value='36'])"));
      } return;

    } else Assert.assertFalse((isElementPresent(By.name("new_group"))));

    type(By.name("address2"),groupSecondary.getAddress2());
    type(By.name("phone2"),groupSecondary.getAddressHome());
    type(By.name("notes"),groupSecondary.getElses());

  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }


  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();

  }

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("img[alt=\"Edit\"]")).get(index).click();


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


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactGeneral general, ContactCompanyInfo companyInfo,
                     ContactNumber number, ContactEmails emails, ContactBirth birth,
                     String contactsGroup, ContactSecondary secondary, boolean creation) {
    initContactCreation();
    fillContactForm (general, companyInfo, number, emails, birth, contactsGroup, secondary, creation);
    submintContactCreation();
    returnToContactPage();
  }
  public void createContactGeneral(ContactGeneral general) {
    initContactCreation();
    fillGeneralContact (general);
    submintContactCreation();
    returnToContactPage();
  }
  public void modify(int index, ContactGeneral contactG) {
    initContactModification(index);
    fillGeneralContact(contactG);
    submitContactModification();
    returnToContactPage();
  }
  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();

  }

  public int getContactCount() {
   return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactGeneral> list() {
    List<ContactGeneral> contacts = new ArrayList<ContactGeneral>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
    for (WebElement element : elements){
      String name = element.findElement(By.xpath("./td[3]")).getText();
      String lastname = element.findElement(By.xpath("./td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.xpath("./td/input")).getAttribute("value"));

      ContactGeneral general = new ContactGeneral(id, name, lastname);
      contacts.add(general);
    }
    return contacts;
  }

  public void fillGeneralContact(ContactGeneral groupGeneral) {
    type(By.name("firstname"),groupGeneral.getName());
    type(By.name("lastname"),groupGeneral.getLastname());
  }
}
