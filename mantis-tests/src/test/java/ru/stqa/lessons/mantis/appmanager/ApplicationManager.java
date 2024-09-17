package ru.stqa.lessons.mantis.appmanager;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
  private String string;
  private Properties properties;
  private WebDriver wd;

  private SessionHelper sessionHelper;
  private HttpSessionHelper httpSessionHelper;

  public void init(String browser, Properties properties) {
    this.string = browser;
    this.properties = properties;
  }

  public WebDriver driver(){
    if (wd == null){
      if ("chrome".equals(string)){
        wd = new ChromeDriver();
      } else if ("firefox".equals(string)) {
        wd = new FirefoxDriver();
      } else {
        throw new IllegalArgumentException(String.format("Unknown browser %s", string));
      }
      Runtime.getRuntime().addShutdownHook(new Thread(wd::quit));
      wd.get(properties.getProperty("web.baseUrl"));
      wd.manage().window().setSize(new Dimension(1076, 640));
    }
    return wd;
  }

  public SessionHelper session() {
    if (sessionHelper == null) {
      sessionHelper = new SessionHelper(this);
    }
    return sessionHelper;
  }

  public HttpSessionHelper http() {
    if (httpSessionHelper == null) {
      httpSessionHelper = new HttpSessionHelper(this);
    }
    return httpSessionHelper;
  }

  public String property(String name) {
    return properties.getProperty(name);
  }
}
