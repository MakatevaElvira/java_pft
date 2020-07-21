package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();

    GroupData modifyGroup = before.iterator().next() ;
    GroupData group = new GroupData()
            .withId(modifyGroup.getId()).withName("Test1").withHeader("Header1").withFooter("Footer1");
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.withOut(modifyGroup).withAdded(group)));
    verifyGroupListInUI();
  }

  @Test  (enabled = false)
  public void testGroupModificationOld() {
    Groups before = app.group().all();
    GroupData modifyGroup = before.iterator().next() ;
    GroupData group = new GroupData()
            .withId(modifyGroup.getId()).withName("Test1").withHeader("Header1").withFooter("Footer1");
    app.group().modify(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withOut(modifyGroup).withAdded(group)));
  }

}
