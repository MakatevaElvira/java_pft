package ru.stqa.pft.mantis.tests;

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

public class ChangePasswordTests extends  TestBase {

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    Users before = app.db().users(); // получили список пользователей
   // User modifyUser = new User().withId(Integer.parseInt("15")).withUsername("elviramak").withEmail("elviramak@mail.ru");//выбрали пользователя
    //User modifyUser = (before.iterator().next());
    String modifyUser = String.format("%s", before.iterator().next());
   // User user = new User().withId(modifyUser.getId()).withUsername(modifyUser.getUsername());

    //String modifyUser = String.format( app.db().getUserById(Integer.parseInt("15")));
    String password = app.db().getUserById(Integer.parseInt("15")).getPassword(); //"password";
    app.session().login(); //вошли под админом
    app.goTo().userManagement(); // зашли в управление пользователями
    //вытащить из базы конкретного пользователя, найти пользователя по линк.текст=имя пользователя, нажать
    app.registration().changePassword();//нажали Сбросить пароль

    //List<MailMessage> mailMessages = app.james().waitForMail(modifyUser, password, 120000);
    //String confirmationLink = findConfirmationLink(mailMessages,modifyUser.getEmail1());
    //app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(modifyUser,password));

  }

  @Test
  public void testChangePassword2() throws IOException, MessagingException {
    Users before = app.db().users(); // получили список пользователей
    User modifyUser = before.iterator().next();
    User user = new User().withId(modifyUser.getId()).withUsername(modifyUser.getUsername())
            .withEmail(modifyUser.getEmail()).withPassword(modifyUser.getPassword()).withCod(modifyUser.getCod());//выбрали пользователя
    User admin = app.db().getAdmin();
    String password = "password";
    long now = System.currentTimeMillis();
    String passwordNew = String.format("password%s", now); // новый пароль;
    app.session().login(); //вошли под админом
    app.goTo().userManagement(); // зашли в управление пользователями
    app.registration().selectUser(user);
    //вытащить из базы конкретного пользователя, найти пользователя по линк.текст=имя пользователя, нажать
    app.registration().changePassword();//нажали Сбросить пароль

    //List<MailMessage> mailMessages = app.james().waitForMail1(user,password, 120000);// шаг 2 получение ссылки и переход по ней
    List<MailMessage> mailMessages = app.james().waitForMail1(user, password, 120000);
    String confirmationLink = findChangingLink(mailMessages,modifyUser.getEmail());
    app.registration().finish(confirmationLink, passwordNew); //меняем пароль на новый

    User userById = app.db().getUserById(modifyUser.getId());
    assertTrue(app.newSession().login2(userById)); //шаг 3 через HTTP логинится пользователь

    Users after = app.db().users();
    assertThat(after, equalTo(before.withOut(modifyUser).withAdded(userById)));

    app.goTo().userManagement();
   // verifyGroupListInUI();

  }
  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
  private String findChangingLink (List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
