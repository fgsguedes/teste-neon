package com.fgsguedes.testeneon.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class Contact implements Parcelable {

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

  private Contact(Parcel parcel) {
    this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contact contact = (Contact) o;

    return id == contact.id;

  }

  @Override
  public int hashCode() {
    return (int) (id ^ (id >>> 32));
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(id);
    dest.writeString(name);
    dest.writeString(phoneNumber);
    dest.writeString(photoPath);
  }

  private static Creator CREATOR = new Creator() {

    @Override
    public Contact createFromParcel(Parcel source) {
      return new Contact(source);
    }

    @Override
    public Contact[] newArray(int size) {
      return new Contact[size];
    }
  };
}
