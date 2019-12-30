package com.example.testproject;


import com.example.testproject.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MapsApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<DaggerApplication> applicationInjector() {

        return DaggerAppComponent.builder()
                .application(this)
                .applicationContext(this)
                .build();
   }
}
