package com.example.testproject.di.module;


import com.example.testproject.view.presentation.map.MapsActivity;
import com.example.testproject.view.presentation.map.WeatherInfoDialog;
import com.example.testproject.view.presentation.requstlist.ListRequestsActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidInjectionModule.class, AndroidSupportInjectionModule.class})
public abstract class AppBindingModule {

    @ContributesAndroidInjector
    abstract MapsActivity bindMapsActivity();

    @ContributesAndroidInjector
    abstract WeatherInfoDialog contributeWeatherInfoDialog();

    @ContributesAndroidInjector
    abstract ListRequestsActivity contributeListRequestsActivity();

}
