package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

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
    List<ContactGeneral> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size()-1);
    ContactGeneral contactG = new ContactGeneral(before.get(before.size()-1).getId(),"Elle", "Mak");
    app.getContactHelper().fillGeneralContact(contactG);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    List<ContactGeneral> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(before.size()-1);
    before.add(contactG);
    Comparator<? super ContactGeneral> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

    app.getSessionHelper().logout();

  }
}
