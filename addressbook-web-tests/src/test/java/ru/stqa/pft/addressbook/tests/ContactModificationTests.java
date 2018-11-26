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
      app.contact().create(new ContactGeneral().withName("Elvira").withLastname("Makateva")
              .withAdress("410003, г.Саратов ул.Кирова д.1")
              .withEmail("myemail@bk.ru").withEmail2("youemail@gmail.ru").withEmail3("ouremail@yandex.ru")
              .withHomeNumber("777").withMobileNumber("111-7").withWorkNumber("25 12 2"));
    }
  }
  @Test
  public void testContactModification(){
    Contacts before = app.contact().all();
    ContactGeneral modifyContact =  before.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId()).withName("Elle").withLastname( "Mak");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo( before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));

  }

}
