package ru.stqa.pft.rest.appManager;
import java.util.Properties;

public class ApplicationManager {

  private final Properties properties;
  private RestHelper rest;


  public ApplicationManager() {
    properties = new Properties();
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RestHelper rest(){
    if (rest == null) {
      rest = new RestHelper(this);
    }
    return rest;
  }
}
