package ru.stqa.lessons.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.lessons.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEMailTests extends TestBase {

  @Test
  public void testContactEMail() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditFom(contact);

    assertThat(contact.getAllEMail(), equalTo(mergeEMails(contactInfoFromEditForm)));
  }

  private <T> String mergeEMails(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }
}
