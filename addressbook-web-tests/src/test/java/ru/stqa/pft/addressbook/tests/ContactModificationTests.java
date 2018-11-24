package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
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
              new ContactSecondary().withAddressHome("Miami"), true);
    }
  }
  @Test
  public void testContactModification(){
    List<ContactGeneral> before = app.contact().list();
    int index = before.size()-1;
    ContactGeneral contactG = new ContactGeneral().withId(before.get(index).getId()).withName("Elle").withLastname( "Mak");
    app.contact().modify(index, contactG);
    List<ContactGeneral> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(index);
    before.add(contactG);
    Comparator<? super ContactGeneral> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

    app.getSessionHelper().logout();

  }


}
