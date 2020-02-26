package ru.stqa.pft.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MainPageTest extends TestBase {
    @Test
    public void testStickerDucks() {
        driver.navigate().to("http://localhost:8080/litecart/en/");

        List<WebElement> ducks = driver.findElements(By.cssSelector("li[class^='product']"));
        for (WebElement duck : ducks) {
            List<WebElement>  stickers =  duck.findElements(By.cssSelector("div[class^='sticker']"));
            Assert.assertEquals(stickers.size(),1);
        }
    }
}
