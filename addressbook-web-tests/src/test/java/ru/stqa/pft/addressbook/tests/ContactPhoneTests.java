package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;

public class ContactPhoneTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactGeneral().withName("Elvira").withLastname("Makateva"));
    }
  }
  @Test
  public void testContactPhone() throws Exception {
    app.goTo().contactPage();
    ContactGeneral contact = app.contact().all().iterator().next();
    ContactGeneral contactInfoFromeEditForm = app.contact().infoFromEditForm(contact);

  }
}
