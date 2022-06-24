package ru.stqa.lessons.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.lessons.addressbook.model.ContactData;
import ru.stqa.lessons.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase{
  public ContactHelper(WebDriver wd) {
    super(wd);
  }
  public void submitContactCreation() {
    click(By.xpath("//input[21]"));
  }
  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFistn());
    type(By.name("middlename"), contactData.getMiddlen());
    type(By.name("lastname"), contactData.getLastn());
    type(By.name("nickname"), contactData.getNickn());
    type(By.name("address"), contactData.getMail());
    type(By.name("home"), contactData.getHomep());

    if(creation) {
      if(contactData.getGroup() == null)
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("[none]");
          else new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
   public void createGroupForContact(ContactData contactData){
     wd.findElement(By.linkText("groups")).click();
     wd.findElement(By.name("new")).click();
     wd.findElement(By.name("group_name")).click();
     wd.findElement(By.name("group_name")).clear();
     wd.findElement(By.name("group_name")).sendKeys(contactData.getGroup());
     wd.findElement(By.name("submit")).click();
   }
  public void addnewContact() {
    click(By.linkText("add new"));
  }

  public void selecteContactById(int id) {
    wd.findElement(By.cssSelector("input[id = '"+id+"']")).click();}
  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void allert() {
    wd.switchTo().alert().accept();
  }
  public void editContactById(int id) { wd.get("http://localhost/addressbook/edit.php?id="+id); }
  public void updateContact() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }
  public void create(ContactData contact) {
    addnewContact();
    if (contact.getGroup() != null) {
      if (!isElementPresent(By.id(contact.getGroup()))) {
        createGroupForContact(contact);
        addnewContact();
      }
    }
      fillContactForm(contact, true);
      submitContactCreation();

  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillContactForm(contact, false);
    updateContact();
  }

  public void delete(ContactData contact) {
    selecteContactById(contact.getId());
    deleteSelectedContact();
    allert();
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }
  public int getContactCount() { return wd.findElements(By.name("selected[]")).size(); }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name ='entry']"));
    for(WebElement element: elements){
      String fistn = element.findElement(By.cssSelector("#maintable td:nth-child(3)")).getText();
      String lastn = element.findElement(By.cssSelector("#maintable td:nth-child(2)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFistn(fistn).withLastn(lastn));
    }
    return contacts;
  }
}
