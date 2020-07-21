package ru.stqa.pft.test.pages.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//div[@id='cart-wrapper']")
public class HomeButton extends HtmlElement {


    @FindBy(xpath = "//span[@class='quantity']")
    public  WebElement HomeButton;


    public  void goHome(){
        HomeButton.click();
    }


}
