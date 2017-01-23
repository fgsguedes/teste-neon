package com.fgsguedes.testeneon.model;

import com.google.gson.annotations.SerializedName;

public final class Transaction {

  @SerializedName("ClienteId")
  public final long contactId;

  @SerializedName("Valor")
  public final double value;

  public Transaction(long contactId, double value) {
    this.contactId = contactId;
    this.value = value;
  }
}
