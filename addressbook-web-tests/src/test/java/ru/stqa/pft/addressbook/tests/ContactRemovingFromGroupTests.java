package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemovingFromGroupTests extends TestBase {
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
  public void testContactRemovingFromGroup777() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    GroupData groupA = groups.iterator().next();
    GroupData group = new GroupData().withId(groupA.getId()).withName(groupA.getName());
    ContactGeneral modifyContact = contacts.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId())
            .withName(modifyContact.getName()).withLastName(modifyContact.getLastName())
            .withAddress(modifyContact.getAddress()).withMobileNumber(modifyContact.getMobileNumber())
            .inGroup(group);
    app.goTo().contactPage();

    Groups before = modifyContact.getGroups();
    if (modifyContact.getGroups().size() == 0) { //пункт 1 готов
      app.contact().addToGroup(modifyContact, false, group); // id group
      app.contact().removeFromGroup(modifyContact, false, group);
    } else {
      for (GroupData groupData : groups) {//для всех групп
        if (groupData.getContacts().contains(modifyContact)) {
          app.contact().removeFromGroup(contact, false, groupData);
          return;
        }
      }
    }
    if (before.size() == 0) {
      assertThat(modifyContact.getGroups().size(), equalTo(before.size()));
    } else assertThat(modifyContact.getGroups().size(), equalTo(before.size() - 1));

    Groups after = modifyContact.getGroups();
    if (before.size() > 0) {
      assertThat(after, equalTo(before.withOut(group)));
    }
    app.goTo().groupPage();
    verifyGroupListInUI();
  }


  @Test(enabled = false)
  public void testContactRemoveFromGroupOld(GroupData group) { //Идеально!
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();

    GroupData groupA = groups.iterator().next();
    //GroupData group = new GroupData().withId(groupA.getId()).withName(groupA.getName());
    ContactGeneral modifyContact = before.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId())
            .withName(modifyContact.getName()).withLastName(modifyContact.getLastName())
            .withAddress(modifyContact.getAddress()).withMobileNumber(modifyContact.getMobileNumber())
            .inGroup(group);
    app.goTo().contactPage();
    if (modifyContact.getGroups().contains(groupA)) {//+
      app.contact().removeFromGroup(modifyContact, false, groupA);
    }
    app.contact().addToGroup(modifyContact, false, group);
    app.contact().removeFromGroup(contact, false, group);
    app.contact().selectVisibleAllGroups();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
