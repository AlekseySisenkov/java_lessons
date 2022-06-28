package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.*;

import java.security.SecureRandom;
import java.util.ArrayList;

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
    var random = new SecureRandom();
    var listOfGroups = new ArrayList<>(groups);
    var listOfContacts = new ArrayList<>(contacts);
    GroupData randomGroup = new GroupData().withId(listOfGroups.get(random.nextInt(listOfGroups.size())).getId());
    ContactData randomContact = new ContactData().withId(listOfContacts.get(random.nextInt(listOfContacts.size())).getId())
            .withIdGroup(randomGroup.getId());
    ContactsInGroupData contactInGroup = new ContactsInGroupData().withContact(randomContact.getId()).withGroup(randomGroup.getId());
    app.goTo().homePage();
    app.contact().addInGroup(randomContact);

    ContactsInGroup after = app.db().contactsInGroup();
    if(after.size() == before.size()) System.out.println("Контакт уже содержится в группе");
    else assertThat(after, equalTo(before.withAdded(contactInGroup)));
    }
}
