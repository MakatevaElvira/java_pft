package ru.stqa.pft.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;
import ru.stqa.pft.test.models.Colour;
import ru.stqa.pft.test.tests.TestBaseFF;

import java.util.List;
import java.util.StringTokenizer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainPageTestFF extends TestBaseFF {
    @Test
    public void testStickerDucks() {
        driver.navigate().to("http://localhost:8080/litecart/en/");

        List<WebElement> ducks = driver.findElements(By.cssSelector("li[class^='product']"));
        for (WebElement duck : ducks) {
            List<WebElement> stickers = duck.findElements(By.cssSelector("div[class^='sticker']"));
            assertEquals(stickers.size(), 1);
        }
    }

    @Test
    public void testCompareDucksOnPage() {
        driver.navigate().to("http://localhost:8080/litecart/en/");
        //главная
        WebElement duck = driver.findElement((By.cssSelector("div [id=box-campaigns] li[class^='product']")));
        String mainPageText = duck.findElement(By.cssSelector("div.name")).getText();
        String mainPageOldPrice = duck.findElement(By.cssSelector("s")).getText();
        String mainPageNewPrice = duck.findElement(By.cssSelector("strong")).getText();

        //цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать
        String mainPageOldPriceFont = duck.findElement(By.cssSelector("s")).getCssValue("text-decoration-line");
        assertEquals(mainPageOldPriceFont, "line-through");//шрифт зачеркнутый
        String mainPageOldPriceColour = duck.findElement(By.cssSelector("s")).getCssValue("color"); //RGBa представлении одинаковые значения для каналов R, G и B)
        getElementColourFF(mainPageOldPriceColour);
        System.out.println("ElementColour= " + mainPageOldPriceColour);
        assertTrue(getElementColourFF(mainPageOldPriceColour).getR() == getElementColourFF(mainPageOldPriceColour).getG() &
                getElementColourFF(mainPageOldPriceColour).getG() == getElementColourFF(mainPageOldPriceColour).getB());// серый цвет

        String mainPageOldPriceSize = duck.findElement(By.cssSelector("s")).getCssValue("font-size");
        mainPageOldPriceSize = mainPageOldPriceSize.substring(0, mainPageOldPriceSize.length() - 4);
        String mainPageNewPriceSize = duck.findElement(By.cssSelector("strong")).getCssValue("font-size");
        mainPageNewPriceSize = mainPageNewPriceSize.substring(0, mainPageNewPriceSize.length() - 2);
        assertTrue(Integer.parseInt(mainPageOldPriceSize) < Integer.parseInt(mainPageNewPriceSize)); //д)размер цены

        String mainPageNewPriceFont = duck.findElement(By.cssSelector("strong")).getCssValue("font-weight");
        System.out.println("NewPriceFont " + mainPageNewPriceFont);
        //assertEquals(mainPageNewPriceFont, "bold"); //- проверка не работает, браузер Chrome\Лисе отдает какое-то число
        String mainPageNewPriceColour = duck.findElement(By.cssSelector("strong")).getCssValue("color"); //RGBa представлении каналы G и B имеют нулевые значения
        getElementColourFF(mainPageNewPriceColour); //подходит для Лисы
        assertTrue(getElementColourFF(mainPageNewPriceColour).getG() == getElementColourFF(mainPageNewPriceColour).getB() & getElementColourFF(mainPageNewPriceColour).getB() == 0);//красный цвет

        duck.click();
        //внутренняя
        WebElement duckInner = driver.findElement((By.cssSelector("div [id=box-product]")));
        String innerPageText = duckInner.findElement(By.cssSelector("h1")).getText();
        String innerPageOldPrice = duckInner.findElement(By.cssSelector("s")).getText();
        String innerPageNewPrice = duckInner.findElement(By.cssSelector("strong")).getText();
        String innerPageOldPriceFont = duckInner.findElement(By.cssSelector("s")).getCssValue("text-decoration-line");
        assertEquals(innerPageOldPriceFont, "line-through");//шрифт зачеркнутый
        String innerPageNewPriceFont = duckInner.findElement(By.cssSelector("strong")).getCssValue("font-weight");
        System.out.println("NewPriceFont " + mainPageNewPriceFont);
       // assertEquals(innerPageNewPriceFont, "bold"); //- проверка не работает, браузер Chrome/Лиса отдает какое-то число
        String innerPageOldPriceColour = duckInner.findElement(By.cssSelector("s")).getCssValue("color"); //RGBa представлении одинаковые значения для каналов R, G и B)
        getElementColourFF(innerPageOldPriceColour);
        assertTrue(getElementColourFF(innerPageOldPriceColour).getR() == getElementColourFF(innerPageOldPriceColour).getG() &
                getElementColourFF(innerPageOldPriceColour).getG() == getElementColourFF(innerPageOldPriceColour).getB());// серый цвет

        String innerPageNewPriceColour = duckInner.findElement(By.cssSelector("strong")).getCssValue("color"); //RGBa представлении каналы G и B имеют нулевые значения
        getElementColourFF(innerPageNewPriceColour);
        assertTrue(getElementColourFF(innerPageNewPriceColour).getG() == getElementColourFF(innerPageNewPriceColour).getB() &
                getElementColourFF(innerPageNewPriceColour).getB() == 0);//красный цвет

        String innerPageOldPriceSize = duckInner.findElement(By.cssSelector("s")).getCssValue("font-size");
        System.out.println("PriceSize= " + innerPageOldPriceSize);
        innerPageOldPriceSize = innerPageOldPriceSize.substring(0, innerPageOldPriceSize.length() - 2);
        String innerPageNewPriceSize = duckInner.findElement(By.cssSelector("strong")).getCssValue("font-size");
        System.out.println("PriceSizeNew= " + innerPageNewPriceSize);
        ;
        innerPageNewPriceSize = innerPageNewPriceSize.substring(0, innerPageNewPriceSize.length() - 2);
        assertTrue(Integer.parseInt(innerPageOldPriceSize) < Integer.parseInt(innerPageNewPriceSize)); //д)размер цены

// ассерты
        assertEquals(mainPageText, innerPageText);//а)текст названия
        assertEquals(mainPageOldPrice, innerPageOldPrice);//б)текст обычн цена
        assertEquals(mainPageNewPrice, innerPageNewPrice);//б)текст акционная цена
        assertEquals(mainPageOldPriceFont, innerPageOldPriceFont);//в)шрифт зачеркнутый
        assertEquals(mainPageNewPriceFont, innerPageNewPriceFont);//в)шрифт жирный в Лисе нашел несоответствие! проверка выдает ошибку

    }
    public Colour getElementColourFF(String elementsCssColor) {
        String mainPageOldPriceColour = elementsCssColor; //RGBa представлении одинаковые значения для каналов R, G и B)
        ; //RGBa представлении одинаковые значения для каналов R, G и B)
        String c = mainPageOldPriceColour.substring(4);
        mainPageOldPriceColour = c.replace(')', ' ');
        StringTokenizer st = new StringTokenizer(mainPageOldPriceColour);
        int r = Integer.parseInt(st.nextToken(",").trim());
        int g = Integer.parseInt(st.nextToken(",").trim());
        int b = Integer.parseInt(st.nextToken(",").trim());
        return new Colour().withR(r).withG(g).withB(b);
    }
}
