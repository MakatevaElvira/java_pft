package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet <ContactGeneral> {
  private Set <ContactGeneral> delegate;

  public Contacts (Contacts contacts) {
    this.delegate = new HashSet<ContactGeneral>(contacts.delegate);
  }
  public Contacts () {
    this.delegate = new HashSet<ContactGeneral>();
  }

  public Contacts(Collection<ContactGeneral> contacts) {
    this.delegate = new HashSet<ContactGeneral>(contacts);
  }

  @Override
  protected Set<ContactGeneral> delegate() {
    return delegate;
  }
  public Contacts withAdded(ContactGeneral contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }
  public Contacts withOut(ContactGeneral contact){
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
