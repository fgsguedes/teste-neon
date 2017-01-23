package com.fgsguedes.testeneon.data.repository;

import com.fgsguedes.testeneon.model.Contact;

import io.reactivex.Observable;

public class ContactsRepository {

  public Observable<Contact> list() {
    return Observable.fromArray(
        new Contact(1, "Filipe Guedes 1", "11 974-605-298", ""),
        new Contact(2, "Filipe Guedes 2", "11 974-605-298", ""),
        new Contact(3, "Filipe Guedes 3", "11 974-605-298", ""),
        new Contact(4, "Filipe Guedes 4", "11 974-605-298", ""),
        new Contact(5, "Filipe Guedes 5", "11 974-605-298", ""),
        new Contact(6, "Filipe Guedes 6", "11 974-605-298", ""),
        new Contact(7, "Filipe Guedes 7", "11 974-605-298", ""),
        new Contact(8, "Filipe Guedes 8", "11 974-605-298", ""),
        new Contact(9, "Filipe Guedes 9", "11 974-605-298", ""),
        new Contact(10, "Filipe Guedes1 10", "11 974-605-298", ""),
        new Contact(11, "Filipe Guedes1 11", "11 974-605-298", ""),
        new Contact(12, "Filipe Guedes1 12", "11 974-605-298", ""),
        new Contact(13, "Filipe Guedes1 13", "11 974-605-298", ""),
        new Contact(14, "Filipe Guedes1 14", "11 974-605-298", ""),
        new Contact(15, "Filipe Guedes1 15", "11 974-605-298", "")
    );
  }
}
