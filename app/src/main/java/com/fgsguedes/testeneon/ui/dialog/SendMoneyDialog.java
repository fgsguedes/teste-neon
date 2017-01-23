package com.fgsguedes.testeneon.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fgsguedes.testeneon.R;
import com.fgsguedes.testeneon.model.Contact;

public class SendMoneyDialog extends DialogFragment {

  private static final String TAG = SendMoneyDialog.class.getSimpleName();

  private static final String CONTACT_BUNDLE_KEY = "contact";

  public static SendMoneyDialog newInstance(
      @NonNull Contact contact
  ) {

    Bundle bundle = new Bundle();
    bundle.putParcelable(CONTACT_BUNDLE_KEY, contact);

    SendMoneyDialog sendMoneyDialog = new SendMoneyDialog();
    sendMoneyDialog.setArguments(bundle);

    return sendMoneyDialog;
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
      view.findViewById(R.id.button_send_money).setOnClickListener(v -> onSendClicked(editText, contact));

    } else {
      dismissAllowingStateLoss();
    }

    return new AlertDialog.Builder(getActivity())
        .setView(view)
        .create();
  }

  @Override
  public void onResume() {
    super.onResume();

    ((AlertDialog) getDialog())
        .getButton(DialogInterface.BUTTON_POSITIVE)
        .setEnabled(false);
  }

  private void onSendClicked(EditText editText, Contact contact) {

    String inputValue = editText.getText().toString();

    double value = 0;
    if (inputValue.length() > 0) {
      value = Double.parseDouble(inputValue);
    }

    ((SendMoneyCallback) getActivity()).onReceivedTransactionValue(contact, value);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    if (!(context instanceof SendMoneyCallback)) {
      throw new IllegalStateException("Activity must implement SendMoneyCallback");
    }
  }

  public interface SendMoneyCallback {
    void onReceivedTransactionValue(Contact contact, double value);
  }
}
