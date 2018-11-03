package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    app.contactHelper.selectGroup();
    app.getGroupHelper().deletSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    app.sessionHelper.logout();
  }

}
