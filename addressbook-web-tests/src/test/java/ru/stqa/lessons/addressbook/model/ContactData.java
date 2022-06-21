package ru.stqa.lessons.addressbook.model;

public class ContactData {
  private final String fistn;
  private final String middlen;
  private final String lastn;
  private final String nickn;
  private final String mail;
  private final String homep;
  private String group;

  public ContactData(String fistn, String middlen, String lastn, String nickn,
                     String mail, String homep, String group) {
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
}
