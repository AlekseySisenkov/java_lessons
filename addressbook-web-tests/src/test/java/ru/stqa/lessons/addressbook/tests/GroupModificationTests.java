package ru.stqa.lessons.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupsHelper().isThereGroup()){
      app.getGroupsHelper().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupsHelper().getGroupList();
    app.getGroupsHelper().selectGroup(before.size()-1);
    app.getGroupsHelper().initGroupModification();
    GroupData  group = new GroupData(before.get(before.size()-1).getId(),"test1", "tessdgsdgt2", "sdggsd");
    app.getGroupsHelper().fillGroupForm(group);
    app.getGroupsHelper().submitGroupModification();
    app.getGroupsHelper().returntoGroupPage();
    List<GroupData> after = app.getGroupsHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size()-1);
    before.add(group);
    Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
