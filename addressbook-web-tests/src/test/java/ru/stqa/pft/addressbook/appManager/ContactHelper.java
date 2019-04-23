package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;

import static java.lang.String.valueOf;
import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {
  private boolean acceptNextAlert = true;
  private Contacts contactCache = null;
  public ContactHelper(WebDriver wd) {
   super(wd);  }

  public void returnToContactPage() {  click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactGeneral groupGeneral,
                              ContactCompanyInfo groupCompanyInfo, ContactNumber groupNumber,
                              ContactEmails groupEmails, ContactBirth groupBirth, String contactsGroup,
                              ContactSecondary groupSecondary, boolean creation) {
    type(By.name("firstname"),groupGeneral.getName());
    type(By.name("lastname"),groupGeneral.getLastName());
    type(By.name("company"),groupCompanyInfo.getCompany());
    type(By.name("address"),groupCompanyInfo.getAddressCompany());
    type(By.name("home"),groupNumber.getHomeNumber());
    type(By.name("mobile"),groupNumber.getMobileNumber());
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
      if (groupGeneral.getGroups().size()>0) {
        Assert.assertTrue(groupGeneral.getGroups().size()==1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupGeneral.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse((isElementPresent(By.name("new_group"))));
    }

    type(By.name("address2"),groupSecondary.getAddress2());
    type(By.name("phone2"),groupSecondary.getAddressHome());
    type(By.name("notes"),groupSecondary.getElses());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    //wd.findElement((By.id(id).click();

  }
  public void waitSelectContactById(int id){
    Wait<WebDriver> wait = new WebDriverWait(wd, 5, 5000);
    wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("input[value='" + id + "']")))).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href=\"edit.php?id=" + id + "\"]")).click();
  }

  public void initContactModifyByIdLong(int id) {
   WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[id='%s']", id)));
   WebElement row = checkbox.findElement(By.xpath("./../.."));
   List <WebElement> cells = row.findElements(By.tagName("td"));
   cells.get(7).findElement(By.tagName("a")).click();
   //wd.findElement(By.xpath(String.format("//input[id='%s']/../../td[8]/a",id))).click();
   //wd.findElement(By.xpath(String.format("//tr[.input[id='%s']]/td[8]/a",id))).click();
   //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
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
    submitContactCreation();
    contactCache = null;
    returnToContactPage();
  }

  public void create(ContactGeneral contact, boolean creation) {
    initContactCreation();
    fillGeneralContact (contact, creation);
    submitContactCreation();
    contactCache = null;
    returnToContactPage();
  }


  public void modify(ContactGeneral contact, boolean creation) {
    initContactModificationById(contact.getId());
    fillGeneralContact(contact, creation);
    submitContactModification();
    contactCache = null;
    returnToContactPage();
  }
  public void addToGroup(ContactGeneral contact, boolean creation, GroupData group) {
    selectVisibleAllGroups(); //
    waitSelectContactById(contact.getId());
    selectAddingGroupByID1(contact,group);

    submitAddingGroup();
    //returnToContactsGroupPage(contact.getGroups().iterator().next().getId());
    returnToContactsGroupPage(group.getName());

  }
  public void removeFromGroup(ContactGeneral contact, boolean creation, GroupData group) {
    waitSelectGroupByID1(group);
    waitSelectContactById(contact.getId());

    waitSubmitRemovingGroup();
    //returnToContactsGroupPage(contact.getGroups().iterator().next().getId());
    returnToContactsGroupPage(group.getName());

  }


  public void returnToContactsGroupPage1(int id) {
    wd.findElement(By.cssSelector("a[href=\"./?group=" + id + "\"]")).click();
  }
  public void returnToContactsGroupPage(String name) {
   // wd.findElement(By.cssSelector("a[href=\"./?group=" + id + "\"]")).click();
    //wd.findElements(By.linkText("group page \"" + name + "\""));
    click(By.linkText("group page \"" + name + "\""));
  }

  public void selectAddingGroupByID(ContactGeneral contact) {
    if (contact.getGroups().size()>0) {
      Assert.assertTrue(contact.getGroups().size()==1);
      new Select(wd.findElement(By.name("to_group"))).selectByValue(valueOf(contact.getGroups().iterator().next().getId()));

    }

  }
  public void selectGroupByID(GroupData group) {
   // if (group.getContacts().size()>0) {
   //   Assert.assertTrue(group.getContacts().size()==1);
     // new Select(wd.findElement(By.name("group"))).selectByValue(valueOf(group.getId()));
       //new Select(wd.findElement(By.name("group"))).selectByValue(valueOf(contact.getGroups().iterator().next().getId()));
    selectByValue(By.name("group"),valueOf(group.getId()));
  }
  public void waitSelectGroupByID(GroupData group) {
    Wait<WebDriver> wait = new WebDriverWait(wd, 5, 5000);
    wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.name("group"))));
    selectByValue(By.name("group"),valueOf(group.getId()));
  }
  public void waitSelectGroupByID1(GroupData group) {
    Wait<WebDriver> wait = new WebDriverWait(wd, 5, 5000);
    wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.name("group"))));
    click((By.name("group")));
    selectByValue(By.name("group"),valueOf(group.getId()));
    //click(By.name(group.getName()));
    //click(By.name("group"));
  }
  public void selectVisibleAllGroups() {
    click(By.name("group"));
    selectByVisibleText(By.name("group"),valueOf("[all]"));


  }

  public void selectAddingGroupByID1(ContactGeneral contact, GroupData group) {
   // if (contact.getGroups().size()>0) {
    //  Assert.assertTrue(contact.getGroups().size()==1);
    click(By.name("to_group"));
      selectByValue(By.name("to_group"),valueOf(group.getId()));

  }
  public void selectAddingGroupByName(ContactGeneral contact,GroupData group) {
    if (contact.getGroups().size()>0) {
      Assert.assertTrue(contact.getGroups().size()==1);
      selectByVisibleText(By.name("to_group"),group.getName());
    }
  }
  //public void selestG (GroupData group, ContactGeneral contact){
   // if (GroupData group == Arrays.asList(groups.iterator().next().getId().contains(contact.getGroups().iterator().next().getId());{
 //       )
 // }


  public void submitAddingGroup() {
    click(By.name("add"));
  }
  public void submitRemovingGroup() {
    click(By.name("remove"));
  }
  public void waiter(){
    Wait<WebDriver> wait = new WebDriverWait(wd, 5, 1000);
    wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.name("remove"))));
    wait.until(ExpectedConditions.elementToBeClickable(wd.findElement(By.name("remove")))).click();
  }
  public void waitSubmitRemovingGroup(){
    Wait<WebDriver> wait = new WebDriverWait(wd, 5, 5000);
    wait.until(ExpectedConditions.elementToBeClickable(wd.findElement(By.name("remove")))).click();
  }


  public void delete(ContactGeneral contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
  }

  public int count() {
    return wd.findElements(By.cssSelector("tr[name=\"entry\"]")).size();
  }

  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
    for (WebElement element : elements){
      String name = element.findElement(By.xpath("./td[3]")).getText();
      String lastName = element.findElement(By.xpath("./td[2]")).getText();
      String allPhones = element.findElement(By.xpath("./td[6]")).getText();
      String allEmails = element.findElement(By.xpath("./td[5]")).getText();
      String address = element.findElement(By.xpath("./td[4]")).getText();
      int id = Integer.parseInt(element.findElement(By.xpath("./td/input")).getAttribute("value"));
      contactCache.add(new ContactGeneral().withId(id).withName(name).withLastName(lastName)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return new Contacts(contactCache);
  }
  public void fillGeneralContact(ContactGeneral groupGeneral, boolean creation) {
    type(By.name("firstname"),groupGeneral.getName());
    type(By.name("lastname"),groupGeneral.getLastName());
    attach(By.name("photo"), groupGeneral.getPhoto());
    type((By.name("address")),groupGeneral.getAddress());
    type((By.name("home")),groupGeneral.getHomeNumber());
    type((By.name("mobile")),groupGeneral.getMobileNumber());
    type((By.name("work")),groupGeneral.getWorkNumber());
    type((By.name("email")),groupGeneral.getEmail1());
    type((By.name("email2")),groupGeneral.getEmail2());
    type((By.name("email3")),groupGeneral.getEmail3());
    if (creation)  {
      if (groupGeneral.getGroups().size()>0) {
        Assert.assertTrue(groupGeneral.getGroups().size()==1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupGeneral.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse((isElementPresent(By.name("new_group"))));
    }

  }


  public ContactGeneral infoFromEditForm(ContactGeneral contact) {
    initContactModificationById(contact.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactGeneral().withId(contact.getId()).withName(name)
            .withLastName(lastname).withHomeNumber(home).withMobileNumber(mobile)
            .withWorkNumber(work).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);


  }

}
