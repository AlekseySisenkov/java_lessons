package ru.stqa.lessons.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.lessons.addressbook.model.*;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
  private ContactsInGroupData contactInGroup;

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


    if (creation) {
      attach(By.name("photo"), contactData.getPhoto());
      if (contactData.getGroups().size() > 0) {
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
    wd.findElement(By.cssSelector("input[id = '" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void allert() {
    wd.switchTo().alert().accept();
  }

  public void editContactById(int id) {
    //wd.get("http://localhost/addressbook/edit.php?id="+id);
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s'", id))).click();
  }

  /* public void goToGroupPageForContact(int id) {
       click(By.linkText("group page"));
   }*/
  public void updateContact() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void create(ContactData contact) {
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

  public ContactsInGroupData selectContactForAddInGroup(Groups groups, Contacts contacts, ContactsInGroup before) {
    ArrayList<GroupData> listOfGroups = new ArrayList<>(groups);
    int elementCountOfGroups = listOfGroups.size();
    ArrayList<ContactData> listOfContacts = new ArrayList<>(contacts);
    int elementCountOfContacts = listOfContacts.size();
    ArrayList<ContactsInGroupData> listOfBefore = new ArrayList<>(before);
    int elementCountOfBefore = listOfBefore.size();

    if (elementCountOfBefore != 0) {
      ContactsInGroupData contactsInGroup;
      ContactData addedContact = null;
      GroupData addedInGroup = null;
      boolean createGroup = false;
      boolean conInGroup = false;
      boolean groupForContact = false;

      for (int i = 0; i < elementCountOfContacts; i++) {
        addedContact = listOfContacts.get(i);
        conInGroup = false;

        for (int k = 0; k < elementCountOfBefore; k++) {
          contactsInGroup = listOfBefore.get(k);
          if (addedContact.getId() == contactsInGroup.getContact()) {
            conInGroup = true;
            break;
          }
        }
        if (!conInGroup) break;
      }

      if (conInGroup) {
        for (int l = 0; l < elementCountOfGroups; l++) {
          addedInGroup = listOfGroups.get(l);
          groupForContact = false;

          for (int j = 0; j < elementCountOfBefore; j++) {
            contactsInGroup = listOfBefore.get(j);
            if (addedInGroup.getId() == contactsInGroup.getGroup()) {
              groupForContact = true;
              break;
            }
          }
          if (!groupForContact) break;
        }

        if (groupForContact) {
          outer:
          for (int m = 0; m < elementCountOfContacts; m++) {
            addedContact = listOfContacts.get(m);
            for (int n = 0; n < elementCountOfGroups; n++) {
              addedInGroup = listOfGroups.get(n);
              createGroup = false;
              for (int p = 0; p < elementCountOfBefore; p++) {
                contactsInGroup = listOfBefore.get(p);
                if ((addedContact.getId() == contactsInGroup.getContact()) && (addedInGroup.getId() == contactsInGroup.getGroup())) {
                  createGroup = true;
                  break;
                }
              }
              if (!createGroup) break outer;
            }
          }

          if (createGroup) {
            addedInGroup = new GroupData().withName("Group" + elementCountOfGroups);
            createGroupForContact(addedInGroup);
            return new ContactsInGroupData().withContact(contacts.iterator().next().getId())
                    .withGroup(Integer.parseInt(wd.findElement(By.xpath("//input[@title='Select (" + addedInGroup.getName() + ")']"))
                            .getAttribute("value")));
          } else
            return new ContactsInGroupData().withContact(addedContact.getId()).withGroup(addedInGroup.getId());
        } else
          return new ContactsInGroupData().withContact(contacts.iterator().next().getId()).withGroup(addedInGroup.getId());
      } else
        return new ContactsInGroupData().withContact(addedContact.getId()).withGroup(groups.iterator().next().getId());
    } else
      return new ContactsInGroupData().withContact(contacts.iterator().next().getId()).withGroup(groups.iterator().next().getId());
  }

  public void createGroupForContact(GroupData group) {
    click(By.linkText("groups"));
    click(By.name("new"));
    type(By.name("group_name"), group.getName());
    click(By.name("submit"));
    click(By.linkText("groups"));
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

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name ='entry']"));
    for (WebElement element : elements) {
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
