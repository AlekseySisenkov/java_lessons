package ru.stqa.lessons.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String fistn;
  private final String middlen;
  private final String lastn;
  private final String nickn;
  private final String mail;
  private final String homep;
  private String group;

  public ContactData(String fistn, String middlen, String lastn, String nickn,
                     String mail, String homep, String group) {
    this.id = Integer.MAX_VALUE;
    this.fistn = fistn;
    this.middlen = middlen;
    this.lastn = lastn;
    this.nickn = nickn;
    this.mail = mail;
    this.homep = homep;
    this.group = group;
  }

  public ContactData(int id, String fistn, String middlen, String lastn, String nickn,
                     String mail, String homep, String group) {
    this.id = id;
    this.fistn = fistn;
    this.middlen = middlen;
    this.lastn = lastn;
    this.nickn = nickn;
    this.mail = mail;
    this.homep = homep;
    this.group = group;
  }

  public String getFistn() {
    return fistn;
  }

  public String getMiddlen() {
    return middlen;
  }

  public String getLastn() {
    return lastn;
  }

  public String getNickn() {
    return nickn;
  }

  public String getMail() {
    return mail;
  }

  public String getHomep() {
    return homep;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(fistn, that.fistn) && Objects.equals(lastn, that.lastn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fistn, lastn);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", fistn='" + fistn + '\'' +
            ", lastn='" + lastn + '\'' +
            '}';
  }
}
