package com.fgsguedes.testeneon.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fgsguedes.testeneon.R;
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

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Contact contact = getArguments().getParcelable(CONTACT_BUNDLE_KEY);

    View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_send_money, null, false);
    EditText editText = (EditText) view.findViewById(R.id.edit_text_transaction_value);

    if (contact != null) {
      ((TextView) view.findViewById(R.id.text_contact_name)).setText(contact.name);
      ((TextView) view.findViewById(R.id.text_contact_phone_number)).setText(contact.phoneNumber);

    } else {
      dismissAllowingStateLoss();
    }

    return new AlertDialog.Builder(getActivity())
        .setView(view)
        .setPositiveButton(R.string.send, (dialog, which) -> Log.e("lala", editText.getText().toString()))
        .create();
  }
}
