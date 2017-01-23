package com.fgsguedes.testeneon.data.repository;

import com.fgsguedes.testeneon.model.Contact;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class ContactsRepository {

  private final List<Contact> contacts = new ArrayList<>();

  public ContactsRepository() {
    contacts.add(new Contact(1, "Filipe Guedes 1", "11 974-605-298", ""));
    contacts.add(new Contact(2, "Filipe Guedes 2", "11 974-605-298", ""));
    contacts.add(new Contact(3, "Filipe Guedes 3", "11 974-605-298", ""));
    contacts.add(new Contact(4, "Filipe Guedes 4", "11 974-605-298", ""));
    contacts.add(new Contact(5, "Filipe Guedes 5", "11 974-605-298", ""));
    contacts.add(new Contact(6, "Filipe Guedes 6", "11 974-605-298", ""));
    contacts.add(new Contact(7, "Filipe Guedes 7", "11 974-605-298", ""));
    contacts.add(new Contact(8, "Filipe Guedes 8", "11 974-605-298", ""));
    contacts.add(new Contact(9, "Filipe Guedes 9", "11 974-605-298", ""));
    contacts.add(new Contact(10, "Filipe Guedes1 10", "11 974-605-298", ""));
    contacts.add(new Contact(11, "Filipe Guedes1 11", "11 974-605-298", ""));
    contacts.add(new Contact(12, "Filipe Guedes1 12", "11 974-605-298", ""));
    contacts.add(new Contact(13, "Filipe Guedes1 13", "11 974-605-298", ""));
    contacts.add(new Contact(14, "Filipe Guedes1 14", "11 974-605-298", ""));
    contacts.add(new Contact(15, "Filipe Guedes1 15", "11 974-605-298", ""));
  }

  public Single<List<Contact>> list() {
    return Single.just(contacts);
  }

  public Maybe<Contact> find(long id) {
    return Maybe.fromCallable(() -> {
      for (Contact contact : contacts) {
        if (contact.id == id) {
          return contact;
        }
      }
      return null;
    });
  }
}
