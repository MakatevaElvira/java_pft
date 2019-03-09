package ru.stqa.pft.addressbook.appManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactGeneral;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;

public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData" ).list();
    for ( GroupData group :  result ) {
      System.out.println(group );
    }
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactGeneral> result = session.createQuery( "from ContactGeneral where deprecated ='0000-00-00'" ).list();
    for ( ContactGeneral contact :  result ) {
      System.out.println(contact );
    }
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }
}
