package ru.stqa.pft.test.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;

public class RestTests  {

  @Test
  public void testDelay(){
    getDelay();
  }
  @Test
  public void testImage() throws IOException {
    getImage();

  }
  public String deleteDelay(){
    String delay = RestAssured.given().parameter("delay",2)
            .delete("https://httpbin.org/#/Dynamic_data/delete_delay__delay_").asString();
    System.out.println(delay);
    return delay;
  }
  public String getDelay(){
    String delay = RestAssured.given().parameter("delay",2)
            .get("https://httpbin.org/#/Dynamic_data/delete_delay__delay_").asString();
    System.out.println(delay);
    return delay;
  }
  private String getImage() throws IOException {
    return  RestAssured.get("https://httpbin.org/#/Images/get_image_png").asString();
  }
}
