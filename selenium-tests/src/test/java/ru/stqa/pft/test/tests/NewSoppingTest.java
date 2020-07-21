package ru.stqa.pft.test.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.test.models.Product;
import ru.stqa.pft.test.pages.MainPage;

public class NewSoppingTest extends TestBasePgOb {
    Product product = new Product().withValue(3);

    @Test
    public void shoppingTest(){
        app.openMainPage();
        app.selectProducts(product);
        app.removeProductsFromBasket();

    }

}
