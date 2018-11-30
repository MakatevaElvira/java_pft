package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactGeneral;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactGeneralGenerator {
  public static void main (String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactGeneral> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactGeneral> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactGeneral contact : contacts) {
      writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s/%s\n",contact.getName(),contact.getLastname(),contact.getAddress()
              ,contact.getHomeNumber(),contact.getMobileNumber(),contact.getWorkNumber()
              ,contact.getEmail1(), contact.getEmail2(),contact.getEmail3()));
    }
    writer.close();

  }

  private static List<ContactGeneral> generateContacts(int count) {
    List <ContactGeneral> contacts = new ArrayList<ContactGeneral>();
    for (int i=0; i< count;i++ ) {
      contacts.add(new ContactGeneral().withName())
    }

  }
}
