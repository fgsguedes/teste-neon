package com.fgsguedes.testeneon.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fgsguedes.testeneon.contract.SendMoneyContract;
import com.fgsguedes.testeneon.data.repository.ContactsRepository;
import com.fgsguedes.testeneon.data.repository.TokenRepository;
import com.fgsguedes.testeneon.data.repository.TransactionsRepository;
import com.fgsguedes.testeneon.model.Contact;

import java.util.ArrayList;

public class SendMoneyPresenter implements SendMoneyContract.Presenter {

  private final String TAG = getClass().getSimpleName();

  private final TokenRepository tokenRepository;
  private final ContactsRepository contactsRepository;
  private final TransactionsRepository transactionsRepository;

  private SendMoneyContract.View view;

  private final ArrayList<Contact> contacts = new ArrayList<>();

  public SendMoneyPresenter(
      TokenRepository tokenRepository,
      ContactsRepository contactsRepository,
      TransactionsRepository transactionsRepository
  ) {
    this.tokenRepository = tokenRepository;
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
            this::onReceivedContact,
            this::onListContactError
        );
  }

  private void onReceivedContact(@NonNull Contact contact) {
    contacts.add(contact);
    view.showContact(contact);
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
      view.showAmountPrompt(contact);

    } else {
      transactionsRepository.sendMoney(contact, value)
          .subscribe(
              this::onTransactionSent,
              this::onTransactionSendError
          );
    }
  }

  private void onTransactionSent() {
    view.notifyTransactionSent();
  }

  private void onTransactionSendError(Throwable error) {
    Log.e(TAG, "Send transaction error", error);
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
