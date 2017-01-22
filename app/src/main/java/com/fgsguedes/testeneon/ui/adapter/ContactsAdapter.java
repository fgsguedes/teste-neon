package com.fgsguedes.testeneon.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fgsguedes.testeneon.R;
import com.fgsguedes.testeneon.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {

  private final Context context;
  private final List<Contact> contacts = new ArrayList<>();

  public ContactsAdapter(Context context) {
    this.context = context;
  }

  @Override
  public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    return new ContactsViewHolder(inflater.inflate(R.layout.adapter_row_contacts, parent, false));
  }

  @Override
  public void onBindViewHolder(ContactsViewHolder holder, int position) {
    Contact contact = contacts.get(position);

    holder.thumbnail.setImageURI(Uri.parse(contact.photoPath));
    holder.contactName.setText(contact.name);
    holder.contactPhoneNumber.setText(contact.phoneNumber);
  }

  @Override
  public int getItemCount() {
    return contacts.size();
  }

  public void addItem(@NonNull Contact contact) {
    int addedPosition = contacts.size();
    contacts.add(contact);
    notifyItemInserted(addedPosition);
  }
}

class ContactsViewHolder extends RecyclerView.ViewHolder {

  final ImageView thumbnail;
  final TextView contactName;
  final TextView contactPhoneNumber;

  ContactsViewHolder(View itemView) {
    super(itemView);
    thumbnail = (ImageView) itemView.findViewById(R.id.image_contact_thumbnail);
    contactName = (TextView) itemView.findViewById(R.id.text_contact_name);
    contactPhoneNumber = (TextView) itemView.findViewById(R.id.text_contact_phone_number);
  }
}
