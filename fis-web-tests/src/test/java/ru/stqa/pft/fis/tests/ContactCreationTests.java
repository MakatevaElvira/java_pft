package ru.stqa.pft.fis.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.fis.model.ContactGeneral;
import ru.stqa.pft.fis.model.Contacts;
import ru.stqa.pft.fis.model.ContactGeneral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsAsXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactGeneral.class);
      List<ContactGeneral> contacts = (List<ContactGeneral>) xstream.fromXML(xml);
      return  contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }
  @DataProvider
  public Iterator<Object[]> validContactsAsJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactGeneral> contacts = gson.fromJson(json,new TypeToken<List<ContactGeneral>>(){}.getType()); //List<ContactGeneral>.class);
      return  contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }
  @Test (dataProvider = "validContactsAsJson")
  public void testContactCreation(ContactGeneral contact) throws Exception {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo( before.size() + 1));
    Contacts after =  app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test (enabled = false)
  public void testContactCreationOld() throws Exception {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/stru.png");
    ContactGeneral contact = new ContactGeneral().withName("Efilia").withLastname("Liopda").withLastname("Makateva")
            .withAdress("410003, г.Саратов ул.Кирова д.1")
            .withEmail("myemail@bk.ru").withEmail2("youemail@gmail.ru").withEmail3("ouremail@yandex.ru")
            .withHomeNumber("777").withMobileNumber("111-7").withWorkNumber("25 12 2").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo( before.size() + 1));
    Contacts after =  app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }


  @Test(enabled = false)
  public void testCurrentDir () {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
