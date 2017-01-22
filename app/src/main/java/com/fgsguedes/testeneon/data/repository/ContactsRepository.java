package com.fgsguedes.testeneon.data.repository;

import com.fgsguedes.testeneon.model.Contact;

import io.reactivex.Observable;

public interface ContactsRepository {
  public Observable<Contact> list();
}
