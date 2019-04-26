package ru.stqa.pft.test.tests;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckingTests extends TestBase {

  @Test
  public void testTopMenu(){
    app.menuHelper().checkTopMenu();
  }
  @Test
  public void testWhoWeServeMenu(){
    app.menuHelper().checkWhoWeServe();
    assertThat(app.menuHelper().whoWeServeCount(), equalTo( 12));

  }
}
