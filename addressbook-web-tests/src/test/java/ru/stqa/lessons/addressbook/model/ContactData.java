package ru.stqa.lessons.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("Contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @XStreamOmitField
  @Transient
  private int idGroup = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String fistn;
  @XStreamOmitField
  @Transient
  private String middlen;
  @Expose
  @Column(name = "lastname")
  private String lastn;
  @XStreamOmitField
  @Transient
  private String nickn;
  @Expose
  @Column(name = "home")
  private String homep;
  @Expose
  @Column(name = "mobile")
  private String mobilep;
  @Expose
  @Column(name = "work")
  private String workp;
  @XStreamOmitField
  @Transient
  private String allPhones;
  @Expose
  @Column(name = "address")
  private String address;
  @Expose
  @Column(name = "email")
  private String email;
  @Expose
  @Column(name = "email2")
  private String email2;
  @Expose
  @Column(name = "email3")
  private String email3;
  @XStreamOmitField
  @Transient
  private String allEMail;
  @XStreamOmitField
  @Column(name = "photo")
  private String photo;

  @XStreamOmitField
  @Transient
  private String secondaryPhone;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();
  public File getPhoto() { return new File(photo); }
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
  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public int getIdGroup() {
    return idGroup;
  }

  public ContactData withIdGroup(int idGroup) {
    this.idGroup = idGroup;
    return this;
  }

  public String getSecondaryPhone() {
    return secondaryPhone;
  }

  public ContactData withSecondaryPhone(String secondaryPhone) {
    this.secondaryPhone = secondaryPhone;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && idGroup == that.idGroup && Objects.equals(fistn, that.fistn) && Objects.equals(middlen, that.middlen) && Objects.equals(lastn, that.lastn) && Objects.equals(nickn, that.nickn) && Objects.equals(homep, that.homep) && Objects.equals(mobilep, that.mobilep) && Objects.equals(workp, that.workp) && Objects.equals(allPhones, that.allPhones) && Objects.equals(address, that.address) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3) && Objects.equals(allEMail, that.allEMail) && Objects.equals(photo, that.photo) && Objects.equals(secondaryPhone, that.secondaryPhone) && Objects.equals(groups, that.groups);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idGroup, fistn, middlen, lastn, nickn, homep, mobilep, workp, allPhones, address, email, email2, email3, allEMail, photo, secondaryPhone, groups);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", idGroup=" + idGroup +
            ", fistn='" + fistn + '\'' +
            ", middlen='" + middlen + '\'' +
            ", lastn='" + lastn + '\'' +
            ", nickn='" + nickn + '\'' +
            ", homep='" + homep + '\'' +
            ", mobilep='" + mobilep + '\'' +
            ", workp='" + workp + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", address='" + address + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", allEMail='" + allEMail + '\'' +
            ", photo='" + photo + '\'' +
            ", secondaryPhone='" + secondaryPhone + '\'' +
            ", groups=" + groups +
            '}';
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
