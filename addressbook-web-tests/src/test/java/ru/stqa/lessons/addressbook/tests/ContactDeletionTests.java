package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    if (! app.getContactHelper().isThereContact()){
      app.getContactHelper().createContact(new ContactData("sdg", "sdg",
              "Tesdgst3", "Alesdgksey", "example@test.sdg", "+s32525",
              "test1"), true);
    }
   app.getContactHelper().selecteContact(10);
   app.getContactHelper().deleteSelectedContact();
   app.getContactHelper().allert();
  }

}
