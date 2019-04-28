package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() throws IOException, MessagingException {
    if (app.db().users().size() == 0) {
      long now = System.currentTimeMillis();
      String email = String.format("user%s@localhost", now);//.localdomain
      String user = String.format("user%s", now);
      String password = "password";
      app.james().createUser(user, password);
      app.registration().start(user, email);
      List<MailMessage> mailMessages = app.james().waitForMail(user, password, 120000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
    }

  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    Users before = app.db().users(); // получили список пользователей
    User modifyUser = before.iterator().next();
    User user = new User().withId(modifyUser.getId()).withUsername(modifyUser.getUsername())
            .withEmail(modifyUser.getEmail()).withPassword(modifyUser.getPassword()).withCod(modifyUser.getCod());//выбрали пользователя
    String password = "password"; // пароль от почты пользователя
    long now = System.currentTimeMillis();
    String passwordNew = String.format("password%s", now); // новый пароль от mantis;
    app.james().drainEmail(user, password);// очистка почты пользователя

    app.session().login(); //Шаг 1.вошли под админом
    app.goTo().userManagement(); // зашли в управление пользователями
    app.registration().selectUser(user); //выбрали пользователя
    app.registration().changePassword();//нажали Сбросить пароль


    List<MailMessage> mailMessages = app.james().waitForMail1(user, password, 120000);// Шаг 2. получение ссылки и переход по ней
    String confirmationLink = findChangingLink(mailMessages, modifyUser.getEmail());
    app.registration().finish(confirmationLink, passwordNew); //меняем пароль на новый
    //Проверки:
    User userById = app.db().getUserById(modifyUser.getId());
    assertTrue(app.newSession().loginWithNewPassword(userById, passwordNew)); // Шаг 3. через HTTP логинится пользователь
    Users after = app.db().users();
    assertThat(after, equalTo(before.withOut(modifyUser).withAdded(userById)));

  }

  private String findChangingLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
