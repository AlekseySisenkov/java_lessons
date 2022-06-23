package ru.stqa.lessons.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupsHelper().getGroupList();
    app.getGroupsHelper().createGroup(new GroupData("test1", null, null));
    List<GroupData> after = app.getGroupsHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size() +1);
  }

}
