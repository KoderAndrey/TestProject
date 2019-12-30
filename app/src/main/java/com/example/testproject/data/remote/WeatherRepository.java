package com.example.testproject.data.remote;

import com.example.testproject.data.remote.api.WeatherList;

import javax.inject.Inject;

import io.reactivex.Single;

public class WeatherRepository {

    private final String mAppId = "3d237f03ecf1138ecf2075ad6796f1d3";

    private WeatherService mService;

    @Inject
    public WeatherRepository(WeatherService weatherService) {
        mService = weatherService;
    }

    public Single<WeatherList> getWeather(double lat, double lon) {
        return mService.getWeather(lat, lon, mAppId);
    }
}
