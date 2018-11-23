package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test (enabled = false)
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().goToContactPage();
    List<ContactGeneral> before = app.getContactHelper().getContactList();
    ContactGeneral contactG = new ContactGeneral("Efilia", "Liopda");
    app.getContactHelper().createContactGeneral(contactG);

    List<ContactGeneral> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contactG);
    Comparator<? super ContactGeneral> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);


    app.getSessionHelper().logout();
  }

}
