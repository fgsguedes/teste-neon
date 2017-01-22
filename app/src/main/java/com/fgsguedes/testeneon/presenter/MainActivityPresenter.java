package com.fgsguedes.testeneon.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fgsguedes.testeneon.contract.MainActivityContract;

public class MainActivityPresenter implements MainActivityContract.Presenter {

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
    repository.getToken();
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
