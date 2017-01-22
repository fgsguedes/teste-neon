package com.fgsguedes.testeneon.di;

import com.fgsguedes.testeneon.di.module.PresenterModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    dependencies = {
        PresenterModule.class
    }
)
interface ApplicationComponent {
}
