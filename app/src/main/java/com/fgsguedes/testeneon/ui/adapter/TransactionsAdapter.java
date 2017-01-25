package com.fgsguedes.testeneon.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fgsguedes.testeneon.R;
import com.fgsguedes.testeneon.model.datatransfer.ContactTransactionTotal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsViewHolder> {

  private final Context context;
  private final Locale locale;
  private List<ContactTransactionTotal> transactions = new ArrayList<>();

  public TransactionsAdapter(Context context) {
    this.context = context;
    locale = context.getResources().getConfiguration().locale;
    setHasStableIds(true);
  }

  @Override
  public TransactionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.adapter_row_transactions, parent, false);
    return new TransactionsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(TransactionsViewHolder holder, int position) {
    ContactTransactionTotal transaction = transactions.get(position);

    holder.contactName.setText(transaction.contact.name);
    holder.contactPhoneNumber.setText(transaction.contact.phoneNumber);
    holder.contactTransferredAmount.setText(String.format(locale, "R$ %.2f", transaction.amount));
  }

  @Override
  public int getItemCount() {
    return transactions.size();
  }

  @Override
  public long getItemId(int position) {
    return transactions.get(position).contact.id;
  }

  public void setItems(@NonNull List<ContactTransactionTotal> contactTransactionTotals) {
    this.transactions = contactTransactionTotals;
    notifyDataSetChanged();
  }
}

class TransactionsViewHolder extends RecyclerView.ViewHolder {

  final ImageView thumbnail;
  final TextView contactName;
  final TextView contactPhoneNumber;
  final TextView contactTransferredAmount;

  TransactionsViewHolder(View itemView) {
    super(itemView);
    thumbnail = (ImageView) itemView.findViewById(R.id.image_contact_thumbnail);
    contactName = (TextView) itemView.findViewById(R.id.text_contact_name);
    contactPhoneNumber = (TextView) itemView.findViewById(R.id.text_contact_phone_number);
    contactTransferredAmount = (TextView) itemView.findViewById(R.id.text_contact_transferred_amount);
  }
}
