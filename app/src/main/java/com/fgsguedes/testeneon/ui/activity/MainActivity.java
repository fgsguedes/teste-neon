package com.fgsguedes.testeneon.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.fgsguedes.testeneon.App;
import com.fgsguedes.testeneon.R;
import com.fgsguedes.testeneon.contract.MainActivityContract;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

  @Inject
  MainActivityContract.Presenter presenter;

  private Button sendMoneyButton;
  private Button transactionHistory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setUpInjector();
    setUpUi();
    initPresenter(savedInstanceState);
  }

  private void setUpInjector() {
    ((App) getApplicationContext())
        .getApplicationComponent()
        .inject(this);
  }

  private void initPresenter(Bundle savedInstanceState) {
    presenter.bindView(this);
    presenter.onCreate(savedInstanceState);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    presenter.onSaveInstanceState(outState);
  }

  @Override
  public void showButtons() {

  }

  @Override
  public void navigateToContacts() {

  }

  @Override
  public void navigateToTransactions() {

  }

  private void setUpUi() {
    sendMoneyButton = ((Button) findViewById(R.id.button_send_money));
    sendMoneyButton.setOnClickListener((view) -> presenter.sendMoneyClicked());

    transactionHistory = ((Button) findViewById(R.id.button_transaction_history));
    transactionHistory.setOnClickListener((view) -> presenter.transactionsClicked());
  }
}
