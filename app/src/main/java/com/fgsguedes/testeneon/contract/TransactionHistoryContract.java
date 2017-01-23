package com.fgsguedes.testeneon.contract;

import android.support.annotation.NonNull;

import com.fgsguedes.testeneon.base.BasePresenter;
import com.fgsguedes.testeneon.model.X;

import java.util.List;

public interface TransactionHistoryContract {

  interface Presenter extends BasePresenter<View> {
  }

  interface View {
    void showTransactions(@NonNull List<X> xes);
  }
}
