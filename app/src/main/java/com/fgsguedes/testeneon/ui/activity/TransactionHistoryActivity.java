package com.fgsguedes.testeneon.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.fgsguedes.testeneon.App;
import com.fgsguedes.testeneon.R;
import com.fgsguedes.testeneon.contract.TransactionHistoryContract;
import com.fgsguedes.testeneon.model.X;
import com.fgsguedes.testeneon.ui.adapter.TransactionsAdapter;

import java.util.List;

import javax.inject.Inject;

public class TransactionHistoryActivity extends AppCompatActivity implements TransactionHistoryContract.View {

  @Inject
  TransactionHistoryContract.Presenter presenter;

  private RecyclerView transactionsRecycler;
  private LinearLayoutManager linearLayoutManager;
  private TransactionsAdapter transactionsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transactions);

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
  public void showTransactions(@NonNull List<X> xes) {
    transactionsAdapter.setItems(xes);
  }

  private void setUpUi() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ActionBar supportActionBar = getSupportActionBar();
    if (supportActionBar != null) {
      supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    linearLayoutManager = new LinearLayoutManager(this);
    transactionsAdapter = new TransactionsAdapter(this);

    transactionsRecycler = ((RecyclerView) findViewById(R.id.recycler_view_transactions));
    transactionsRecycler.setLayoutManager(linearLayoutManager);
    transactionsRecycler.setAdapter(transactionsAdapter);
  }
}
