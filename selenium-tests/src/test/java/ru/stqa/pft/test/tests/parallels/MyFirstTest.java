package ru.stqa.pft.test.tests.parallels;

import org.testng.annotations.Test;
import ru.stqa.pft.test.tests.TestBase;
import ru.stqa.pft.test.tests.TestBaseFF;

public class MyFirstTest extends TestBaseFF {


    @Test
    public void testMyFirstTest() {
        driver.navigate().to("https://www.google.ru/");
    }


    @Test(enabled = false)
    public void testMyFirstTest1() {
        driver.navigate().to("https://www.google.ru/");
    }

}
