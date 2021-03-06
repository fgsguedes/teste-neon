package com.fgsguedes.testeneon.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.LongSparseArray;

import com.fgsguedes.testeneon.contract.TransactionHistoryContract;
import com.fgsguedes.testeneon.data.api.SchedulerComposer;
import com.fgsguedes.testeneon.data.repository.ContactsRepository;
import com.fgsguedes.testeneon.data.repository.TransactionsRepository;
import com.fgsguedes.testeneon.model.Transaction;
import com.fgsguedes.testeneon.model.datatransfer.ContactTransactionTotal;

import java.util.List;

import io.reactivex.Observable;

public class TransactionHistoryPresenter implements TransactionHistoryContract.Presenter {

  private final String TAG = getClass().getSimpleName();

  private TransactionHistoryContract.View view;

  private final ContactsRepository contactsRepository;
  private final TransactionsRepository transactionsRepository;
  private final SchedulerComposer composer;

  public TransactionHistoryPresenter(
      ContactsRepository contactsRepository,
      TransactionsRepository transactionsRepository,
      SchedulerComposer composer) {
    this.contactsRepository = contactsRepository;
    this.transactionsRepository = transactionsRepository;
    this.composer = composer;
  }

  @Override
  public void bindView(TransactionHistoryContract.View view) {
    this.view = view;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    transactionsRepository.list()
        .flatMapObservable(this::mapTransactions)
        .sorted()
        .toList()
        .compose(composer.singleComputation())
        .subscribe(
            this::onReceiveTransactionTotal,
            this::onError
        );
  }

  private Observable<ContactTransactionTotal> mapTransactions(List<Transaction> transactions) {
    return Observable.create(emitter -> {

      LongSparseArray<Double> valuesPerContact = new LongSparseArray<>();

      for (Transaction transaction : transactions) {
        double previousValue = valuesPerContact.get(transaction.contactId, 0.0);
        valuesPerContact.put(transaction.contactId, previousValue + transaction.value);
      }

      for (int i = 0; i < valuesPerContact.size(); i++) {
        long contactId = valuesPerContact.keyAt(i);
        double value = valuesPerContact.valueAt(i);

        contactsRepository.find(contactId)
            .subscribe(
                contact -> {
                  if (contact != null) {
                    emitter.onNext(new ContactTransactionTotal(contact, value));
                  }
                },
                error -> emitter.onError(new IllegalStateException("Could not find Contact for given transaction"))
            );
      }

      emitter.onComplete();
    });
  }

  private void onReceiveTransactionTotal(List<ContactTransactionTotal> contactTransactionTotals) {
    view.showTransactions(contactTransactionTotals);
  }

  private void onError(Throwable error) {
    Log.e(TAG, "List transactions error", error);
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {

  }
}
