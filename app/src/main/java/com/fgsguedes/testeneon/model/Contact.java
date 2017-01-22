package com.fgsguedes.testeneon.model;

public final class Contact {

  public final long id;
  public final String name;
  public final String phoneNumber;
  public final String photoPath;

  public Contact(long id, String name, String phoneNumber, String photoPath) {
    this.id = id;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.photoPath = photoPath;
  }
}
