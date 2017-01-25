package com.fgsguedes.testeneon.di.module;

import com.fgsguedes.testeneon.contract.MainActivityContract;
import com.fgsguedes.testeneon.contract.SendMoneyContract;
import com.fgsguedes.testeneon.contract.TransactionHistoryContract;
import com.fgsguedes.testeneon.data.api.SchedulerComposer;
import com.fgsguedes.testeneon.data.repository.ContactsRepository;
import com.fgsguedes.testeneon.data.repository.TokenRepository;
import com.fgsguedes.testeneon.data.repository.TransactionsRepository;
import com.fgsguedes.testeneon.presenter.MainActivityPresenter;
import com.fgsguedes.testeneon.presenter.SendMoneyPresenter;
import com.fgsguedes.testeneon.presenter.TransactionHistoryPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

  @Provides
  @Singleton
  public MainActivityContract.Presenter providesMainActivityPresenter(
      TokenRepository repository
  ) {
    return new MainActivityPresenter(repository);
  }

  @Provides
  @Singleton
  public SendMoneyContract.Presenter provideSendMoneyPresenter(
      ContactsRepository contactsRepository,
      TransactionsRepository transactionsRepository
  ) {
    return new SendMoneyPresenter(contactsRepository, transactionsRepository);
  }

  @Provides
  @Singleton
  public TransactionHistoryContract.Presenter provideTransactionHistoryPresenter(
      ContactsRepository contactsRepository,
      TransactionsRepository transactionsRepository,
      SchedulerComposer composer
  ) {
    return new TransactionHistoryPresenter(contactsRepository, transactionsRepository, composer);
  }
}
