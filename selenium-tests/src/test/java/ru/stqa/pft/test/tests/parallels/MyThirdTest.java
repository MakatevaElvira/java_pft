package ru.stqa.pft.test.tests.parallels;

import org.testng.annotations.Test;
import ru.stqa.pft.test.tests.TestBase;

public class MyThirdTest extends TestBase {


@Test
    public void testMyThirdTest(){
    driver.navigate().to("https://www.google.ru/");
}

}
