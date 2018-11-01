package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.goToContactPage();
    app.initContactCreation();
    app.fillContactForm(new ContactGeneral("Elvira", "Makateva"), new ContactCompanyInfo("Bank", "Saratov"), new ContactNumber("+79008885522", "+79007775522", "+79005552255"), new ContactEmails("mak@mail.ru", "mak1@mail.ru", "mak3@mail.ru"), new ContactBirth("1", "December", "1990"), "Test1", new ContactSecondary("Saratov2", "Saratov3", "else"));
    app.submintContactCreation();
    app.returnToContactPage();
    app.logout();
  }

}
