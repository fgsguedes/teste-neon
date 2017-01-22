package com.fgsguedes.testeneon.di.module;

import com.fgsguedes.testeneon.contract.MainActivityContract;
import com.fgsguedes.testeneon.presenter.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

  @Provides
  @Singleton
  public MainActivityContract.Presenter providesMainActivityPresenter(
      MainActivityContract.Repository repository
  ) {
    return new MainActivityPresenter(repository);
  }
}
