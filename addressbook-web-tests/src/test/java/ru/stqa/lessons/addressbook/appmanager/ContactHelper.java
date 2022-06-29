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
    type(By.name("lastname"), contactData.getLastn());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomep());
    type(By.name("mobile"), contactData.getMobilep());
    type(By.name("work"), contactData.getWorkp());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());


    if(creation) {
      attach(By.name("photo"),contactData.getPhoto());
      if(contactData.getGroups().size() > 0){
    // if(contactData.getGroups() == null)
     //   new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("[none]");
      //    else{
        Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
     }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
 /*  public void createGroupForContact(ContactData contactData){
     wd.findElement(By.linkText("groups")).click();
     wd.findElement(By.name("new")).click();
     wd.findElement(By.name("group_name")).click();
     wd.findElement(By.name("group_name")).clear();
     wd.findElement(By.name("group_name")).sendKeys(contactData.getGroups().iterator().next().getName());
     wd.findElement(By.name("submit")).click();
   }*/


  public void addnewContact() {
    click(By.linkText("add new"));
  }

  public void returntoHomePage() {
    click(By.linkText("home"));
  }
  public void selecteContactById(int id) {
    wd.findElement(By.cssSelector("input[id = '"+id+"']")).click();}
  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void allert() {
    wd.switchTo().alert().accept();
  }
  public void editContactById(int id) {
    //wd.get("http://localhost/addressbook/edit.php?id="+id);
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s'",id))).click();
  }

 /* public void goToGroupPageForContact(int id) {
      click(By.linkText("group page"));
  }*/
  public void updateContact() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }
  public void  create(ContactData contact) {
    addnewContact();
      fillContactForm(contact, true);
      submitContactCreation();
      contactCache = null;
      returntoHomePage();
  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillContactForm(contact, false);
    updateContact();
    contactCache = null;
    returntoHomePage();
  }

  public void delete(ContactData contact) {
    selecteContactById(contact.getId());
    deleteSelectedContact();
    allert();
    contactCache = null;
    returntoHomePage();
  }


  public void removeContactFromGroup(ContactData contact) {

      new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(contact.getIdGroup()));
    selecteContactById(contact.getId());
    wd.findElement(By.name("remove")).click();
  }
  public void addInGroup(ContactData contact) {
      selecteContactById(contact.getId());
      new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(contact.getIdGroup()));
    wd.findElement(By.name("add")).click();
  }
  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }
  public int count() { return wd.findElements(By.name("selected[]")).size(); }

  private Contacts contactCache = null;
  public Contacts all() {
    if(contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name ='entry']"));
    for(WebElement element: elements){
      String fistn = element.findElement(By.cssSelector("#maintable td:nth-child(3)")).getText();
      String lastn = element.findElement(By.cssSelector("#maintable td:nth-child(2)")).getText();
      String allPhones = element.findElement(By.cssSelector("#maintable td:nth-child(6)")).getText();
      String address = element.findElement(By.cssSelector("#maintable td:nth-child(4)")).getText();
      String allEMail = element.findElement(By.cssSelector("#maintable td:nth-child(5)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contactCache.add(new ContactData().withId(id).withFistn(fistn).withLastn(lastn)
              .withAllPhones(allPhones).withAddress(address).withAllEMail(allEMail));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditFom(ContactData contact) {
    editContactById(contact.getId());
    String fistn = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastn = wd.findElement(By.name("lastname")).getAttribute("value");
    String homep = wd.findElement(By.name("home")).getAttribute("value");
    String mobilep = wd.findElement(By.name("mobile")).getAttribute("value");
    String workp = wd.findElement(By.name("work")).getAttribute("value");
    String secondaryPhone = wd.findElement(By.name("phone2")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withFistn(fistn).withLastn(lastn).withHomep(homep).withMobilep(mobilep).withWorkp(workp).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3).withSecondaryPhone(secondaryPhone);
  }
}
