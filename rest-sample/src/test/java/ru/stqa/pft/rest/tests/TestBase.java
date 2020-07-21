package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appManager.ApplicationManager;

import java.io.IOException;
import java.util.Objects;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager();

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
      }

  public ApplicationManager getApplicationManager() {
    return app;
  }


  boolean isIssueOpen1(int issueId) throws IOException {
    String status = app.rest().getIssuesById1(issueId).iterator().next().getState_name();
    System.out.println(status);
    return !Objects.equals(status, "closed");  }//closed

  public void skipIfNotFixed(int issueId) throws IOException {
    if ( isIssueOpen1(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}

