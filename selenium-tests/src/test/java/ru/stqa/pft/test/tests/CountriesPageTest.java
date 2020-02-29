package ru.stqa.pft.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
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

}
