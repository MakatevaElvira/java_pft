package ru.stqa.pft.test.tests;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;

public class CountriesPageTest extends TestBaseLiteCart {


    @Test
    public void testCountriesAndZonesSort()  {
        driver.navigate().to("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        assertListIsSortedByText(By.cssSelector("tr.row a:not([title^='Edit']"));
        List<WebElement> rows = driver.findElements(By.xpath("//td[.>0 and position()=6]/.."));
        int numberListElements = rows.size();
        for (int i = 0; i < numberListElements; i++) { //WebElement row : treeElements
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//td[.>0 and position()=6]/..//a[not(@title='Edit')]")));
             rows = driver.findElements(By.xpath("//td[.>0 and position()=6]/..//a[not(@title='Edit')]"));
            rows.get(i).isDisplayed();
             rows.get(i).click();
            driver.findElement(By.xpath("//h2"));
            assertListIsSortedByValue(By.xpath("//tr/td[3]/input[@type='hidden']"));
            driver.findElement(By.xpath("//span/button[@name='cancel']")).click();

        }
    }
    @Test
    public void testZonesCountrySort()  {
        driver.navigate().to("http://localhost:8080/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='row']//a[not(@title='Edit')]"));
        int numberListElements = rows.size();
        for (int i = 0; i < numberListElements; i++) {
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//tr[@class='row']//a[not(@title='Edit')]")));
            rows = driver.findElements(By.xpath("//tr[@class='row']//a[not(@title='Edit')]"));
            rows.get(i).isDisplayed();
            rows.get(i).click();
            driver.findElement(By.xpath("//h2"));
            assertListIsSortedByText(By.cssSelector("select[name*=zone_code] option[selected=selected]"));
            driver.findElement(By.xpath("//span/button[@name='cancel']")).click();

        }
    }
    @Test
    public void testSweetToWindow(){
        driver.navigate().to("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.xpath("//tr[@class='row']//a[not(@title='Edit')]")).click();
        List<WebElement> links = driver.findElements(By.xpath("//i[@class='fa fa-external-link']"));
        int numberLinks = links.size();
        for (int i=0; i< numberLinks; i++ ){
            String mainWindow = driver.getWindowHandle();
            Set<String> existingWindows = driver.getWindowHandles();
            links.get(i).click();
            String newWindow = wait.until(anyWindowOtherThan(existingWindows));
            //переключаемся
            driver.switchTo().window(newWindow);
            driver.close();
            //возвращаемся
            driver.switchTo().window(mainWindow);
        }

    }


}
