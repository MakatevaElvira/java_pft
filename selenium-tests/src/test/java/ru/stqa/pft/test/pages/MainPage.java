package ru.stqa.pft.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MainPage extends Page {


    public MainPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public  MainPage open1() {
        driver.get("http://localhost:8080/litecart/en/");
        return this;
    }
    public  void open() {
        driver.get("http://localhost:8080/litecart/en/");
        ;
    }

    @FindBy(xpath = "//input[@placeholder='Search']")
    public static WebElement search;

    public void waitSearchPresent() {
        presenceOfElementLocated(By.xpath("//input[@placeholder='Search']"));
    }
    @FindBy(css = "li[class^='product']")
    public List<WebElement> listProducts;

    @FindBy(xpath = ".//span[@class='quantity']")
    public static WebElement basketSize;

    @FindBy(xpath = "//div[@id='cart']")
    public static WebElement basket;

    public static void openBasket(){
        basket.click();
    }
    public static Integer  beforeInBasket(){
        int before = Integer.parseInt(basketSize.getText());
        return before;
    }
    @FindBy(css = "i[title='Home']")
    public static WebElement HomeButton;

    public static void goHome(){
        HomeButton.click();
    }
}
