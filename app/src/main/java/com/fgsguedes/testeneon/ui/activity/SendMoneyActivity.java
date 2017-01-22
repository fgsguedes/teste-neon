package com.fgsguedes.testeneon.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fgsguedes.testeneon.App;
import com.fgsguedes.testeneon.R;
import com.fgsguedes.testeneon.contract.SendMoneyContract;
import com.fgsguedes.testeneon.model.Contact;
import com.fgsguedes.testeneon.ui.adapter.ContactsAdapter;

import javax.inject.Inject;

public class SendMoneyActivity extends AppCompatActivity implements SendMoneyContract.View {

  @Inject
  SendMoneyContract.Presenter presenter;

  private RecyclerView contactsRecycler;
  private LinearLayoutManager linearLayoutManager;
  private ContactsAdapter contactsAdapter;

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

  private void setUpUi() {
    linearLayoutManager = new LinearLayoutManager(this);
    contactsAdapter = new ContactsAdapter(this);

    contactsRecycler = ((RecyclerView) findViewById(R.id.recycler_view_contacts));
    contactsRecycler.setLayoutManager(linearLayoutManager);
    contactsRecycler.setAdapter(contactsAdapter);
  }
}
