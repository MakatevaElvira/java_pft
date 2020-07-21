package ru.stqa.pft.rest.appManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static com.jayway.restassured.RestAssured.get;

public class RestHelper {
  private ApplicationManager app;

  public RestHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Issue> getIssues() throws IOException {
    //старая: String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
    //новая:
    String json = get("http://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    System.out.println(issues);
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType()); //преобразовали в модельные объекты
  }

  public Set<Issue> getIssueById(int issueId) throws IOException {
    String json = get("http://bugify.stqa.ru/api/issues/"+ issueId +".json").asString();
    JsonElement parse = new JsonParser().parse(json);
    JsonElement issues = parse.getAsJsonObject().get("issues");
    System.out.println(issues);
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() { }.getType()); //преобразовали в модельные объекты
  }


  public int createIssue(Issue newIssue) throws IOException { //Идентификатор созданного баг-репорта
    //String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json").bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
    //new BasicNameValuePair("description", newIssue.getDescription()))).returnContent().asString();

    String json = RestAssured.given().parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("http://bugify.stqa.ru/api/issues.json").asString();

    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public Set<Issue> getIssuesById1(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    System.out.println(json);
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
  }

  private org.apache.http.client.fluent.Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }

}
