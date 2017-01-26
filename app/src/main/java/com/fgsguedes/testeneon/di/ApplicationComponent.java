package com.fgsguedes.testeneon.di;

import android.support.annotation.VisibleForTesting;

import com.fgsguedes.testeneon.data.repository.TokenRepository;
import com.fgsguedes.testeneon.data.repository.TransactionsRepository;
import com.fgsguedes.testeneon.di.module.AndroidModule;
import com.fgsguedes.testeneon.di.module.NetworkingModule;
import com.fgsguedes.testeneon.di.module.PresenterModule;
import com.fgsguedes.testeneon.di.module.RepositoryModule;
import com.fgsguedes.testeneon.ui.activity.MainActivity;
import com.fgsguedes.testeneon.ui.activity.SendMoneyActivity;
import com.fgsguedes.testeneon.ui.activity.TransactionHistoryActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {
        AndroidModule.class,
        NetworkingModule.class,
        RepositoryModule.class,
        PresenterModule.class
    }
)
public interface ApplicationComponent {
  void inject(MainActivity mainActivity);
  void inject(SendMoneyActivity sendMoneyActivity);
  void inject(TransactionHistoryActivity transactionHistoryActivity);

  @VisibleForTesting
  TransactionsRepository getTransactionsRepository();

  @VisibleForTesting
  TokenRepository getTokenRepository();
}
