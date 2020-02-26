package ru.stqa.pft.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.test.tests.TestBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class CountriesPageTest extends TestBaseLiteCart {
    @Test
    public void testCountriesSort() {
        driver.navigate().to("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        ArrayList <String> сountriesList = new ArrayList<>();
        List<WebElement> сountries = driver.findElements(By.cssSelector("tr.row a:not([title^='Edit']"));
        for (WebElement сountry : сountries) {
            сountriesList.add(сountry.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for(String s:сountriesList){
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        Assert.assertTrue(sortedList.equals(сountriesList));

        List<WebElement> rows = driver.findElements(By.cssSelector("tr.row"));
        for (WebElement row : rows) {
            WebElement zon = row.findElement(By.cssSelector("td:nth-child(6)"));
            WebElement country = row.findElement(By.cssSelector("a:not([title^='Edit']"));
            int zoneInt = Integer.parseInt(zon.getAttribute("innerText"));
            if ( zoneInt > 0) {
                country.click();
                driver.findElement(By.xpath("//h2"));
            }
        }
//часть б собрать список зон
       // ArrayList <Integer> intList =new ArrayList<>();
        //List<WebElement> zonsList = new List<WebElement>();
      // // List<WebElement> сountriesZons = driver.findElements(By.cssSelector("tr.row td:nth-child(6)"));
      //  for (WebElement zone : сountriesZons) {
          //  int zoneInt = Integer.parseInt(zone.getText());
           //  if ( zoneInt > 0) {
             //    zone.click();            / }
        }//innerText
//для всех строк


//zone.getAttribute("innerHTML")
       // List<WebElement> stuff = somePage.getStuffList();
       // assertThat(stuff).isSortedAccordingTo((e1, e2) -> e1.getText().compareTo(e2.getText()));
        //a[href^='http://localhost:8080/litecart/admin/?app=countries&doc=edit_country&country_code']:not([title^='Edit'])
        //a:not([title^='Edit'])

}
