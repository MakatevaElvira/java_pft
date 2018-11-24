package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().list().size()==0) {
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
    List<ContactGeneral> before = app.contact().list();
    int index = before.size()-1;
    app.contact().delete(index);
    app.goTo().contactPage();
    List<ContactGeneral> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
