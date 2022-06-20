package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupsHelper().selectGroup();
    app.getGroupsHelper().initGroupModification();
    app.getGroupsHelper().fillGroupForm(new GroupData("sdg", "tessdgsdgt2", "sdggsd"));
    app.getGroupsHelper().submitGroupModification();
    app.getGroupsHelper().returntoGroupPage();

  }
}
