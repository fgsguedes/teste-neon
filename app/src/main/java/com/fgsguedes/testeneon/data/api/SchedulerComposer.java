package com.fgsguedes.testeneon.data.api;

import io.reactivex.CompletableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerComposer {

  public <T> SingleTransformer<T, T> singleIo() {
    return upstream -> upstream.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public <T> SingleTransformer<T, T> singleComputation() {
    return upstream -> upstream.subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public CompletableTransformer completableIo() {
    return upstream -> upstream.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
