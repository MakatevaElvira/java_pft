package ru.stqa.pft.test.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.test.models.Product;
import ru.stqa.pft.test.pages.BasketPage;
import ru.stqa.pft.test.pages.MainPage;
import ru.stqa.pft.test.pages.ProductPage;
import ru.stqa.pft.test.pages.elements.Basket;
import ru.stqa.pft.test.pages.elements.HomeButton;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static ru.stqa.pft.test.pages.BasketPage.board;
import static ru.stqa.pft.test.pages.MainPage.*;


public class Application {

    private WebDriver driver;
    private MainPage mainPage;
    private ProductPage productPage;
    private BasketPage basketPage;
    private Basket basket;

    public Application() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
        basket = new Basket();
    }

    public void quit() {
        driver.quit();
    }

    public void openMainPage(){
        mainPage.open();
    }

    public void selectProducts(Product product){
        mainPage.waitSearchPresent();
        for (int i = 0; i < product.getValue(); i++) {
            List<WebElement> myProduct = mainPage.listProducts;
            myProduct.get(i).click();
            //на стр продукта
            //WebElement basketBefore = basketSize;
            int before = mainPage.beforeInBasket();
            //проверить нужно ли выбрать размер:
            if (productPage.productSizeElementPresent()) {
                productPage.selectSize();
            }
            //добавить в корзину
            productPage.addToBasket();
            //подождать что корзина обновиться:
            textToBePresentInElement(basketSize, String.valueOf(before + 1));
            //вернуться на главную
            goHome();
            invisibilityOf(productPage.addToBasket);

        }
    }
    public void removeProductsFromBasket(){
        openBasket();
        invisibilityOf(search);
        //заголовок в корзине
        basketPage.titleIsDisplayed();
        //найти запись в таблице
        WebElement table = basketPage.table;
        basketPage.preferenceOfProductsImages();
        List<WebElement> products = basketPage.listOfProducts;
        int numberList = products.size();
        for (int i = 0; i < numberList; i++) {
            products = basketPage.removeButton;
            products.get(i).click();
            stalenessOf(table);
        }
    }



}
