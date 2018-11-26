package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
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
  public void testContactDeletion(){
    Contacts before = app.contact().all();
    ContactGeneral deletedContact =  before.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(deletedContact.getId()).withName("Elle").withLastname( "Mak");
    app.contact().delete(contact);
    app.goTo().contactPage();
    assertThat(app.contact().count(), equalTo( before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withOut(deletedContact)));
  }
}
