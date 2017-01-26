package com.fgsguedes.testeneon.ui.activity;

import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;

import com.dannyroa.espresso_samples.recyclerview.RecyclerViewMatcher;
import com.fgsguedes.testeneon.App;
import com.fgsguedes.testeneon.di.ApplicationComponent;
import com.fgsguedes.testeneon.di.DaggerApplicationComponent;
import com.fgsguedes.testeneon.di.module.AndroidModule;
import com.fgsguedes.testeneon.di.module.MockedNetworkingModule;

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
}
