package com.fgsguedes.testeneon.data.repository;

import com.fgsguedes.testeneon.contract.MainActivityContract;
import com.fgsguedes.testeneon.data.api.NeonApi;

import io.reactivex.Single;

public class MainActivityRepository implements MainActivityContract.Repository {

  private final NeonApi neonApi;

  public MainActivityRepository(NeonApi neonApi) {
    this.neonApi = neonApi;
  }

  @Override
  public Single<String> generateToken() {
    return neonApi.generateToken("Filipe Guedes", "fgs.guedes@gmail.com");
  }
}
