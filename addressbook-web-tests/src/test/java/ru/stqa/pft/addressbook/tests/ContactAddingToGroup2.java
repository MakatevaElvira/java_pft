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

public class ContactAddingToGroup2 extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1"));
    }
  }


  @Test
  public void  testContactAddingToGroup () {
    Contacts before = app.db().contacts();
    app.goTo().contactPage();
    ContactGeneral modifyContact =  before.iterator().next();

    File photo = new File("src/test/resources/stru.png");
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId()).withName("Elle").withLastName( "Mak")
            .withPhoto(photo).withAddress("Moscow city").withMobileNumber("89061498374");
    app.contact().modify(contact, false);
    assertThat(app.contact().count(), equalTo( before.size()));

    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
    verifyContactListInUI();;

  }
  @Test
  public void  testContactAddingToGroup1 () {
    app.goTo().contactPage();
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactGeneral modifyContact =  before.iterator().next();
    ContactGeneral contact = new ContactGeneral().withId(modifyContact.getId());
            //.inGroup(modifyContact.getGroups().iterator().next().getId());//.inGroup(modifyContact.getGroups());
    GroupData group = new GroupData()
            .withId(groups.iterator().next().getId()).withName(groups.iterator().next().getName());
    app.contact().addToGroup(contact,false,group );


  }
}
