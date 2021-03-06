package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;
import ru.stqa.lessons.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size()==0){
      app.contact().create(new ContactData().withFistn("sdg").withMiddlen("sdg").withLastn("Tesdgst3")
              .withNickn("Alesdgksey"));
    }
  }
  @Test
  public void testContactDeletion(){
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.goTo().homePage();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(),equalTo(before.size()-1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
