package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactGeneral;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactGeneralGenerator {
  @Parameter(names = "-c", description = "Contacts count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter (names = "-g", description = "Contact general format")
  public  String format;

  public static void main(String[] args) throws IOException {
    ContactGeneralGenerator generator = new ContactGeneralGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactGeneral> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")){
      saveAsJson(contacts, new File(file));
    }else {
      System.out.println("Unrecognized format" + format);
    }
  }

  private void saveAsJson(List<ContactGeneral> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactGeneral> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactGeneral.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private static void saveAsCsv(List<ContactGeneral> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      for (ContactGeneral contact : contacts) {
        writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s/%s\n", contact.getName(), contact.getLastName(), contact.getAddress()
                , contact.getHomeNumber(), contact.getMobileNumber(), contact.getWorkNumber()
                , contact.getEmail1(), contact.getEmail2(), contact.getEmail3()));
      }
    }
  }

  private static List<ContactGeneral> generateContacts(int count) {
    List<ContactGeneral> contacts = new ArrayList<ContactGeneral>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactGeneral().withName(String.format("Elvira%s", i))
              .withLastname(String.format("Mak%s", i))
              .withAdress((String.format("Saratov%s", i)))
              .withHomeNumber((String.format("+7900300555%s", i)))
              .withMobileNumber((String.format("+7900300566%s",i)))
              .withWorkNumber((String.format("+7900300577%s",i)))
              .withEmail(String.format("myemail%s@bk.ru",i))
              .withEmail2(String.format("youemail%s@bk.ru",i)).withEmail3(String.format("vemail%s@bk.ru",i)));
    }
    return contacts;

  }
}
