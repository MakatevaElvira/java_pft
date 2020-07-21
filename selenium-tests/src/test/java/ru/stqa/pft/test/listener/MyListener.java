package ru.stqa.pft.test.listener;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;


public  class MyListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        super.beforeFindBy(by, element, driver);
        System.out.println(by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        super.afterFindBy(by, element, driver);
        System.out.println(by + " found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        super.onException(throwable, driver);
        System.out.println(throwable);
        File tmp =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screen = new File("screen-" + System.currentTimeMillis()+ ".png");
        try {
            com.google.common.io.Files.copy(tmp,screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(screen);

    }
}
