package com.fgsguedes.testeneon.data.api;

import android.support.annotation.NonNull;

import com.fgsguedes.testeneon.model.Transfer;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NeonApi {

  @GET
  Single<String> generateToken(
      @Query("nome") @NonNull String name,
      @Query("email") @NonNull String email
  );

  @POST
  Completable sendMoney(
      @Field("token") @NonNull String token,
      @Field("ClienteId") @NonNull String contactId,
      @Field("valor") double value
  );

  @GET
  Single<List<Transfer>> getTransfers(
      @Query("token") @NonNull String token
  );
}
