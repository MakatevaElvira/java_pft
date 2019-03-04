package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGeneralTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactGeneral().withName("Elvira").withLastname("Makateva")
              .withAdress("410003, г.Саратов ул.Кирова д.1")
              .withEmail("myemail@bk.ru").withEmail2("youemail@gmail.ru").withEmail3("ouremail@yandex.ru")
              .withHomeNumber("777").withMobileNumber("111-7").withWorkNumber("25 12 2"));
    }
  }

  @Test
  public void testContactPhone() throws Exception {
    app.goTo().contactPage();
    ContactGeneral contact = app.contact().all().iterator().next();
    ContactGeneral contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

  }

  private String mergePhones(ContactGeneral contact) {
    return Arrays.asList(contact.getHomeNumber(), contact.getMobileNumber(), contact.getWorkNumber())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactGeneralTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "")
            .replaceAll("[-()]", "");
  }
  private String mergeEmails(ContactGeneral contact) {
    return Arrays.asList(contact.getEmail1(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
