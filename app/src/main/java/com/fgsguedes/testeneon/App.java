package com.fgsguedes.testeneon;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fgsguedes.testeneon.di.ApplicationComponent;
import com.fgsguedes.testeneon.di.DaggerApplicationComponent;

public class App extends Application {

  private ApplicationComponent applicationComponent;

  public void onCreate() {
    super.onCreate();
    applicationComponent = DaggerApplicationComponent.create();
  }

  @NonNull
  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
