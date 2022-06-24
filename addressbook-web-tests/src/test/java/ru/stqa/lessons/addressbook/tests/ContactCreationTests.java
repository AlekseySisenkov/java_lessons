package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;
import ru.stqa.lessons.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase{
  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFistn("Test1").withMiddlen("Test2").withLastn("Test3").withNickn("Aleksey").withMail("example@test.ru").withHomep("+71231451617");
    app.contact().create(contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();

    assertEquals(after.size(),before.size()+1);
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
