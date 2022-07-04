package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddInGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size()==0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("atest2"));}
    if (app.db().contacts().size()==0) app.contact().create(new ContactData().withFistn("sdg").withLastn("Tesdgst3"));
  }
  @Test
  public void testContactAddInGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    ContactsInGroup before = app.db().contactsInGroup();
    ContactData addedContact = new ContactData().withId(app.contact().selectContactForAddInGroup(groups, contacts, before).getContact())
            .withIdGroup(app.contact().selectContactForAddInGroup(groups, contacts, before).getGroup());
    app.goTo().homePage();
    app.contact().addInGroup(addedContact);

    ContactsInGroup after = app.db().contactsInGroup();
    if(after.size() == before.size()) System.out.println("Контакт уже содержится в группе");

      assertThat(after, equalTo(before.withAdded(app.contact().selectContactForAddInGroup(groups, contacts, before))));
  }
}
