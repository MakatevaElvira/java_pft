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




    @Test(enabled = false)
    public void testAdminCycleBad() {
        List<WebElement> treeElements = driver.findElements(By.xpath("//li[@id='app-']"));
        for (WebElement row: treeElements) { //WebElement row : treeElements
            WebElement tree = driver.findElement(By.xpath("//div[@id='box-apps-menu-wrapper']"));
            row = tree.findElement(By.xpath(".//li[@id='app-']"));
            row.isDisplayed();
            row.click();
            List<WebElement> blockElements = driver.findElements(By.cssSelector("ul.docs a"));
            for (WebElement blockElement : blockElements) {
                WebElement block = driver.findElement(By.cssSelector("ul.docs"));
                block.isDisplayed();
                blockElement = block.findElement(By.tagName("a"));
                blockElement.isDisplayed();
                blockElement.click();
                Assert.assertTrue(driver.findElement(By.xpath("//h1")).isDisplayed());
            }

        }
    }

    @Test()
    public void testAdminCycle() {
       List<WebElement> treeElements = driver.findElements(By.xpath("//li[@id='app-']"));
        int numberListElements = treeElements.size();
        for (int i = 0; i < numberListElements ; i++) { //WebElement row : treeElements
            WebElement tree = driver.findElement(By.xpath("//div[@id='box-apps-menu-wrapper']"));
            treeElements = tree.findElements(By.xpath(".//li[@id='app-']"));
           treeElements.get(i).isDisplayed();
            treeElements.get(i).click();
            List<WebElement> blockElements = driver.findElements(By.cssSelector("ul.docs a"));
            int numberListBlocks = blockElements.size();
            for (int k = 0; k < numberListBlocks ; k++) {
                WebElement block = driver.findElement(By.cssSelector("ul.docs"));
                block.isDisplayed();
                blockElements = block.findElements(By.tagName("a"));
                blockElements.get(k).isDisplayed();
                blockElements.get(k).click();
                Assert.assertTrue(driver.findElement(By.xpath("//h1")).isDisplayed());
            }

        }
    }
    @Test()
    public void testAdminCycleLittle() {
        List<WebElement> treeElements = driver.findElements(By.xpath("//div[@id='box-apps-menu-wrapper']//a"));
        int numberListElements = treeElements.size();
        for (int i = 0; i < numberListElements ; i++) { //WebElement row : treeElements
            WebElement tree = driver.findElement(By.xpath("//div[@id='box-apps-menu-wrapper']"));
            treeElements = tree.findElements(By.xpath(".//a"));
            treeElements.get(i).isDisplayed();
            treeElements.get(i).click();
            waitVisibilityOf(By.xpath("//h1"));


        }
    }

}



