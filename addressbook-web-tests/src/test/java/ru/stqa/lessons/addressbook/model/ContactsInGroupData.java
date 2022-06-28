package ru.stqa.lessons.addressbook.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "address_in_groups")
public class ContactsInGroupData {


  @Id
  @Column(name = "id")
  private int contact/* = Integer.MAX_VALUE*/;

  @Id
  @Column(name = "group_id")
  private int group/* = Integer.MAX_VALUE*/;

  public int getContact() {
    return contact;
  }

  public ContactsInGroupData withContact(int contact) {
    this.contact = contact;
    return this;
  }

  public int getGroup() {
    return group;
  }

  public ContactsInGroupData withGroup(int group) {
    this.group = group;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactsInGroupData that = (ContactsInGroupData) o;
    return contact == that.contact && group == that.group;
  }

  @Override
  public int hashCode() {
    return Objects.hash(contact, group);
  }

  @Override
  public String toString() {
    return "ContactsInGroupData{" +
            "contact=" + contact +
            ", group=" + group +
            '}';
  }
}
