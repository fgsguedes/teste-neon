package com.fgsguedes.testeneon;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fgsguedes.testeneon.di.ApplicationComponent;
import com.fgsguedes.testeneon.di.DaggerApplicationComponent;
import com.fgsguedes.testeneon.di.module.AndroidModule;

public class App extends Application {

  private ApplicationComponent applicationComponent;

  public void onCreate() {
    super.onCreate();
    applicationComponent = DaggerApplicationComponent.builder()
        .androidModule(new AndroidModule(this))
        .build();
  }

  @NonNull
  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
