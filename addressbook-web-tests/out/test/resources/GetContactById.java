package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GetContactById extends ForwardingSet <ContactGeneral> {
  private Set <ContactGeneral> delegate;
  private Set<GroupData> groups= new HashSet<GroupData>();

  public GetContactById(GetContactById contacts) {
    this.delegate = new HashSet<ContactGeneral>(contacts.delegate);
  }
  public GetContactById() {
    this.delegate = new HashSet<ContactGeneral>();
  }

  public GetContactById(Collection<ContactGeneral> ContactById) {
    this.delegate = new HashSet<ContactGeneral>(ContactById);
  }
  public Groups getGroups() {
    return new Groups(groups);
  }

  @Override
  protected Set<ContactGeneral> delegate() {
    return delegate;
  }
  public GetContactById withAdded(ContactGeneral contact){
    GetContactById contacts = new GetContactById(this);
    contacts.add(contact);
    return contacts;
  }
  public GetContactById withOut(ContactGeneral contact){
    GetContactById contacts = new GetContactById(this);
    contacts.remove(contact);
    return contacts;
  }
}
