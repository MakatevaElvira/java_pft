package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

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
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().goToContactPage();
    app.getSessionHelper().logout();
  }
}
