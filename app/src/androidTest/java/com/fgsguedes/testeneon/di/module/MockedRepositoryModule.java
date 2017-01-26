package com.fgsguedes.testeneon.di.module;

import com.fgsguedes.testeneon.data.api.NeonApi;
import com.fgsguedes.testeneon.data.api.SchedulerComposer;
import com.fgsguedes.testeneon.data.repository.TokenRepository;
import com.fgsguedes.testeneon.data.repository.TransactionsRepository;

import org.mockito.Mockito;

public class MockedRepositoryModule extends RepositoryModule {

  @Override
  public TransactionsRepository providesTransactionsRepository(NeonApi neonApi, TokenRepository tokenRepository, SchedulerComposer composer) {
    return Mockito.spy(
        super.providesTransactionsRepository(neonApi, tokenRepository, composer)
    );
  }
}
