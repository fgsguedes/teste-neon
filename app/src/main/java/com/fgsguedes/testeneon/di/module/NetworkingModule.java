package com.fgsguedes.testeneon.di.module;

import com.fgsguedes.testeneon.data.api.NeonApi;
import com.fgsguedes.testeneon.data.api.SchedulerComposer;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkingModule {

  @Provides
  @Singleton
  public OkHttpClient providesOkHttpClient() {
    return new OkHttpClient.Builder()
        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build();
  }

  @Provides
  @Singleton
  @Named("baseUrl")
  public String provideBaseUrl() {
    return "http://processoseletivoneon.azurewebsites.net/";
  }

  @Provides
  @Singleton
  public Retrofit providesRetrofit(OkHttpClient client, @Named("baseUrl") String baseUrl) {
    return new Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Provides
  @Singleton
  public NeonApi provideNeonApi(Retrofit retrofit) {
    return retrofit.create(NeonApi.class);
  }

  @Provides
  @Singleton
  public SchedulerComposer provideSchedulerComposer() {
    return new SchedulerComposer();
  }
}
