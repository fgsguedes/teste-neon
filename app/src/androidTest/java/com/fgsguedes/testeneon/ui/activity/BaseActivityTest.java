package com.fgsguedes.testeneon.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;

import com.dannyroa.espresso_samples.recyclerview.RecyclerViewMatcher;
import com.fgsguedes.testeneon.App;
import com.fgsguedes.testeneon.di.ApplicationComponent;
import com.fgsguedes.testeneon.di.DaggerApplicationComponent;
import com.fgsguedes.testeneon.di.module.AndroidModule;
import com.fgsguedes.testeneon.di.module.MockedNetworkingModule;
import com.fgsguedes.testeneon.di.module.MockedRepositoryModule;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

class BaseActivityTest {

  void setUp(MockWebServer server) {
    ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
        .androidModule(new AndroidModule(InstrumentationRegistry.getTargetContext()))
        .networkingModule(new MockedNetworkingModule(server))
        .repositoryModule(new MockedRepositoryModule())
        .build();

    ((App) InstrumentationRegistry.getTargetContext().getApplicationContext())
        .setApplicationComponent(applicationComponent);
  }

  RecyclerViewMatcher withRecyclerView(@IdRes int viewId) {
    return new RecyclerViewMatcher(viewId);
  }

  String readFile(String fileName) throws IOException {
    InputStream stream = InstrumentationRegistry.getContext()
        .getAssets()
        .open(fileName);

    Source source = Okio.source(stream);
    BufferedSource buffer = Okio.buffer(source);

    return buffer.readUtf8();
  }

  SharedPreferences getSharedPreferences() {
    Context context = InstrumentationRegistry.getTargetContext();
    return PreferenceManager.getDefaultSharedPreferences(context);
  }

  void clearSharedPreferences() {
    getSharedPreferences()
        .edit()
        .clear()
        .apply();
  }

  ApplicationComponent getApplicationComponent() {
    return ((App) InstrumentationRegistry.getTargetContext().getApplicationContext())
        .getApplicationComponent();
  }
}
