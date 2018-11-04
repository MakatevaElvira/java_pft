package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactFormGeneral(new ContactGeneral("Elle", "Mak"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    app.getSessionHelper().logout();

  }
}
