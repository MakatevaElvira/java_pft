package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests_FF extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("Test1", null, null);
    app.getGroupHelper().createGroup(new GroupData("Test1", null, null));
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() +1);

    int max = 0;
    for (GroupData g : after ) {
      if (g.getId() > max) {
        max =g.getId();
      }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals (new HashSet<>(before), new HashSet<>(after));
    app.getSessionHelper().logout();
  }

}
