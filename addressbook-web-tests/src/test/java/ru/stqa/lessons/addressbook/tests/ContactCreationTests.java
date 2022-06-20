package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;
public class ContactCreationTests extends TestBase{
  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().addnewContact();
    app.getContactHelper().fillContactForm(new ContactData("Test1", "Test2", "Test3", "Aleksey", "example@test.ru", "+71231451617"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().gotoHomePage();
  }

}
