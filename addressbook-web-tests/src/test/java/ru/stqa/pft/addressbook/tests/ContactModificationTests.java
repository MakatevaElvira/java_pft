package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {
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
              new ContactSecondary().withAddressHome("Miami"), true);
    }
  }
  @Test
  public void testContactModification(){
    Set<ContactGeneral> before = app.contact().all();
    ContactGeneral modifyContact =  before.iterator().next();
    ContactGeneral contactG = new ContactGeneral().withId(modifyContact.getId()).withName("Elle").withLastname( "Mak");
    app.contact().modify(contactG);
    Set<ContactGeneral> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(modifyContact);
    before.add(contactG);
    Assert.assertEquals(before, after);

    app.getSessionHelper().logout();

  }


}
