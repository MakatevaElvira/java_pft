package ru.stqa.pft.test.pages.elements;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.stqa.pft.test.models.Product;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

@Name("Корзина")
@FindBy(xpath = "//div[@id='cart-wrapper']")
public class Basket extends HtmlElement {
    @Name("Список Корзин")
    public List<Basket> baskets;

    @Name("Размер")
    @FindBy(xpath = ".//span[@class='quantity']")
    public  WebElement basketSize;

    @Name("Иконка")
    @FindBy(xpath = ".//div[@id='cart']")
    public  WebElement basketIcon;

    //("Найти корзину с параметрами")
    public Basket chooseTaskRowByContNumAndDate(Product product) {
        String date = new SimpleDateFormat("dd.MM.yyyy").format(product.getCurrency());
        return baskets.stream().filter(param -> param.getText().contains(product.getPrice()) &&
                param.getText().contains(date))
                .findAny().orElseThrow(() ->
                        new RuntimeException(String
                                .format("Корзины с ценой %s и датой %s не найдено", product
                                        .getPrice(), date)));
    }

    public  void openBasket(){
        basketIcon.click();
    }
    public  Integer  beforeInBasket(){
        int before = Integer.parseInt(basketSize.getText());
        return before;
    }




}
