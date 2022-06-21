package ru.stqa.lessons.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupsHelper().getGroupCount();
    app.getGroupsHelper().createGroup(new GroupData("test1", null, null));
    int after = app.getGroupsHelper().getGroupCount();
    Assert.assertEquals(after,before+1);
  }

}
