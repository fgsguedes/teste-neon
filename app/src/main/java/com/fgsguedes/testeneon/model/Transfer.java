package com.fgsguedes.testeneon.model;

import java.math.BigDecimal;
import java.util.Date;

public final class Transfer {

  public final long id;
  public final long contactId;
  public final BigDecimal value;
  public final Date date;

  public Transfer(long id, long contactId, BigDecimal value, Date date) {
    this.id = id;
    this.contactId = contactId;
    this.value = value;
    this.date = date;
  }
}
