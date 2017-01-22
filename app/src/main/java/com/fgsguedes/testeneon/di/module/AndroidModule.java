package com.fgsguedes.testeneon.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {

  private final Context context;

  public AndroidModule(Context context) {
    this.context = context;
  }

  @Provides
  @Singleton
  public SharedPreferences providesSharedPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }
}
