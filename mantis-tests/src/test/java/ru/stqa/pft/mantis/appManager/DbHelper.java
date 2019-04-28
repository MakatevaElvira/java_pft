package ru.stqa.pft.mantis.appManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.User;
import ru.stqa.pft.mantis.model.Users;

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

  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<User> result = session.createQuery("from User where access_level = '25'").list();
    for (User user : result) {
      System.out.println(user);
    }
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

  public User getUserById(int id){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    User user  = (User) session.createQuery(   // new ContactGeneral().withId(id)
            "from User where id = '" + id +"'").uniqueResult();

    session.getTransaction().commit();
    session.close();
    return user;
  }
  public User getAdmin(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    User user  = (User) session.createQuery(   // new ContactGeneral().withId(id)
            "from User where access_level = '90'").uniqueResult();

    session.getTransaction().commit();
    session.close();
    return user;
  }
}
