package ru.stqa.lessons.addressbook.appmanager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupsHelper groupsHelper;
  private SessionHelper sessionHelper;
  private JavascriptExecutor js;
  WebDriver wd;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {

    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target","local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

    dbHelper = new DbHelper();

    if (browser.equals(BrowserType.CHROME)){
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    }
   wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    js = (JavascriptExecutor) wd;
    wd.get(properties.getProperty("web.baseUrl"));
    groupsHelper = new GroupsHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));
  }

  public void stop() {
    wd.quit();
  }

  public GroupsHelper group() {
    return groupsHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public DbHelper db(){return dbHelper;}
}
