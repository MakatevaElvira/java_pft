package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.goTo().contactPage();
    Set<ContactGeneral> before = app.contact().all();
    ContactGeneral contactG = new ContactGeneral().withName("Efilia").withLastname("Liopda");
    app.contact().createContactGeneral(contactG);

    Set<ContactGeneral> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contactG.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contactG);
    Assert.assertEquals(before, after);

  }

}
