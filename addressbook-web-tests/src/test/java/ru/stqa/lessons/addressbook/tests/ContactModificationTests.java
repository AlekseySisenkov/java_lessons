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
    if (app.db().contacts().size()==0){
      app.contact().create(new ContactData().withFistn("sdg").withMiddlen("sdg").withLastn("Tesdgst3")
              .withNickn("Alesdgksey").withGroup("test1"));
    }
  }
  @Test
  public void testContactModification(){
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFistn("test1").withLastn("sdg")
            .withAddress("hjasvfjghfd").withHomep("87685").withMobilep("870975875").withWorkp("789754")
            .withEmail("76985").withEmail2("9868579").withEmail3("hgjgf");
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
