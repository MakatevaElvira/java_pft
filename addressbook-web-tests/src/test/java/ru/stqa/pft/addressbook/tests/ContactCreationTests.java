package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGeneral;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
      return  contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
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
      return  contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1"));
    }
  }
  @Test (dataProvider = "validContactsAsJson")
  public void testContactCreation(ContactGeneral contact) throws Exception {
    Groups groups = app.db().groups();
    app.goTo().contactPage();
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/stru.png");
    app.contact().create(contact.withPhoto(photo).inGroup(groups.iterator().next()),true);
    assertThat(app.contact().count(), equalTo( before.size() + 1));
    Contacts after =  app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListInUI(); // сравнение значений из базы и с интерфейса

  }

  @Test (enabled = false)
  public void testContactCreationOld() throws Exception {
    app.goTo().contactPage();
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/stru.png");
    ContactGeneral contact = new ContactGeneral().withName("Efilia").withLastName("Liopda").withLastName("Makateva")
            .withAddress("410003, г.Саратов ул.Кирова д.1")
            .withEmail("myemail@bk.ru").withEmail2("youemail@gmail.ru").withEmail3("ouremail@yandex.ru")
            .withHomeNumber("777").withMobileNumber("111-7").withWorkNumber("25 12 2").withPhoto(photo)
            .inGroup(groups.iterator().next());
    app.contact().create(contact,true);
    assertThat(app.contact().count(), equalTo( before.size() + 1));
    Contacts after =  app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

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
