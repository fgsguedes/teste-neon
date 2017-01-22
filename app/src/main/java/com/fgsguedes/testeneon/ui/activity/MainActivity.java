package com.fgsguedes.testeneon.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fgsguedes.testeneon.R;
import com.fgsguedes.testeneon.contract.MainActivityContract;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainActivityContract.View {

  private MainActivityContract.Presenter presenter;

  private Button sendMoneyButton;
  private Button transactionHistory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    bindUiElements();
    initPresenter(savedInstanceState);
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
  public void onClick(View v) {

    switch (v.getId()) {

      case R.id.button_send_money:
        presenter.sendMoneyClicked();
        break;

      case R.id.button_transaction_history:
        presenter.transactionsClicked();
        break;
    }
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

  private void bindUiElements() {
    sendMoneyButton = ((Button) findViewById(R.id.button_send_money));
    transactionHistory = ((Button) findViewById(R.id.button_transaction_history));

    sendMoneyButton.setOnClickListener(this);
    transactionHistory.setOnClickListener(this);
  }
}
