package ru.stqa.lessons.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{
  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Test1", "Test2",
            "Test3", "Aleksey", "example@test.ru", "+71231451617",
            null);
    app.getContactHelper().createContact(contact);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before,after);
  }
}
