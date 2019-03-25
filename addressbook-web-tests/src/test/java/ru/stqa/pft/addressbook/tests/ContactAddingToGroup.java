package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingToGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/stru.png");
      ContactGeneral contact = new ContactGeneral().withName("Elle").withLastName("Mak")
              .withPhoto(photo).withAddress("Moscow city").withMobileNumber("89061498374");
      app.goTo().contactPage();
      app.contact().create(contact, true);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1"));
    }
  }

  @Test
  public void testContactAddingToGroup1() {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();

    GroupData groupA = groups.iterator().next();
    GroupData group = new GroupData().withId(groupA.getId()).withName(groupA.getName());
    ContactGeneral modifyContact = before.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId());
    GroupData groupB = new GroupData().withName("Test1").withId(groups.iterator().next().getId());
    if (modifyContact.getGroups().contains(groupA)) {
      app.goTo().groupPage();
      app.group().create(groupB);
      app.goTo().contactPage();
      app.contact().addToGroup(contact, false, groupB);
    } else {
      app.goTo().contactPage();
      app.contact().addToGroup(contact, false, group);
    }
    app.goTo().contactPage();
  }

  @Test
  public void testContactRemoveFromGroup() {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();

    ContactGeneral modifyContact = before.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId());
    GroupData groupA = groups.iterator().next();
    GroupData group = new GroupData().withId(groupA.getId());
    if (groupA.getContacts().contains(modifyContact)) {
      app.goTo().contactPage();
      app.contact().removeFromGroup(contact, false, group);

    } else {
      app.goTo().contactPage();
      app.contact().addToGroup(contact, false, groupA);
      app.goTo().contactPage();
      app.contact().removeFromGroup(contact, false, group);
      app.goTo().contactPage();
    }
  }
}
