package ru.stqa.lessons.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{
  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupsHelper().isThereGroup()){
      app.getGroupsHelper().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupsHelper().getGroupList();
    app.getGroupsHelper().selectGroup(before.size()-1);
    app.getGroupsHelper().deleteSelectedGroup();
    app.getGroupsHelper().returntoGroupPage();
    List<GroupData> after = app.getGroupsHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(before.size()-1);
      Assert.assertEquals(after,before);
  }

}
