package com.fgsguedes.testeneon.di;

import com.fgsguedes.testeneon.di.module.AndroidModule;
import com.fgsguedes.testeneon.di.module.NetworkingModule;
import com.fgsguedes.testeneon.di.module.PresenterModule;
import com.fgsguedes.testeneon.di.module.RepositoryModule;
import com.fgsguedes.testeneon.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {
        AndroidModule.class,
        NetworkingModule.class,
        RepositoryModule.class,
        PresenterModule.class
    }
)
public interface ApplicationComponent {
  void inject(MainActivity mainActivity);
}
