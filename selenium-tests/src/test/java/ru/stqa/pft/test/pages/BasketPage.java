package ru.stqa.pft.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

public class BasketPage extends Page {
    public BasketPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[.='Customer Service']")
    public static WebElement title;

    public void titleIsDisplayed(){
        title.isDisplayed();
    }
    @FindBy(xpath = "//table[@class='dataTable rounded-corners']")
    public WebElement table;


    @FindBy(xpath = "//li[@class='item']")
    public static WebElement board;

    @FindBy(xpath = ".//button[.='Remove']")
    public List<WebElement> removeButton;


    @FindBy(xpath = "//li[@class='item']//a[@class='image-wrapper shadow']")
    public static List<WebElement> listOfProducts;

    public void preferenceOfProductsImages(){
        presenceOfAllElementsLocatedBy(By.xpath("//li[@class='item']//a[@class='image-wrapper shadow']"));
    }
}

