package ru.stqa.lessons.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    if (! app.getContactHelper().isThereContact()){
      app.getContactHelper().createContact(new ContactData("sdg", "sdg",
              "Tesdgst3", "Alesdgksey", "example@test.sdg", "+s32525",
              "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    //app.getContactHelper().selecteContact();
   app.getContactHelper().selecteContact(before.size()-1);
   app.getContactHelper().deleteSelectedContact();
   app.getContactHelper().allert();
   app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();


    Assert.assertEquals(after.size(),before.size()-1);
  }

}
