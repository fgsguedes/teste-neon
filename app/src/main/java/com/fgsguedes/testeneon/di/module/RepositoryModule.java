package com.fgsguedes.testeneon.di.module;

import com.fgsguedes.testeneon.contract.MainActivityContract;
import com.fgsguedes.testeneon.repository.MainActivityRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

  @Provides
  @Singleton
  public MainActivityContract.Repository providesMainActivityRepository() {
    return new MainActivityRepository();
  }

}
