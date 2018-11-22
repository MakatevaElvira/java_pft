package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().goToContactPage();
    List<ContactGeneral> before = app.getContactHelper().getContactList();
    ContactGeneral contactG = new ContactGeneral("Efilia", "Liopda");
    app.getContactHelper().createContactGeneral(contactG);

    List<ContactGeneral> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contactG.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contactG);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));


    app.getSessionHelper().logout();
  }

}
