package com.fgsguedes.testeneon.di.module;

import com.fgsguedes.testeneon.data.api.NeonApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkingModule {

  private OkHttpClient createHttpClient() {
    return new OkHttpClient.Builder()
        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build();
  }

  @Provides
  @Singleton
  public Retrofit providesRetrofit() {
    return new Retrofit.Builder()
        .client(createHttpClient())
        .baseUrl("http://processoseletivoneon.azurewebsites.net/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Provides
  @Singleton
  public NeonApi provideNeonApi(Retrofit retrofit) {
    return retrofit.create(NeonApi.class);
  }
}
