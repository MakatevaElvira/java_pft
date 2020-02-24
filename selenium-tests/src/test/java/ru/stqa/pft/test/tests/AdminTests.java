package ru.stqa.pft.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.html.HTMLElement;

import java.util.List;

public class AdminTests extends TestBaseLiteCart {

    @Test()
    public void testAdminAllMenu() {
        WebElement tree = driver.findElement(By.xpath("//div[@id='box-apps-menu-wrapper']"));
        WebElement rowAppearance = tree.findElement(By.xpath(".//a[@href='http://localhost:8080/litecart/admin/?app=appearance&doc=template']"));
        rowAppearance.click();
        WebElement rowAppearanceBlock = driver.findElement(By.cssSelector("ul.docs"));
        WebElement blockTemplate = rowAppearanceBlock.findElement(By.xpath(".//a[@href='http://localhost:8080/litecart/admin/?app=appearance&doc=template']"));
        blockTemplate.click();
        waitVisibilityOf(By.xpath("//h1"));
        WebElement blockLogotype = rowAppearanceBlock.findElement(By.xpath(".//a[@href='http://localhost:8080/litecart/admin/?app=appearance&doc=logotype']"));
        blockLogotype.click();
        waitVisibilityOf(By.xpath("//h1"));

    }

    @Test()
    public void testAdminCycle() {
        WebElement tree = driver.findElement(By.xpath("//div[@id='box-apps-menu-wrapper']"));
        tree.isDisplayed();
        List<WebElement> treeElements = tree.findElements(By.xpath(".//a"));
        for (WebElement row : treeElements) {
            String linkName = row.getAttribute("href");
            driver.findElement(By.xpath("//a[@href='" + linkName + "']")).isDisplayed();
            driver.findElement(By.xpath("//a[@href='" + linkName + "']")).click();
            WebElement block = driver.findElement(By.cssSelector("ul.docs"));
            block.isDisplayed();
            List<WebElement> blockElements = block.findElements(By.tagName("a"));
            for (WebElement blockElement : blockElements) {
                String elementName = blockElement.getAttribute("href");
                driver.findElement(By.xpath("//a[@href='" + elementName + "']")).isDisplayed();
                driver.findElement(By.xpath("//a[@href='" + elementName + "']")).click();
                waitVisibilityOf(By.xpath("//h1"));
            }

        }
    }

}



