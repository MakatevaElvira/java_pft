package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.goTo().contactPage();
    Contacts before = app.contact().all();
    ContactGeneral contact = new ContactGeneral().withName("Efilia").withLastname("Liopda").withLastname("Makateva")
            .withAdress("410003, г.Саратов ул.Кирова д.1")
            .withEmail("myemail@bk.ru").withEmail2("youemail@gmail.ru").withEmail3("ouremail@yandex.ru")
            .withHomeNumber("777").withMobileNumber("111-7").withWorkNumber("25 12 2");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo( before.size() + 1));
    Contacts after =  app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

}
