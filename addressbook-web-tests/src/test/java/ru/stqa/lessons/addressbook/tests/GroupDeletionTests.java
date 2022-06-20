package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.Test;
public class GroupDeletionTests extends TestBase{
  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupsHelper().selectGroup();
    app.getGroupsHelper().deleteSelectedGroup();
    app.getGroupsHelper().returntoGroupPage();
  }

}
