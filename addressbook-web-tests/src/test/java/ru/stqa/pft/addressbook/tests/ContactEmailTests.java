package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactGeneral().withName("Elvira").withLastName("Makateva")
              .withEmail("myemail@bk.ru").withEmail2("youemail@gmail.ru").withEmail3("ouremail@yandex.ru"));
    }
  }
  @Test(enabled = false)
  public void testContactEmail() throws Exception {
    app.goTo().contactPage();
    ContactGeneral contact = app.contact().all().iterator().next();
    ContactGeneral contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactGeneral contact) {
    return Arrays.asList(contact.getEmail1(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}
