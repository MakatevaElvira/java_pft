package ru.stqa.pft.test.tests.parallels;

import org.testng.annotations.Test;
import ru.stqa.pft.test.tests.TestBase;
import ru.stqa.pft.test.tests.TestBaseFF;

public class MySecondTest extends TestBaseFF {


@Test
    public void testMySecondTest(){
    driver.navigate().to("https://www.google.ru/");
}

}
