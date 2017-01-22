package com.fgsguedes.testeneon.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fgsguedes.testeneon.contract.SendMoneyContract;
import com.fgsguedes.testeneon.data.repository.ContactsRepository;
import com.fgsguedes.testeneon.data.repository.TokenRepository;
import com.fgsguedes.testeneon.data.repository.TransactionsRepository;

public class SendMoneyPresenter implements SendMoneyContract.Presenter {

  private final String TAG = getClass().getSimpleName();

  private final TokenRepository tokenRepository;
  private final ContactsRepository contactsRepository;
  private final TransactionsRepository transactionsRepository;

  private SendMoneyContract.View view;

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
            view::showContact,
            this::onListContactError
        );
  }

  private void onListContactError(Throwable error) {
    Log.e(TAG, "List contacts error", error);
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {

  }

  @Override
  public void contactClicked(long contactId) {

  }
}
