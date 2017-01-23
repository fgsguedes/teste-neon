package com.fgsguedes.testeneon.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.fgsguedes.testeneon.App;
import com.fgsguedes.testeneon.R;
import com.fgsguedes.testeneon.contract.SendMoneyContract;
import com.fgsguedes.testeneon.model.Contact;
import com.fgsguedes.testeneon.ui.adapter.ContactsAdapter;
import com.fgsguedes.testeneon.ui.dialog.SendMoneyDialog;

import javax.inject.Inject;

public class SendMoneyActivity extends AppCompatActivity implements SendMoneyContract.View, SendMoneyDialog.SendMoneyCallback {

  @Inject
  SendMoneyContract.Presenter presenter;

  private RecyclerView contactsRecycler;
  private LinearLayoutManager linearLayoutManager;
  private ContactsAdapter contactsAdapter;

  private SendMoneyDialog sendMoneyDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_send_money);

    setUpInjector();
    setUpUi();
    initPresenter(savedInstanceState);
  }

  private void setUpInjector() {
    ((App) getApplicationContext())
        .getApplicationComponent()
        .inject(this);
  }

  private void initPresenter(@Nullable Bundle savedInstanceState) {
    presenter.bindView(this);
    presenter.onCreate(savedInstanceState);
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    presenter.onSaveInstanceState(outState);
  }

  @Override
  public void showContact(@NonNull Contact contact) {
    contactsAdapter.addItem(contact);
  }

  @Override
  public void showAmountPrompt(@NonNull Contact contact) {
    sendMoneyDialog = SendMoneyDialog.newInstance(contact);
    sendMoneyDialog.show(getSupportFragmentManager(), SendMoneyDialog.class.getSimpleName());
  }

  @Override
  public void showInvalidValueWarning() {
    Toast.makeText(this, R.string.invalid_transaction_value, Toast.LENGTH_LONG).show();
  }

  @Override
  public void notifyTransactionSent() {
    Snackbar.make(contactsRecycler, R.string.transaction_sent, Snackbar.LENGTH_LONG)
        .setAction(android.R.string.ok, v -> {})
        .show();
  }

  @Override
  public void notifyTransactionError() {
    Snackbar.make(contactsRecycler, R.string.transaction_error, Snackbar.LENGTH_LONG)
        .setAction(android.R.string.ok, v -> {})
        .show();
  }

  @Override
  public void hideDialogs() {
    sendMoneyDialog.dismissAllowingStateLoss();
  }

  @Override
  public void onReceivedTransactionValue(Contact contact, double value) {
    presenter.onReceivedTransactionValue(contact, value);
  }

  private void setUpUi() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ActionBar supportActionBar = getSupportActionBar();
    if (supportActionBar != null) {
      supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    linearLayoutManager = new LinearLayoutManager(this);
    contactsAdapter = new ContactsAdapter(this);

    contactsRecycler = ((RecyclerView) findViewById(R.id.recycler_view_contacts));
    contactsRecycler.setLayoutManager(linearLayoutManager);
    contactsRecycler.setAdapter(contactsAdapter);

    contactsAdapter.setOnClickListener(this::listItemClicked);
  }

  private void listItemClicked(View view) {
    long childItemId = contactsRecycler.getChildItemId(view);
    presenter.contactClicked(childItemId);
  }
}
