package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;
import ru.stqa.lessons.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFistn("sdg").withMiddlen("sdg").withLastn("Tesdgst3")
              .withNickn("Alesdgksey").withGroup("test1"));
    }
  }
  @Test
  public void testContactModification(){
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFistn("test1").withMiddlen("sdfg")
            .withLastn("sdg").withNickn("hg").withGroup("test1");
    app.contact().modify(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
