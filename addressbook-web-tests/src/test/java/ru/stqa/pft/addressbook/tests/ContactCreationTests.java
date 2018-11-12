package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().goToContactPage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(
            new ContactGeneral("Elvira", "Makateva"),
            new ContactCompanyInfo("Bank", "Saratov"),
            new ContactNumber("+79008885522", "+79007775522", "+79005552255"),
            new ContactEmails("mak@mail.ru", "mak1@mail.ru", null),
            new ContactBirth("1", "December", "1990"),
            "Test1",
            new ContactSecondary(null, null, null), true);

    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
    app.getSessionHelper().logout();
  }

}
