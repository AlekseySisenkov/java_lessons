package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;
public class ContactCreationTests extends TestBase{
  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactData("Test1", "Test2",
            "Test3", "Aleksey", "example@test.ru", "+71231451617",
            "test1"), true);
    app.getNavigationHelper().gotoHomePage();
  }

}
