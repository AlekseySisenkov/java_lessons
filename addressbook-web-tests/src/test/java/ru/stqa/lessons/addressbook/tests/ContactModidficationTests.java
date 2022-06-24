package ru.stqa.lessons.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModidficationTests extends TestBase{
  @Test
  public void testContactModification(){
    if (! app.getContactHelper().isThereContact()){
      app.getContactHelper().createContact(new ContactData("sdg", "sdg",
              "Tesdgst3", "Alesdgksey", "example@test.sdg", "+s32525",
              null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact(before.size()-1);
    ContactData contact = new ContactData("test1", "sdfg",
            "sdg", "hg", "wqe@test.ru",
            "+34636436", "test1");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();


    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before,after);
  }
}
