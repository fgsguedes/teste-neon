package com.fgsguedes.testeneon.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fgsguedes.testeneon.contract.SendMoneyContract;
import com.fgsguedes.testeneon.data.repository.ContactsRepository;
import com.fgsguedes.testeneon.data.repository.TransactionsRepository;
import com.fgsguedes.testeneon.model.Contact;

import java.util.List;

public class SendMoneyPresenter implements SendMoneyContract.Presenter {

  private final String TAG = getClass().getSimpleName();

  private final ContactsRepository contactsRepository;
  private final TransactionsRepository transactionsRepository;

  private SendMoneyContract.View view;

  private List<Contact> contacts;

  public SendMoneyPresenter(
      ContactsRepository contactsRepository,
      TransactionsRepository transactionsRepository
  ) {
    this.contactsRepository = contactsRepository;
    this.transactionsRepository = transactionsRepository;
  }

  @Override
  public void bindView(SendMoneyContract.View view) {
    this.view = view;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    contactsRepository.list()
        .subscribe(
            this::onReceivedContacts,
            this::onListContactError
        );
  }

  private void onReceivedContacts(@NonNull List<Contact> contacts) {
    this.contacts = contacts;
    view.showContacts(contacts);
  }

  private void onListContactError(@NonNull Throwable error) {
    Log.e(TAG, "List contacts error", error);
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {

  }

  @Override
  public void contactClicked(long contactId) {
    Contact contact = find(contactId);
    view.showAmountPrompt(contact);
  }

  @Override
  public void onReceivedTransactionValue(Contact contact, double value) {

    if (value <= 0) {
      view.showInvalidValueWarning();

    } else {
      view.hideDialogs();
      transactionsRepository.sendMoney(contact, value)
          .subscribe(
              this::onTransactionSent,
              this::onTransactionSendError
          );
    }
  }

  private void onTransactionSent() {
    view.hideDialogs();
    view.notifyTransactionSent();
  }

  private void onTransactionSendError(Throwable error) {
    Log.e(TAG, "Unable to send transaction", error);
    view.notifyTransactionError();
  }

  @NonNull
  private Contact find(long contactId) {
    for (Contact c : contacts) {
      if (c.id == contactId) {
        return c;
      }
    }

    throw new IllegalArgumentException("Invalid contactId");
  }
}
