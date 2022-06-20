package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;

public class ContactModidficationTests extends TestBase{
  @Test
  public void testContactModidfication(){
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("asf", "sdfg", "sdg", "hg", "wqe@test.ru", "+34636436"));
    app.getContactHelper().updateContact();
    app.getContactHelper().gotoHomePage();
  }
}
