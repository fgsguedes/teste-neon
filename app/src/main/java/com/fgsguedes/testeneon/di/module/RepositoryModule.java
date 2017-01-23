package com.fgsguedes.testeneon.di.module;

import android.content.SharedPreferences;

import com.fgsguedes.testeneon.data.api.NeonApi;
import com.fgsguedes.testeneon.data.api.SchedulerComposer;
import com.fgsguedes.testeneon.data.repository.ContactsRepository;
import com.fgsguedes.testeneon.data.repository.TokenRepository;
import com.fgsguedes.testeneon.data.repository.TransactionsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

  @Provides
  @Singleton
  public TokenRepository providesMainActivityRepository(
      NeonApi neonApi,
      SchedulerComposer composer,
      SharedPreferences preferences
  ) {
    return new TokenRepository(neonApi, composer, preferences);
  }

  @Provides
  @Singleton
  public ContactsRepository providesContactRepository() {
    return new ContactsRepository();
  }

  @Provides
  @Singleton
  public TransactionsRepository providesTransactionsRepository(
      NeonApi neonApi,
      TokenRepository tokenRepository,
      SchedulerComposer composer
  ) {
    return new TransactionsRepository(neonApi, tokenRepository, composer);
  }
}
