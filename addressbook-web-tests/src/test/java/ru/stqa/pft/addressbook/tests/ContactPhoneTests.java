package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    ContactGeneral contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomeNumber(), equalTo(cleaned(contactInfoFromEditForm.getHomeNumber())));
    assertThat(contact.getMobileNumber(), equalTo(cleaned(contactInfoFromEditForm.getMobileNumber())));
    assertThat(contact.getWorkNumber(), equalTo(cleaned(contactInfoFromEditForm.getWorkNumber())));
  }
  public String cleaned(String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }

}
