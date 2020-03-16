package ru.stqa.pft.test.pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.awt.*;

@FindBy(xpath = "//div[@id='cart-wrapper']")
public class Basket extends HtmlElement {

    @FindBy(xpath = ".//span[@class='quantity']")
    public  WebElement basketSize;

    @FindBy(xpath = "//div[@id='cart']")
    public static WebElement basket;

    public  void openBasket(){
        basket.click();
    }
    public  Integer  beforeInBasket(){
        int before = Integer.parseInt(basketSize.getText());
        return before;
    }




}
