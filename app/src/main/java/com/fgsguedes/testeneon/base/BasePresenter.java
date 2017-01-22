package com.fgsguedes.testeneon.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface BasePresenter<T> {

  void onCreate(@Nullable Bundle savedInstanceState);
  void onSaveInstanceState(@NonNull Bundle outState);
  void bindView(T t);
}
