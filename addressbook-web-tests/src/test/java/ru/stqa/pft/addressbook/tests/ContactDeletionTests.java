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
    if (app.db().contacts().size()==0) {
      app.contact().create(new ContactGeneral().withName("Elvira").withLastName("Makateva")
              .withAddress("410003, г.Саратов ул.Кирова д.1")
              .withEmail("myemail@bk.ru").withEmail2("youemail@gmail.ru").withEmail3("ouremail@yandex.ru")
              .withHomeNumber("777").withMobileNumber("111-7").withWorkNumber("25 12 2"),false);
    }
  }
  @Test
  public void testContactDeletion(){
    Contacts before = app.db().contacts();
    ContactGeneral deletedContact =  before.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(deletedContact.getId()).withName("Elle").withLastName( "Mak");
    app.contact().delete(contact);
    app.goTo().contactPage();
    assertThat(app.contact().count(), equalTo( before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(deletedContact)));
    verifyContactListInUI();
  }
}
