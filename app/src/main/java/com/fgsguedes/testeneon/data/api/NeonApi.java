package com.fgsguedes.testeneon.data.api;

import android.support.annotation.NonNull;

import com.fgsguedes.testeneon.model.Transfer;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NeonApi {

  @GET
  Observable<String> generateToken(
      @Query("nome") @NonNull String name,
      @Query("email") @NonNull String email
  );

  @POST
  Observable<Boolean> sendMoney(
      @Field("token") @NonNull String token,
      @Field("ClienteId") @NonNull String contactId,
      @Field("valor") double value
  );

  @GET
  Observable<List<Transfer>> getTransfers(
      @Query("token") @NonNull String token
  );
}
