package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size()==0) {
      app.contact().create(
              new ContactGeneral().withName("Elvira").withLastname("Makateva"),
              new ContactCompanyInfo().withCompany("Bank").withAddressCompany("Saratov"),
              new ContactNumber().withHomeNumber("+79008885522").withMobileNumber("+79007775522").withWorkNumber("+79005552255"),
              new ContactEmails().withEmail1("mak@mail.ru").withEmail2("mak2@mail.ru").withEmail3("mak2@mail.ru"),
              new ContactBirth().withBday("1").withDmonth("December").withByear("1990"),
              "Test1",
              new ContactSecondary().withAddress2("Moscow"), true);
    }
  }
  @Test
  public void testContactDeletion(){
    Set<ContactGeneral> before = app.contact().all();
    ContactGeneral deletedContact =  before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().contactPage();
    Set<ContactGeneral> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
