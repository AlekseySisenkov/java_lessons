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
    int elementCountOfContacts = listOfContacts.size();
    ArrayList<ContactsInGroupData> listOfBefore = new ArrayList<>(before);
    int elementCountOfBefore = listOfBefore.size();
    //ContactsInGroupData beforeForGroup = before.stream().iterator().next();
    ContactsInGroupData beforeForGroup = listOfBefore.get(0);
   // ContactsInGroupData beforeForContact = before.stream().iterator().next();
    ContactsInGroupData beforeForContact = listOfBefore.get(0);
    ContactData addedContact = listOfContacts.get(0);
 /*  GroupData randomGroup = new GroupData().withId(listOfGroups.get(random.nextInt(listOfGroups.size())).getId());
    ContactData randomContact = new ContactData().withId(listOfContacts.get(random.nextInt(listOfContacts.size())).getId())
            .withIdGroup(randomGroup.getId());*/
     //ContactsInGroupData contactInGroup = new ContactsInGroupData().withContact(randomContact.getId()).withGroup(randomGroup.getId());
    //  ContactsInGroupData contactInGroup = new ContactsInGroupData().withContact(209).withGroup(209);
    //  ContactsInGroup listOfContactInGroup = new ContactsInGroup();
    ContactsInGroupData contactInGroup = null;
    outer:
      for(int i = 0; i < elementCountOfContacts; i++){
        for (int k = 0; k < elementCountOfBefore; k++) {
          if (addedContact.getId() == beforeForContact.getContact()) {
            contactInGroup = new ContactsInGroupData().withContact(addedContact.getId()).withGroup(beforeForGroup.getGroup());
            for (int j = 0; j < elementCountOfBefore; j++) {
              if (addedContact.getIdGroup() == beforeForGroup.getGroup()) {
                beforeForGroup = listOfBefore.get(j);
              }else {
                contactInGroup = new ContactsInGroupData().withContact(beforeForContact.getContact()).withGroup(beforeForGroup.getGroup());
                addedContact = new ContactData().withId(contactInGroup.getContact()).withIdGroup(contactInGroup.getGroup());
                break outer;
              }
            }
          }  else beforeForContact = listOfBefore.get(k);
        }
        addedContact = listOfContacts.get(i);
      }


    // while((contactInGroup.getGroup() == before.iterator().next().getGroup())&&(contactInGroup.getContact() == before.iterator().next().getContact())){
   /* while(listOfContactInGroup == before){
    randomGroup = new GroupData().withId(listOfGroups.get(random.nextInt(listOfGroups.size())).getId());
        randomContact = new ContactData().withId(listOfContacts.get(random.nextInt(listOfContacts.size())).getId())
                .withIdGroup(randomGroup.getId());
        contactInGroup = new ContactsInGroupData().withContact(randomContact.getId()).withGroup(randomGroup.getId());
      for (int i = 0; i < elementCount; i++){
        listOfContactInGroup.add(contactInGroup);
      }
    }*/
    app.goTo().homePage();
    app.contact().addInGroup(addedContact);

    ContactsInGroup after = app.db().contactsInGroup();
    /*if (after.size() == before.size()) System.out.println("Контакт уже содержится в группе");
    else */
      assertThat(after, equalTo(before.withAdded(contactInGroup)));
  }
}
