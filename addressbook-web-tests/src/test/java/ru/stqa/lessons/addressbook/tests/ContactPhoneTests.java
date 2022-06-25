package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

  @Test
  public void testContactPhones(){
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditFom(contact);

    assertThat(contact.getHomep(), equalTo(cleaned(contactInfoFromEditForm.getHomep())));
    assertThat(contact.getMobilep(), equalTo(cleaned(contactInfoFromEditForm.getMobilep())));
    assertThat(contact.getWorkp(), equalTo(cleaned(contactInfoFromEditForm.getWorkp())));
  }

  public String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
