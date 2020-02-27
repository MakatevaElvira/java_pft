package ru.stqa.pft.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class CountriesPageTest extends TestBaseLiteCart {


    @Test
    public void testCountriesAndZonesSort() {
        driver.navigate().to("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        assertListIsSorted(By.cssSelector("tr.row a:not([title^='Edit']"));
        List<WebElement> rowsElements = driver.findElements(By.xpath("//td[.>0 and position()=6]/.."));
        int numberListElements = rowsElements.size();
        for (int i = 0; i < numberListElements; i++) { //WebElement row : treeElements
            WebElement row = driver.findElement(By.xpath("//td[.>0 and position()=6]/.."));
            WebElement country = row.findElement(By.cssSelector("a:not([title^='Edit']"));
            country.click();
            driver.findElement(By.xpath("//h2"));
            assertListIsSorted(By.xpath("//tr/td[3]/input[@type='hidden']"));
            driver.findElement(By.xpath("//button[@name='cancel']")).click();
        }
    }

}
