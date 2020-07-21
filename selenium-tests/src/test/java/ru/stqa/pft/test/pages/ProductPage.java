package ru.stqa.pft.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductPage extends Page {

    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public boolean productSizeElementPresent(){
        isElementPresent(By.xpath("//select[@name='options[Size]']"));
        return false;
    }
    public void selectSize(){
        select(By.xpath("//select[@name='options[Size]']"),
                "Small", By.xpath("//select[@name='options[Size]']//option[@value='Small']"));
    }
    @FindBy(xpath = "//button[.='Add To Cart']" )
    public WebElement addToBasket;

    public void addToBasket(){
        addToBasket.click();
    }
}
