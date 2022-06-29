package ru.stqa.lessons.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ContactsInGroup extends ForwardingSet<ContactsInGroupData> {

  private Set<ContactsInGroupData> delegate;

  public ContactsInGroup() {
    this.delegate = new HashSet<>();
  }
  public ContactsInGroup(Collection<ContactsInGroupData> contactsInGroup) {
    this.delegate = new HashSet<>(contactsInGroup);
  }

  @Override
  protected Set<ContactsInGroupData> delegate() {
    return delegate;
  }

  public ContactsInGroup withAdded(ContactsInGroupData contactInGroup){
    ContactsInGroup contactsInGroup = new ContactsInGroup(this);
    contactsInGroup.add(contactInGroup);
    return contactsInGroup;
  }

  public ContactsInGroup without(ContactsInGroupData contactInGroup){
    ContactsInGroup contactsInGroup = new ContactsInGroup(this);
    contactsInGroup.remove(contactInGroup);
    return contactsInGroup;
  }
}
