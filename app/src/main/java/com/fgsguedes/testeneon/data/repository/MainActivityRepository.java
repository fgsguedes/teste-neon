package com.fgsguedes.testeneon.data.repository;

import com.fgsguedes.testeneon.contract.MainActivityContract;
import com.fgsguedes.testeneon.data.api.NeonApi;
import com.fgsguedes.testeneon.data.api.SchedulerComposer;

import io.reactivex.Single;

public class MainActivityRepository implements MainActivityContract.Repository {

  private final NeonApi neonApi;
  private final SchedulerComposer composer;

  public MainActivityRepository(NeonApi neonApi, SchedulerComposer composer) {
    this.neonApi = neonApi;
    this.composer = composer;
  }

  @Override
  public Single<String> generateToken() {
    return neonApi.generateToken("Filipe Guedes", "fgs.guedes@gmail.com")
        .compose(composer.singleTransformer());
  }
}
