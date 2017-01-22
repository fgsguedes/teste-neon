package com.fgsguedes.testeneon.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fgsguedes.testeneon.contract.SendMoneyContract;
import com.fgsguedes.testeneon.data.repository.TokenRepository;

public class SendMoneyPresenter implements SendMoneyContract.Presenter {

  private final TokenRepository tokenRepository;
  private SendMoneyContract.View view;

  public SendMoneyPresenter(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  @Override
  public void bindView(SendMoneyContract.View view) {
    this.view = view;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {

  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {

  }
}
