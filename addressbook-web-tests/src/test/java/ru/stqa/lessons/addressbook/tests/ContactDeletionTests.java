package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
   app.getContactHelper().selecteContact(8);
   app.getContactHelper().deleteSelectedContact();
   app.getContactHelper().allert();
  }

}
