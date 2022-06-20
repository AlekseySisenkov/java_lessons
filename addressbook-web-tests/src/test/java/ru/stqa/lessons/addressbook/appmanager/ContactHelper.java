package ru.stqa.lessons.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.lessons.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public String a;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFistn());
    type(By.name("middlename"), contactData.getMiddlen());
    type(By.name("lastname"), contactData.getLastn());
    type(By.name("nickname"), contactData.getNickn());
    type(By.name("address"), contactData.getMail());
    type(By.name("home"), contactData.getHomep());
  }

  public void addnewContact() {
    click(By.linkText("add new"));
  }

  public void selecteContact(int a) {
    click(By.id(String.valueOf(a)));

  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void allert() {
    wd.switchTo().alert().accept();
  }

  public void editContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void updateContact() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }
}
