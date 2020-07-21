package ru.stqa.pft.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ru.stqa.pft.test.models.UserAccount;
import ru.stqa.pft.test.tests.TestBase;

public class RegistrationTest extends TestBase {
    @Test
    public void testRegistration() {
        UserAccount acc = new UserAccount().withName("Name")
                .withLastName("LastName").withAddress1("Address 1")
                .withPostcode("41003").withCity("Los-Angeles")
                .withCountry("United States").withState("California").withEmail("email001@mail.ru")
                .withPhone("9061001010").withPassword("password");
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost", now);

        driver.get("http://localhost:8080/litecart/en/");
        driver.findElement(By.xpath("//a[.='New customers click here']")).isDisplayed();
        driver.findElement(By.xpath("//a[.='New customers click here']")).click();
        waitInvisibility(By.xpath("//a[.='New customers click here']"));
        driver.findElement(By.xpath("//input[@name='firstname']")).isDisplayed();
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(acc.getName());
        driver.findElement(By.xpath("//input[@name='lastname']")).isDisplayed();
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(acc.getLastName());
        driver.findElement(By.xpath("//input[@name='address1']")).isDisplayed();
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(acc.getAddress1());
        driver.findElement(By.xpath("//input[@name='postcode']")).isDisplayed();
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys(acc.getPostcode());
        driver.findElement(By.xpath("//input[@name='city']")).isDisplayed();
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(acc.getCity());//span[@class='selection']/span
        driver.findElement(By.xpath("//span[@class='selection']/span")).click();
        scrollToClick(By.xpath("//li[.='" + acc.getCountry() + "']"));
        driver.findElement(By.xpath("//select[@name='zone_code']")).click();
        scrollToClick(By.xpath("//option[.='" + acc.getState() + "']"));
        //selectFirstElm();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(acc.getPhone());
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(acc.getPassword());
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(acc.getPassword());
        driver.findElement(By.xpath("//button[@name='create_account']")).click();
        waitInvisibility(By.xpath("//button[@name='create_account']"));
        //выйти
        driver.findElement(By.xpath("//div[@class='box']//a[.='Logout']")).click();
        //войти
        waitInvisibility(By.xpath("//div[@class='box']//a[.='Logout']"));
        WebElement loginBlock = driver.findElement(By.xpath("//div[@id='box-account-login']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='box']//input[@name='email']")));
        loginBlock.findElement(By.xpath(".//input[@name='email']")).sendKeys(email);
        loginBlock.findElement(By.xpath(".//input[@name='password']")).sendKeys(acc.getPassword());
        loginBlock.findElement(By.xpath(".//button[.='Login']")).click();
        //выйти
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='box']//a[.='Logout']"))).click();


    }

}
