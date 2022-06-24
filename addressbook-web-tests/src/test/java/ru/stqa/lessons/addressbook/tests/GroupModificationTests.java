package ru.stqa.lessons.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupsHelper().isThereGroup()){
      app.getGroupsHelper().createGroup(new GroupData("test1", null, null));
    }
  }
  @Test
  public void testGroupModification() {
    List<GroupData> before = app.getGroupsHelper().getGroupList();
    int index = before.size() -1;
    GroupData  group = new GroupData(before.get(index).getId(),"test1", "tessdgsdgt2", "sdggsd");
    app.getGroupsHelper().modifyGroup(index, group);
    List<GroupData> after = app.getGroupsHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
