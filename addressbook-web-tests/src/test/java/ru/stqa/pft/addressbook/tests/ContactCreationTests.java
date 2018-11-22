package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().goToContactPage();
    List<ContactGeneral> before = app.getContactHelper().getContactList();
    ContactGeneral contactG = new ContactGeneral("Elvira", "Makateva");
    app.getContactHelper().createContactGeneral(contactG);

    List<ContactGeneral> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (ContactGeneral c : after) {
      if (c.getId()> max) {
        max = c.getId();
      }
    }
    contactG.setId(max);
    before.add(contactG);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));


    app.getSessionHelper().logout();
  }

}
