package com.fgsguedes.testeneon.contract;

import android.support.annotation.NonNull;

import com.fgsguedes.testeneon.base.BasePresenter;
import com.fgsguedes.testeneon.model.Contact;

public interface SendMoneyContract {

  interface Presenter extends BasePresenter<View> {

  }

  interface View {
    void showContact(@NonNull Contact contact);
  }
}
