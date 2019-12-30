package com.example.testproject.data.remote;


import com.example.testproject.data.remote.api.WeatherList;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("/data/2.5/forecast")
    Single<WeatherList> getWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String appid);
}