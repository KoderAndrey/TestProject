package com.example.testproject.view.presentation.map;

import com.arellomobile.mvp.MvpView;
import com.example.testproject.data.local.RequstData;
import com.example.testproject.data.model.WeatherDisplayData;

import java.util.ArrayList;
import java.util.List;

public interface MapView extends MvpView {
  void getWeather(ArrayList<WeatherDisplayData> weatherList);
  void noError(Throwable throwable);
  void onStartLoad();
}
