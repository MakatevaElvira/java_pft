package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.contact().all();
    ContactGeneral modifyContact =  before.iterator().next();
    ContactGeneral contactG = new ContactGeneral().withId(modifyContact.getId()).withName("Elle").withLastname( "Mak");
    app.contact().modify(contactG);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() );
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contactG)));

    app.getSessionHelper().logout();

  }

}
