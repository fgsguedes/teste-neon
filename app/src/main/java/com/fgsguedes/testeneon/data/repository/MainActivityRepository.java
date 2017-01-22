package com.fgsguedes.testeneon.data.repository;

import com.fgsguedes.testeneon.contract.MainActivityContract;
import com.fgsguedes.testeneon.data.api.NeonApi;

import io.reactivex.Observable;

public class MainActivityRepository implements MainActivityContract.Repository {

  private final NeonApi neonApi;

  public MainActivityRepository(NeonApi neonApi) {
    this.neonApi = neonApi;
  }

  @Override
  public Observable<String> generateToken() {
    return neonApi.generateToken("Filipe Guedes", "fgs.guedes@gmail.com");
  }
}
