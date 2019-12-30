package com.example.testproject.di;

import android.app.Application;
import android.content.Context;

import com.example.testproject.AppContext;
import com.example.testproject.MapsApplication;
import com.example.testproject.di.module.AppBindingModule;
import com.example.testproject.di.module.DatabaseModule;
import com.example.testproject.di.module.NetworkModule;
import com.example.testproject.di.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

@Singleton
@Component(modules = {AppBindingModule.class, RepositoryModule.class, DatabaseModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(MapsApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(MapsApplication application);

        @BindsInstance
        Builder applicationContext(@AppContext  Context context);

        AppComponent build();
    }

}
