package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.*;

import java.security.SecureRandom;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contactsInGroup().size()==0) {
      Groups groups = app.db().groups();
      app.contact().create(new ContactData().withFistn("sdg").withMiddlen("sdg").withLastn("Tesdgst3")
              .withNickn("Alesdgksey").inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactRemoveFromGroup() {
    ContactsInGroup before = app.db().contactsInGroup();
    var random = new SecureRandom();
    var listOfContactsInGroup = new ArrayList<>(before);
    ContactsInGroupData removedContactFromGroup = new ContactsInGroupData().withContact(listOfContactsInGroup.get(random.nextInt(listOfContactsInGroup.size())).getContact())
            .withGroup(listOfContactsInGroup.get(random.nextInt(listOfContactsInGroup.size())).getGroup());
    ContactData removedContact = new ContactData().withId(removedContactFromGroup.getContact()).withIdGroup(removedContactFromGroup.getGroup());
    app.goTo().homePage();
    app.contact().removeContactFromGroup(removedContact);

    ContactsInGroup after = app.db().contactsInGroup();

    assertThat(after, equalTo(before.without(removedContactFromGroup)));
  }
}
