package com.fgsguedes.testeneon.data.repository;

import io.reactivex.Single;

public interface TokenRepository {
  Single<String> generateToken();
  Single<String> getToken();
}

