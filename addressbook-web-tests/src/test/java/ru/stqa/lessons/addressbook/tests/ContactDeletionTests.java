package ru.stqa.lessons.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereContact()){
      app.getContactHelper().createContact(new ContactData("sdg", "sdg",
              "Tesdgst3", "Alesdgksey", "example@test.sdg", "+s32525",
              "test1"));
    }
    //app.getContactHelper().selecteContact();
   app.getContactHelper().selecteContact(before-1);
   app.getContactHelper().deleteSelectedContact();
   app.getContactHelper().allert();
   app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before-1);
  }

}
