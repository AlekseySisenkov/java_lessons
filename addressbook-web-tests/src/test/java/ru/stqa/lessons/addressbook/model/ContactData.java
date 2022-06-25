package ru.stqa.lessons.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("Contact")
public class ContactData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  private String fistn;
  @XStreamOmitField
  private String middlen;
  @Expose
  private String lastn;
  @XStreamOmitField
  private String nickn;
  @Expose
  private String homep;
  @Expose
  private String mobilep;
  @Expose
  private String workp;
  @XStreamOmitField
  private String allPhones;
  @Expose
  private String address;
  @Expose
  private String email;
  @Expose
  private String email2;
  @Expose
  private String email3;
  @XStreamOmitField
  private String allEMail;
  @XStreamOmitField
  private String group;
  @XStreamOmitField
  private File photo;

  public File getPhoto() { return photo; }
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
  public String getHomep() {
    return homep;
  }

  public String getMobilep() {
    return mobilep;
  }

  public String getWorkp() {
    return workp;
  }

  public String getAllPhones() { return allPhones; }
  public String getGroup() {
    return group;
  }
  public int getId() {
    return id;
  }

  public String getAddress() { return address; }

  public String getEmail() { return email; }

  public String getEmail2() { return email2; }

  public String getEmail3() { return email3; }

  public String getAllEMail() { return allEMail; }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEMail(String allEMail) {
    this.allEMail = allEMail;
    return this;
  }
  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }
  public ContactData withFistn(String fistn) {
    this.fistn = fistn;
    return this;
  }
  public ContactData withMiddlen(String middlen) {
    this.middlen = middlen;
    return this;
  }
  public ContactData withLastn(String lastn) {
    this.lastn = lastn;
    return this;
  }
  public ContactData withNickn(String nickn) {
    this.nickn = nickn;
    return this;
  }
  public ContactData withHomep(String homep) {
    this.homep = homep;
    return this;
  }

  public ContactData withMobilep(String mobilep) {
    this.mobilep = mobilep;
    return this;
  }

  public ContactData withWorkp(String workp) {
    this.workp = workp;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }
  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(fistn, that.fistn) && Objects.equals(lastn, that.lastn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fistn, lastn);
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
