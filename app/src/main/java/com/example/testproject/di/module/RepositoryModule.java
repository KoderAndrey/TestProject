package com.example.testproject.di.module;

import com.example.testproject.data.local.RequestDatabase;
import com.example.testproject.data.remote.WeatherRepository;
import com.example.testproject.data.remote.WeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = NetworkModule.class)
public class RepositoryModule {

    @Singleton
    @Provides
    WeatherRepository provideWeatherRepository(WeatherService service) {
        return new WeatherRepository(service);
    }
}
