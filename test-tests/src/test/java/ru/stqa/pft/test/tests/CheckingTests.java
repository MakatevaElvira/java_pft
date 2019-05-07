package ru.stqa.pft.test.tests;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckingTests extends TestBase {

  @Test //1
  public void testTopMenu(){
    //app.menu().close();
    app.menu().checkTopMenu();
  }
  @Test //2
  public void testWhoWeServeMenu(){
    //app.menu().close();
    app.menu().checkWhoWeServe();
    assertThat(app.menu().whoWeServeCount(), equalTo( 12));
  }
  @Test //3
  public void testStudents(){
    app.menu().checkStudentsItem();
  }
  @Test //4
  public void testSubjectsMenu(){
    app.menu().checkSubjectsMenu();
    assertThat(app.menu().educationCount(), equalTo( 12));
  }
  @Test //5
  public void testLogo(){
    app.menu().checkLogo();
  }
  @Test //6
  public void testSearch(){
    app.menu().checkSearch();
  }
  @Test //7
  public void  testSearchJava(){
    app.menu().checkSearchJava();
    assertThat(app.menu().suggestionsCount(), equalTo( 4));
    assertThat(app.menu().productsCount(), equalTo( 4));
  }
}
