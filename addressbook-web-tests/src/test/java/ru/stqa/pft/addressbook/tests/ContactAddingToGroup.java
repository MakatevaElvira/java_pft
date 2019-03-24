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
    Groups groups = app.db().groups();  // проработать список!!!!!
    Contacts before = app.db().contacts();  // проработать список!!!!

    //app.goTo().contactPage();
    GroupData groupA = groups.iterator().next();
    GroupData group = new GroupData().withId(groupA.getId()).withName(groupA.getName());
    ContactGeneral modifyContact = before.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId());
    //.inGroup(modifyContact.getGroups().iterator().next().getId());//.inGroup(modifyContact.getGroups());
    //if (contact.withId(modifyContact.getId()).getGroups().contains(group.withId(groupA.getId()))

    GroupData groupB = new GroupData().withName("Test1").withId(groups.iterator().next().getId());
    if (modifyContact.getGroups().contains(groupA)) {
      app.goTo().groupPage();
      app.group().create(groupB);
      app.goTo().contactPage();
      app.contact().addToGroup(contact, false, groupB);
    } else app.contact().addToGroup(contact, false, group);
    app.goTo().contactPage();
  }

  @Test
  public void testContactRemoveFromGroup() {
    Groups groups = app.db().groups();  // проработать список!!!!!
    Contacts before = app.db().contacts();  // проработать список!!!!


    ContactGeneral modifyContact = before.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId());
    //.inGroup(modifyContact.getGroups().iterator().next().getId());//.inGroup(modifyContact.getGroups());
    //if (contact.withId(modifyContact.getId()).getGroups().contains(group.withId(groupA.getId()))
    GroupData groupA = groups.iterator().next();
    GroupData group = new GroupData().withId(groupA.getId());

    //File photo = new File("src/test/resources/stru.png");
    //ContactGeneral contactB = new ContactGeneral().withName("Efilia").withLastName("Liopda").withLastName("Makateva")
            //.withAddress("410003, г.Саратов ул.Кирова д.1")
           // .withEmail("myemail@bk.ru").withEmail2("youemail@gmail.ru").withEmail3("ouremail@yandex.ru")
           // .withHomeNumber("777").withMobileNumber("111-7").withWorkNumber("25 12 2").withPhoto(photo)
           // .inGroup(groupA);

    if (groupA.getContacts().contains(modifyContact)) {
      app.contact().removeFromGroup(contact , false, group);

    }
    else {//app.goTo().contactPage();
    app.contact().addToGroup(contact, false, groupA);
    //app.goTo().contactPage();
    app.contact().removeFromGroup(contact, false, group);

    app.goTo().contactPage();}
  }

  @Test
  public void testContactAddingToGroup2() {
    Groups groups = app.db().groups();  // проработать список!!!!!
    Contacts before = app.db().contacts();  // проработать список!!!!
    GroupData groupA = groups.iterator().next();
    GroupData group = new GroupData().withId(groupA.getId()).withName(groupA.getName());
    ContactGeneral modifyContact = before.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId()).inGroup(groups.iterator().next());
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1"));
    }
    //app.goTo().contactPage();

    //.inGroup(modifyContact.getGroups().iterator().next().getId());//.inGroup(modifyContact.getGroups());

    GroupData groupB = new GroupData().withName("Test1").withId(groups.iterator().next().getId());
    app.contact().selectContactById(contact.getId());
    app.contact().selectAddingGroupByID(contact);

    if (contact.getGroups().contains(group)) {
      app.goTo().groupPage();
      app.group().create(groupB);
      app.goTo().contactPage();
      app.contact().addToGroup(contact, false, groupB);
    } else app.contact().submitAddingGroup();
    //returnToContactsGroupPage(contact.getGroups().iterator().next().getId());
    app.contact().returnToContactsGroupPage(group.getName());
    app.goTo().contactPage();

  }

  @Test(enabled = false)
  public void testContactCreationOld() throws Exception {
    app.goTo().contactPage();
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();

    File photo = new File("src/test/resources/stru.png");
    ContactGeneral contact = new ContactGeneral().withName("Efilia").withLastName("Liopda").withLastName("Makateva")
            .withAddress("410003, г.Саратов ул.Кирова д.1")
            .withEmail("myemail@bk.ru").withEmail2("youemail@gmail.ru").withEmail3("ouremail@yandex.ru")
            .withHomeNumber("777").withMobileNumber("111-7").withWorkNumber("25 12 2").withPhoto(photo)
            .inGroup(groups.iterator().next());
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    Contacts after1 = app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));


  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactGeneral modifyContact = before.iterator().next();
    File photo = new File("src/test/resources/stru.png");
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId()).withName("Elle").withLastName("Mak")
            .withPhoto(photo).withAddress("Moscow city").withMobileNumber("89061498374");
    app.contact().modify(contact, false);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
    verifyContactListInUI();

  }

  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();
    GroupData modifyGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifyGroup.getId()).withName("Test1").withHeader("Header1").withFooter("Footer1");
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.withOut(modifyGroup).withAdded(group)));
    verifyGroupListInUI();
  }
}
