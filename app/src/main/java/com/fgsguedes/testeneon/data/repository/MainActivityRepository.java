package com.fgsguedes.testeneon.data.repository;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.fgsguedes.testeneon.contract.MainActivityContract;
import com.fgsguedes.testeneon.data.api.NeonApi;
import com.fgsguedes.testeneon.data.api.SchedulerComposer;

import io.reactivex.Single;

public class MainActivityRepository implements MainActivityContract.Repository {

  private final String TAG = getClass().getSimpleName();

  private final NeonApi neonApi;
  private final SchedulerComposer composer;
  private final SharedPreferences preferences;

  public MainActivityRepository(NeonApi neonApi, SchedulerComposer composer, SharedPreferences preferences) {
    this.neonApi = neonApi;
    this.composer = composer;
    this.preferences = preferences;
  }

  @Override
  public Single<String> generateToken() {
    String token = preferences.getString("token", null);

    if (!TextUtils.isEmpty(token)) {
      Log.e(TAG, "Cached -> " + token);
      return Single.just(token);
    }

    return neonApi.generateToken("Filipe Guedes", "fgs.guedes@gmail.com")
        .compose(composer.singleTransformer())
        .doOnSuccess(this::persistToken);
  }

  private void persistToken(String token) {
    Log.e(TAG, "Caching -> " + token);
    preferences.edit()
        .putString("token", token)
        .apply();
  }
}
