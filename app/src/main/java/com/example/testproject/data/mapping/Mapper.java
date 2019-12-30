package com.example.testproject.data.mapping;

import com.example.testproject.data.remote.api.WeatherList;
import com.example.testproject.data.model.WeatherDisplayData;

import java.util.ArrayList;

public class Mapper {

    public static ArrayList<WeatherDisplayData> getDisplayDataList(WeatherList weatherList){
        ArrayList<WeatherDisplayData> weatherDisplayData = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            WeatherDisplayData displayData = new WeatherDisplayData();
            displayData.setDate(weatherList.getList().get(i).getDt());
            displayData.setHumidity(weatherList.getList().get(i).getMain().getHumidity());
            displayData.setPressure(weatherList.getList().get(i).getMain().getPressure());
            displayData.setDescription(weatherList.getList().get(i).getWeather().get(0).getDescription());
            weatherDisplayData.add(displayData);
        }
        return weatherDisplayData;
    }
}
