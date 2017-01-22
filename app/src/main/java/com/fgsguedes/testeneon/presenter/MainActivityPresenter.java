package com.fgsguedes.testeneon.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fgsguedes.testeneon.contract.MainActivityContract;

public class MainActivityPresenter implements MainActivityContract.Presenter {

  private final String TAG = getClass().getSimpleName();

  private final MainActivityContract.Repository repository;

  private MainActivityContract.View view;

  public MainActivityPresenter(MainActivityContract.Repository repository) {
    this.repository = repository;
  }

  @Override
  public void bindView(MainActivityContract.View view) {
    this.view = view;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    repository.generateToken()
        .subscribe(
            this::onReceivedToken,
            this::onTokenGenerationError
        );
  }

  private void onReceivedToken(String token) {
    Log.e(TAG, "Received token: " + token);
    view.showButtons();
  }

  private void onTokenGenerationError(Throwable error) {
    Log.e(TAG, "Token error", error);
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {

  }

  @Override
  public void sendMoneyClicked() {
    view.navigateToContacts();
  }

  @Override
  public void transactionsClicked() {
    view.navigateToTransactions();
  }
}
