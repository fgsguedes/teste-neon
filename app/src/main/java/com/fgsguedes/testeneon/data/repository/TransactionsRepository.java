package com.fgsguedes.testeneon.data.repository;

import com.fgsguedes.testeneon.data.api.NeonApi;
import com.fgsguedes.testeneon.data.api.SchedulerComposer;
import com.fgsguedes.testeneon.model.Contact;

import io.reactivex.Completable;

public class TransactionsRepository {

  private final NeonApi neonApi;
  private final TokenRepository tokenRepository;
  private final SchedulerComposer composer;

  public TransactionsRepository(NeonApi neonApi, TokenRepository tokenRepository, SchedulerComposer composer) {
    this.neonApi = neonApi;
    this.tokenRepository = tokenRepository;
    this.composer = composer;
  }

  public Completable sendMoney(Contact contact, double amount) {
    return tokenRepository.getToken()
        .flatMapCompletable(token ->
            neonApi.sendMoney(token, contact.id, amount)
                .compose(composer.completableTransformer())
        );
  }
}