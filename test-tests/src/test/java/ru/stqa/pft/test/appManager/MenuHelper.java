package ru.stqa.pft.test.appManager;


import org.openqa.selenium.By;

public class MenuHelper extends HelperBase  {

  public MenuHelper(ApplicationManager app) {     super(app);
  }

  public void checkTopMenu(){
    displayed("WHO WE SERVE");
    displayed("SUBJECTS");
    displayed("SUBJECTS");

    //Wait<WebDriver> wait = new WebDriverWait(wd, 5, 5000);
    //wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.name("group"))));
  }
  public void checkWhoWeServe(){
    waitOfVisiobility(By.xpath("//a[contains(text(),'WHO WE SERVE')]/.."));
    click(By.xpath("//a[contains(text(),'WHO WE SERVE')]/.."));
    displayed( "Students");
    displayed( "Instructors");
    displayed( "Book Authors");
    displayed( "Professionals");
    displayed( "Students");
    displayed( "Researchers");
    displayed( "Institutions");
    displayed( "Librarians");
    displayed( "Corporations");
    displayed( "Societies");
    displayed( "Journal Editors");
    displayed( "Bookstores");
    displayed( "Government");

  }
  public int whoWeServeCount() {
    return wd.findElements(By.xpath("//div[@id='Level1NavNode1']//a")).size();
  }
}
