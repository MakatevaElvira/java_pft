package ru.stqa.pft.test.tests.parallels;

import org.testng.annotations.Test;
import ru.stqa.pft.test.tests.TestBase;

public class MySecondTest extends TestBase {


@Test
    public void testMySecondTest(){
    driver.navigate().to("https://www.google.ru/");
}

}
