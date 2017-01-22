package com.fgsguedes.testeneon.contract;

import com.fgsguedes.testeneon.base.BasePresenter;

import io.reactivex.Single;

public interface MainActivityContract {

  interface Presenter extends BasePresenter<View> {
    void sendMoneyClicked();
    void transactionsClicked();
  }

  interface View {
    void showButtons();
    void navigateToContacts();
    void navigateToTransactions();
  }

  interface Repository {
    Single<String> generateToken();
    Single<String> getToken();
  }
}
