package com.fgsguedes.testeneon.model.datatransfer;

import android.support.annotation.NonNull;

import com.fgsguedes.testeneon.model.Contact;

public final class ContactTransactionTotal implements Comparable<ContactTransactionTotal> {

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


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactTransactionTotal that = (ContactTransactionTotal) o;

    if (Double.compare(that.amount, amount) != 0) return false;
    return contact.equals(that.contact);

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = contact.hashCode();
    temp = Double.doubleToLongBits(amount);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
