package ru.stqa.pft.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.test.models.Colour;

import java.util.List;
import java.util.StringTokenizer;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainPageTest extends TestBase {
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
        getElementColour(mainPageOldPriceColour);
        assertTrue(getElementColour(mainPageOldPriceColour).getR() == getElementColour(mainPageOldPriceColour).getG() & getElementColour(mainPageOldPriceColour).getG() == getElementColour(mainPageOldPriceColour).getB());// серый цвет

        System.out.println("ElementColour= " + mainPageOldPriceColour);


        String mainPageOldPriceSize = duck.findElement(By.cssSelector("s")).getCssValue("font-size");
        mainPageOldPriceSize = mainPageOldPriceSize.substring(0, mainPageOldPriceSize.length() - 4);
        String mainPageNewPriceSize = duck.findElement(By.cssSelector("strong")).getCssValue("font-size");
        mainPageNewPriceSize = mainPageNewPriceSize.substring(0, mainPageNewPriceSize.length() - 2);
        assertTrue(Integer.parseInt(mainPageOldPriceSize) < Integer.parseInt(mainPageNewPriceSize)); //д)размер цены

        String mainPageNewPriceFont = duck.findElement(By.cssSelector("strong")).getCssValue("font-weight");
        System.out.println("NewPriceFont " + mainPageNewPriceFont);
        //assertEquals(mainPageNewPriceFont, "bold"); - проверка не работает, браузер Chrome отдает какое-то число
        String mainPageNewPriceColour = duck.findElement(By.cssSelector("strong")).getCssValue("color"); //RGBa представлении каналы G и B имеют нулевые значения
        getElementColour(mainPageNewPriceColour); //подходит для Хрома
        assertTrue(getElementColour(mainPageNewPriceColour).getG() == getElementColour(mainPageNewPriceColour).getB() & getElementColour(mainPageNewPriceColour).getB() == 0);//красный цвет

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
        //assertEquals(innerPageNewPriceFont, "bold"); - проверка не работает, браузер Chrome отдает какое-то число
        String innerPageOldPriceColour = duckInner.findElement(By.cssSelector("s")).getCssValue("color"); //RGBa представлении одинаковые значения для каналов R, G и B)
        getElementColour(innerPageOldPriceColour);
        assertTrue(getElementColour(innerPageOldPriceColour).getR() == getElementColour(innerPageOldPriceColour).getG() &
                getElementColour(innerPageOldPriceColour).getG() == getElementColour(innerPageOldPriceColour).getB());// серый цвет

        String innerPageNewPriceColour = duckInner.findElement(By.cssSelector("strong")).getCssValue("color"); //RGBa представлении каналы G и B имеют нулевые значения
        getElementColour(innerPageNewPriceColour);
        assertTrue(getElementColour(innerPageNewPriceColour).getG() == getElementColour(innerPageNewPriceColour).getB() &
                getElementColour(innerPageNewPriceColour).getB() == 0);//красный цвет

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
        assertEquals(mainPageNewPriceFont, innerPageNewPriceFont);//в)шрифт жирный

    }


    public Colour getElementColour(String elementsCssColor) {
        String mainPageOldPriceColour = elementsCssColor; //RGBa представлении одинаковые значения для каналов R, G и B)
        ; //RGBa представлении одинаковые значения для каналов R, G и B)
        String c = mainPageOldPriceColour.substring(5);
        mainPageOldPriceColour = c.replace(')', ' ');
        StringTokenizer st = new StringTokenizer(mainPageOldPriceColour);
        int r = Integer.parseInt(st.nextToken(",").trim());
        int g = Integer.parseInt(st.nextToken(",").trim());
        int b = Integer.parseInt(st.nextToken(",").trim());
        int a = Integer.parseInt(st.nextToken(",").trim());
        return new Colour().withR(r).withG(g).withB(b).withA(a);
    }


    @Test
    public void testCompareDucksOnPage2() {
        driver.navigate().to("http://localhost:8080/litecart/en/");
        //главная
        WebElement duck = driver.findElement((By.cssSelector("div [id=box-campaigns] li[class^='product']")));

        String mainPageNewPriceFont = duck.findElement(By.cssSelector("strong")).getCssValue("font-weight");
        System.out.println("Font-size new: " + mainPageNewPriceFont);
        assertEquals(mainPageNewPriceFont, "bold");

    }

    @Test
    public void testShopping() {
        driver.navigate().to("http://localhost:8080/litecart/en/");
        //а главной
        waitIsElementPresent(By.xpath("//input[@placeholder='Search']"));
        for (int i = 0; i < 3; i++) {
            List<WebElement> myProduct = driver.findElements(By.cssSelector("li[class^='product']"));
            myProduct.get(i).click();
            //на стр продукта
            WebElement basketBefore = driver.findElement(By.xpath("//span[@class='quantity']"));
            int before = Integer.parseInt(basketBefore.getText());
            //проверить нужно ли выбрать размер:
            if (isElementPresent(By.xpath("//select[@name='options[Size]']"))) {
                select(By.xpath("//select[@name='options[Size]']"),
                        "Small", By.xpath("//select[@name='options[Size]']//option[@value='Small']")); ////select[@name='options[Size]']//option[@value='Small']
            }
            //добавить в корзину
            driver.findElement(By.xpath("//button[.='Add To Cart']")).click();
            //подождать что корзина обновиться:
            waitTextOfElementPresent((By.xpath("//span[@class='quantity' ]")), String.valueOf(before + 1));
            //вернуться на главную
            driver.findElement(By.cssSelector("i[title='Home']")).click();
            waitInvisibility(By.xpath("//button[.='Add To Cart']"));

        }        //открыть корзину
        driver.findElement(By.xpath("//div[@id='cart']")).click();
        waitInvisibility(By.xpath("//input[@placeholder='Search']"));
        //заголовок в корзине
        driver.findElement(By.xpath("//span[.='Customer Service']"));
        //проверить есть ли элементы:
        //if (isElementPresent(By.xpath("//button[.='Remove']"))) {
        //найти запись в таблице
       // waitElementPresent(By.xpath("//li[@class='item']//a[@class='image-wrapper shadow']"));
        WebElement table = driver.findElement(By.xpath("//table[@class='dataTable rounded-corners']"));
        presenceOfAllElementsLocatedBy(By.xpath("//li[@class='item']//a[@class='image-wrapper shadow']"));
        List<WebElement> products = driver
                .findElements(By.xpath("//li[@class='item']//a[@class='image-wrapper shadow']"));
        int numberList = products.size();
        for (int i = 0; i < numberList; i++) {
            WebElement bord = driver.findElement(By.xpath("//li[@class='item']"));
            products = bord.findElements(By.xpath(".//button[.='Remove']"));
            products.get(i).click();
            waitStalenessOf(table);
        }
    }
}

