package ru.stqa.pft.test.appManager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class MenuHelper extends HelperBase {

  public MenuHelper(ApplicationManager app) {
    super(app);
  }

  public void checkTopMenu() {
    displayed("WHO WE SERVE");
    displayed("SUBJECTS");
    displayed("SUBJECTS");

    //Wait<WebDriver> wait = new WebDriverWait(wd, 5, 5000);
    //wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.name("group"))));
  }

  public void checkWhoWeServe() {
    waitVisibilityOf(By.xpath("//a[contains(text(),'WHO WE SERVE')]/.."));
    click(By.xpath("//a[contains(text(),'WHO WE SERVE')]/.."));
    displayed("Students");
    displayed("Instructors");
    displayed("Book Authors");
    displayed("Professionals");
    displayed("Students");
    displayed("Researchers");
    displayed("Institutions");
    displayed("Librarians");
    displayed("Corporations");
    displayed("Societies");
    displayed("Journal Editors");
    displayed("Bookstores");
    displayed("Government");

  }

  public int whoWeServeCount() {
    return wd.findElements(By.xpath("//div[@id='Level1NavNode1']//a")).size();
  }

  public void checkStudentsItem() {
    openStudentsItem();
    assertStudentsUrl();
    openStudentsLearnMore();
    assertStudentsLearnMoreUrl();
    backToHomePage();
    close();
  }

  private void backToHomePage() {
    wd.get(app.getProperty("web.baseUrl"));
  }

  public void openStudentsItem() {
    waitVisibilityOf(By.linkText("WHO WE SERVE"));
    waitClickableOf(By.xpath("//a[contains(text(),'WHO WE SERVE')]/.."));
    click(By.xpath("//a[contains(text(),'WHO WE SERVE')]/.."));
    click(By.linkText("Students"));
  }

  public void assertStudentsUrl() {
    wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    String URL = wd.getCurrentUrl();
    assertEquals(URL, "https://www.wiley.com/en-us/students");
    close();
  }

  public void openStudentsLearnMore() {
    displayed("WHO WE SERVE");
    wd.getPageSource().contains("Learn more");
    waitClickableOf(By.xpath("//p/a/span/span"));
    click(By.xpath("//p[21]/a/span/span"));
  }

  public void assertStudentsLearnMoreUrl() {
    wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    waitVisibilityOf(By.cssSelector("img.dark_logo.mobile_logo"));
    close();
    String redirected_url = wd.getCurrentUrl();
    assertEquals(redirected_url, "https://www.wileyplus.com/");
  }

  public void checkSubjectsMenu() {
    waitVisibilityOf(By.xpath("//a[contains(text(),'SUBJECTS')]/.."));
    //waitVisibilityOf(By.linkText("SUBJECTS"));
    //click(By.xpath("//ul[@class='navigation-menu-items initialized']/li[2]/div/span[2]"));

    // имитация наведения мыши на объект:
    Actions actions = new Actions(wd);
    //actions.moveToElement(wd.findElement(By.xpath("//a[contains(text(),'SUBJECTS')]/.."))).build().perform();
    actions.moveToElement(wd.findElement(By.linkText("SUBJECTS"))).build().perform();

    waitVisibilityOf(By.xpath("//a[contains(text(),'Education')]/../span"));
    click(By.xpath("//a[contains(text(),'Education')]/../span"));
    //click(By.xpath("//li[9]/span"));

    waitVisibilityOf(By.linkText("SUBJECTS"));
    displayed("SUBJECTS");
    waitVisibilityOf(By.linkText("Assessment, Evaluation Methods"));
    displayed("Assessment, Evaluation Methods");
    displayed("Classroom Management");
    displayed("Conflict Resolution & Mediation");
    displayed("Curriculum Tools");
    displayed("Education & Public Policy");
    displayed("Educational Research");
    displayed("General Education");
    displayed("Higher Education");
    displayed("Information & Library Science");
    displayed("Special Education");
    displayed("Special Topics");
    displayed("Vocational Technology");
  }

  public int educationCount() {
    return wd.findElements(By.xpath("//li[9]/div/ul/ul/li/a")).size();
  }

  public void skip() {
    waitVisibilityOf(By.xpath("//button[contains(text(),'NO')]/.."));
    waitClickableOf(By.cssSelector("button.close"));
    click(By.cssSelector("button.close"));

    String parent = wd.getWindowHandle();

    Set<String> pops = wd.getWindowHandles();
    {
      Iterator<String> it = pops.iterator();
      while (it.hasNext()) {

        String popupHandle = it.next().toString();
        if (!popupHandle.contains(parent)) {
          wd.switchTo().window(popupHandle);
          System.out.println("Popu Up Title: " + wd.switchTo().window(popupHandle).getTitle());
          wd.close();
        }
      }
    }
  }

  public void skip2() {
    waitVisibilityOf(By.xpath("//button[contains(text(),'NO')]/.."));
    waitClickableOf(By.cssSelector("button.close"));
    click(By.cssSelector("button.close"));
  }

  public void close() {
    if (isElementPresent(By.xpath("//button[contains(text(),'NO')]/.."))) {
      click(By.cssSelector("button.close"));
    }
  }


  public void checkLogo() {
    waitClickableOf(By.xpath("//img[1][@title='Wiley']"));
    click(By.xpath("//img[1][@title='Wiley']"));
    String URL = wd.getCurrentUrl();
    assertEquals(URL, app.getProperty("web.baseUrl"));
    close();

  }

  public void checkSearch() {
    waitInvisibilityOfSelectCountry();
    waitClickableOf(By.xpath("//button[@type='submit']"));
    click(By.xpath("//button[@type='submit']"));
    String URL = wd.getCurrentUrl();
    assertEquals(URL, app.getProperty("web.baseUrl"));
    waitInvisibilityOfSelectCountry();
    //assertEquals(false,isElementPresent(By.xpath("//div[contains(text(),'Undetected country')]")));
  }

  public void checkSearchJava() {
    waitInvisibilityOfSelectCountry();
    waitClickableOf(By.xpath("//input[@type='search']"));
    type(By.xpath("//input[@type='search']"), "Java");
    suggestionsCount();
    productsCount();

  }

  public int suggestionsCount() {
    // return wd.findElements(By.xpath("//h3[contains(text(),'Suggestions')]/..//span[contains(text(),'java')]")) //[starts-with(.,' sfoobar:')]
    //.size();
    return wd.findElements(By.xpath("//h3[contains(text(),'Suggestions')]/..//span[starts-with(text(),'java')]"))
            .size();
  }

  public int productsCount() {
    return wd.findElements(By.xpath("//h3[contains(text(),'Products')]/..//span[contains(text(),'Java')]")) //[starts-with(.,' sfoobar:')]
            .size();
  }

  public void searchJava() {
    waitInvisibilityOfSelectCountry();
    waitClickableOf(By.xpath("//input[@type='search']"));
    type(By.xpath("//input[@type='search']"), "Java");
    click(By.xpath("//button[@type='submit']"));
    close();
    waitInvisibilityOfSelectCountry();
    countItemsPerPage();
    countAddToCartOnSearchPage();

  }
  public void searchJava2() { //without AddToCart
    waitInvisibilityOfSelectCountry();
    waitClickableOf(By.xpath("//input[@type='search']"));
    type(By.xpath("//input[@type='search']"), "Java");
    click(By.xpath("//button[@type='submit']"));
    close();
    waitInvisibilityOfSelectCountry();
    countItemsPerPage();

  }
  public int countItemsPerPage(){
    //return wd.findElements(By.xpath("//div[@class='product-image']")).size();
    return wd.findElements(By.xpath("//div[@class='product-content']/h3[@class='product-title']")).size();
  }
  public int countAddToCartOnSearchPage(){
    return wd.findElements(By.xpath("//button[contains(text(),'Add to cart')]")).size();

  }

  public int countAddToCartOnSearchPage1(){ // нужно доработать
    List<WebElement> elements = wd.findElements(By.xpath("//div[@class='product-image']"));
    for (WebElement element : elements){
      element.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).getSize();
    }
    System.out.println(elements.size());
    return elements.size();
  }

}
