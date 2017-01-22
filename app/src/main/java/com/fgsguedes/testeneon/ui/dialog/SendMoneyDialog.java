package com.fgsguedes.testeneon.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.fgsguedes.testeneon.model.Contact;

public class SendMoneyDialog extends DialogFragment {

  private static final String TAG = SendMoneyDialog.class.getSimpleName();

  private static final String CONTACT_BUNDLE_KEY = "contact";

  public static void show(
      @NonNull FragmentManager fragmentManager,
      @NonNull Contact contact
  ) {

    Bundle bundle = new Bundle();
    bundle.putParcelable(CONTACT_BUNDLE_KEY, contact);

    SendMoneyDialog sendMoneyDialog = new SendMoneyDialog();
    sendMoneyDialog.setArguments(bundle);

    sendMoneyDialog.show(fragmentManager, TAG);
  }
}
