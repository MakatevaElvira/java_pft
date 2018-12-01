package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
    save(contacts, new File(file));
  }

  private static void save(List<ContactGeneral> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactGeneral contact : contacts) {
      writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s/%s\n", contact.getName(), contact.getLastname(), contact.getAddress()
              , contact.getHomeNumber(), contact.getMobileNumber(), contact.getWorkNumber()
              , contact.getEmail1(), contact.getEmail2(), contact.getEmail3()));
    }
    writer.close();

  }

  private static List<ContactGeneral> generateContacts(int count) {
    List<ContactGeneral> contacts = new ArrayList<ContactGeneral>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactGeneral().withName(String.format("Name%s", i)));
    }
    return contacts;

  }
}
