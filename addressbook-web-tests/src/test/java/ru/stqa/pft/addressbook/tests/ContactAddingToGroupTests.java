package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingToGroupTests extends TestBase {
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
  public void testContactAddingToGroup777() { //Идеально!
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();

    GroupData groupA = groups.iterator().next();
    GroupData group = new GroupData().withId(groupA.getId()).withName(groupA.getName());
    GroupData groupN = new GroupData().withName("Test new");
    ContactGeneral modifyContact = contacts.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId()).withName(modifyContact.getName())
            .withLastName(modifyContact.getLastName()).withAddress(modifyContact.getAddress())
            .withMobileNumber(modifyContact.getMobileNumber()).inGroup(group);

    Groups before = modifyContact.getGroups();
    if (modifyContact.getGroups().size() == (app.db().groups().size())) { //пункт 1 готов
      app.goTo().groupPage();
      app.group().create(groupN);
      Groups groupsPlus = app.db().groups();
      app.goTo().contactPage();
      app.contact().addToGroup(modifyContact, false, groupN
              .withId(groupsPlus.stream().mapToInt((g) -> g.getId()).max().getAsInt()));
    } else {
      for (GroupData groupData : groups) {//для всех групп
        if (!groupData.getContacts().contains(modifyContact)) {
          app.goTo().contactPage();
          app.contact().addToGroup(contact, false, groupData);
          return;
        }
      }
    }// проверки:
    ContactGeneral contactById = app.db().getContactById(modifyContact.getId());
    assertThat(contactById.getGroups().size(), equalTo(before.size() + 1));
    Groups after = contactById.getGroups();
    if (before.size() == groups.size()) {
      assertThat(after, equalTo(before.withAdded(groupN)));
    } else assertThat(after, equalTo(before.withAdded(group)));
    app.goTo().groupPage();
    verifyGroupListInUI();
  }

  @Test(enabled = false)
  public void testContactAddingToGroupOld() { //Идеально!
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();

    GroupData groupA = groups.iterator().next();
    GroupData group = new GroupData().withId(groupA.getId()).withName(groupA.getName());
    ContactGeneral modifyContact = before.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId())
            .withName(modifyContact.getName()).withLastName(modifyContact.getLastName())
            .withAddress(modifyContact.getAddress()).withMobileNumber(modifyContact.getMobileNumber())
            .inGroup(group);
    app.goTo().contactPage();
    if (modifyContact.getGroups().contains(groupA)) {//+
      app.contact().removeFromGroup(contact, false, groupA);
    }
    app.contact().addToGroup(contact, false, group); //- in groupA
    app.contact().selectVisibleAllGroups();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
    verifyContactListInUI();
  }

}
