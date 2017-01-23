package com.fgsguedes.testeneon.model;

import android.support.annotation.NonNull;

public class X implements Comparable<X> {

  public final Contact contact;
  public final double amount;

  public X(Contact contact, double amount) {
    this.contact = contact;
    this.amount = amount;
  }

  @Override
  public int compareTo(@NonNull X other) {
    return Double.compare(other.amount, amount);
  }
}
