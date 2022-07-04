package ru.stqa.lessons.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
  private CloseableHttpClient httpclient;
  private ApplicationManager app;

  public HttpSession(ApplicationManager app){
    this.app = app;
    httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }

  public boolean login(String username, String password) throws IOException {
    HttpPost post1 = new HttpPost (app.getProperty("web.baseUrl") + "/login_page.php");
    List<NameValuePair> params1 = new ArrayList<>();
    params1.add(new BasicNameValuePair ("username",username));
   // params.add(new BasicNameValuePair ("password",password));
    params1.add(new BasicNameValuePair ("secure_session","on"));
    params1.add(new BasicNameValuePair ("return","index.php"));
    post1.setEntity(new UrlEncodedFormEntity(params1));
    CloseableHttpResponse response1 = httpclient.execute(post1);
    String body1 = geTextForm(response1);
    HttpPost post2 = new HttpPost (app.getProperty("web.baseUrl") + "/login_password_page.php");
    List<NameValuePair> params = new ArrayList<>();
    //params.add(new BasicNameValuePair ("username",username));
    params.add(new BasicNameValuePair ("password",password));
    params.add(new BasicNameValuePair ("secure_session","on"));
    params.add(new BasicNameValuePair ("return","index.php"));
    post2.setEntity(new UrlEncodedFormEntity(params));
    CloseableHttpResponse response2 = httpclient.execute(post2);
    String body2 = geTextForm(response2);

    return body1.contains(String.format("<span class=\"italic\">%s</span>", username));
  }

  private String geTextForm(CloseableHttpResponse response) throws IOException {
    try{
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }
  }

  public boolean isLoggedInAs(String username) throws IOException {
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/login.php");
    CloseableHttpResponse response = httpclient.execute(get);
    String body = geTextForm(response);
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));
  }
}
