package com.fgsguedes.testeneon.model.datatransfer;

import android.support.annotation.NonNull;

import com.fgsguedes.testeneon.model.Contact;

public class ContactTransactionTotal implements Comparable<ContactTransactionTotal> {

  public final Contact contact;
  public final double amount;

  public ContactTransactionTotal(Contact contact, double amount) {
    this.contact = contact;
    this.amount = amount;
  }

  @Override
  public int compareTo(@NonNull ContactTransactionTotal other) {
    return Double.compare(other.amount, amount);
  }
}
