package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.goTo().contactPage();
    List<ContactGeneral> before = app.contact().list();
    ContactGeneral contactG = new ContactGeneral().withName("Efilia").withLastname("Liopda");
    app.contact().createContactGeneral(contactG);

    List<ContactGeneral> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contactG);
    Comparator<? super ContactGeneral> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
