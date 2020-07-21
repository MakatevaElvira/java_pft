package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
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
  public void testContactModification(){
    Contacts before = app.db().contacts();
    ContactGeneral modifyContact =  before.iterator().next();
    File photo = new File("src/test/resources/stru.png");
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId()).withName("Elle").withLastName( "Mak")
            .withPhoto(photo).withAddress("Moscow city").withMobileNumber("89061498374");
    app.contact().modify(contact, false);
    assertThat(app.contact().count(), equalTo( before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
    verifyContactListInUI();

  }




}
