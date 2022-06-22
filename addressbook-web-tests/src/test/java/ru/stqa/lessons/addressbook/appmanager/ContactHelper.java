package ru.stqa.lessons.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.lessons.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  //public String a;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFistn());
    type(By.name("middlename"), contactData.getMiddlen());
    type(By.name("lastname"), contactData.getLastn());
    type(By.name("nickname"), contactData.getNickn());
    type(By.name("address"), contactData.getMail());
    type(By.name("home"), contactData.getHomep());

    if(creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void addnewContact() {
    click(By.linkText("add new"));
  }

  public void selecteContact() {
    click(By.name("selected[]"));
  }
 /*public void selecteContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }*/

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void allert() {
    wd.switchTo().alert().accept();
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void updateContact() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contact, boolean creation) {
    addnewContact();
    fillContactForm(contact, creation);
    submitContactCreation();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
