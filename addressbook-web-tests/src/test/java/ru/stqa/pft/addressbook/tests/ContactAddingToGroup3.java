package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingToGroup3 extends TestBase {
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
  public void testContactAdd777() { //Идеально!
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();

    GroupData groupA = groups.iterator().next();
    GroupData group = new GroupData().withId(groupA.getId()).withName(groupA.getName());
    GroupData groupN = new GroupData().withName("Test new");


    ContactGeneral modifyContact = contacts.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId())
            .withName(modifyContact.getName()).withLastName(modifyContact.getLastName())
            .withAddress(modifyContact.getAddress()).withMobileNumber(modifyContact.getMobileNumber())
            .inGroup(group);

    Groups before = modifyContact.getGroups();
    if (modifyContact.getGroups().size() == (app.db().groups().size())) { //пункт 1 готов
      app.goTo().groupPage();
      app.group().create(group);
      Groups groupsPlus = app.db().groups();
      app.goTo().contactPage();
      app.contact().addToGroup(modifyContact, false, group
              .withId(groupsPlus.stream().mapToInt((g) -> g.getId()).max().getAsInt()));
    } else {
      for (GroupData groupData : groups) {//для всех групп
        if (!groupData.getContacts().contains(modifyContact)) {
          app.goTo().contactPage();
          app.contact().addToGroup(contact, false, groupData);
          return;
        }
      }
    }
    Groups groupsPlus = app.db().groups();
    if (before.size() == groups.size()) {
      assertThat(modifyContact.getGroups().withAdded(group).size(), equalTo(before.size() + 1));
    } else     assertThat(modifyContact.getGroups().size(), equalTo(before.size() + 1));



    // ПОЛУЧИТЬ КОНТАКТ ИЗ БАЗЫ ПО ИД И ВЗЯТЬ ЕГО ГРУППЫ!!!
  // Contacts contactById = app.db().getContactById(modifyContact.getId());
   //Groups after = contactById.getGroups();

    ContactGeneral contactById1 = app.db().getContactById1(modifyContact.getId());
    Groups after1 = contactById1.getGroups();

    //Groups after = modifyContact.getGroups();
    if (before.size() < groups.size()){
      System.out.println("after= " + after1);
      System.out.println("before= " + before);

      Assert.assertTrue(modifyContact.getGroups().withAdded(group).contains(group
              .withId(groupsPlus.stream().mapToInt((g) -> g.getId()).max().getAsInt())));
    } else  assertThat(after1, equalTo(before.withAdded(group)));


  }

  @Test
  public void testContactRemove777() {
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
  }
}
