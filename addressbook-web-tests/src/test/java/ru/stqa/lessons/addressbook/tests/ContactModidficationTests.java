package ru.stqa.lessons.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;

public class ContactModidficationTests extends TestBase{
  @Test
  public void testContactModification(){
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereContact()){
      app.getContactHelper().createContact(new ContactData("sdg", "sdg",
              "Tesdgst3", "Alesdgksey", "example@test.sdg", "+s32525",
              null));
    }
    app.getContactHelper().editContact(before-1);
    app.getContactHelper().fillContactForm(new ContactData("test1", "sdfg",
            "sdg", "hg", "wqe@test.ru",
            "+34636436", "test1"), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before);
  }
}
