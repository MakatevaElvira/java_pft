package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
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
    List<ContactGeneral> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().goToContactPage();
    List<ContactGeneral> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    app.getSessionHelper().logout();
  }
}
