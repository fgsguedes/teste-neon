package com.fgsguedes.testeneon.di.module;

import okhttp3.mockwebserver.MockWebServer;

public class MockedNetworkingModule extends NetworkingModule {

  private final MockWebServer server;

  public MockedNetworkingModule(MockWebServer server) {
    this.server = server;
  }

  @Override
  public String provideBaseUrl() {
    return server.url("/").toString();
  }
}
