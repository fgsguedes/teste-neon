package com.fgsguedes.testeneon.contract;

import android.support.annotation.NonNull;

import com.fgsguedes.testeneon.base.BasePresenter;
import com.fgsguedes.testeneon.model.Contact;

import java.util.List;

public interface SendMoneyContract {

  interface Presenter extends BasePresenter<View> {

    void contactClicked(long contactId);
    void onReceivedTransactionValue(Contact contact, double value);
  }

  interface View {
    void showContacts(@NonNull List<Contact> contacts);
    void showAmountPrompt(@NonNull Contact contact);
    void showInvalidValueWarning();
    void notifyTransactionSent();
    void notifyTransactionError();
    void hideDialogs();
  }
}
