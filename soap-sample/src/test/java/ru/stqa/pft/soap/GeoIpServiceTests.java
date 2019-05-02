package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  private Object GeoIPService;

  @Test
  public void testMyIp(){
    GeoIPService  = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("95.84.56.95");
    assertEquals(GeoIPService,"<GeoIP><Country>RU</Country><State>67</State></GeoIP>");
  }
  @Test
  public void testInvalidIp(){
    GeoIPService  = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("95.84.56.xx");
    assertEquals(GeoIPService,"<GeoIP><Country>RU</Country><State>67</State></GeoIP>");
  }
}
