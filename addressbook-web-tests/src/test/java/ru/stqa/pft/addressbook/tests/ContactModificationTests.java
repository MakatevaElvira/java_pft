package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToContactPage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(
              new ContactGeneral("Elvira", "Makateva"),
              new ContactCompanyInfo("Bank", "Saratov"),
              new ContactNumber("+79008885522", "+79007775522", "+79005552255"),
              new ContactEmails("mak@mail.ru", "mak1@mail.ru", null),
              new ContactBirth("1", "December", "1990"),
              "Test1",
              new ContactSecondary(null, null, null), true);
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(
            new ContactGeneral("Elle", "Mak"),
            new ContactCompanyInfo("Bank", "Saratov"),
            new ContactNumber("+79008885522", "+79007775522", "+79005552255"),
            new ContactEmails("mak@mail.ru", "mak1@mail.ru", "mak3@mail.ru"),
            new ContactBirth("1", "December", "1990"),
            null,
            new ContactSecondary("Saratov2", "Saratov3", "else"), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before );
    app.getSessionHelper().logout();

  }
}
