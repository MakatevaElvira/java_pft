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
              new ContactCompanyInfo("Bank", "Saratov"),
              new ContactNumber("+79008885522", "+79007775522", "+79005552255"),
              new ContactEmails("mak@mail.ru", "mak1@mail.ru", null),
              new ContactBirth("1", "December", "1990"),
              "Test1",
              new ContactSecondary(null, null, null), true);
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
