package com.fgsguedes.testeneon.contract;

import com.fgsguedes.testeneon.base.BasePresenter;

public interface MainActivityContract {

  interface Presenter extends BasePresenter<View> {
    void sendMoneyClicked();
    void transactionsClicked();
  }

  interface View {
    void showButtons();
    void navigateToSendMoney();
    void navigateToTransactions();
  }
}
