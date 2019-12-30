package com.example.testproject.data.remote.api;

import java.util.List;

public class WeatherList {
    private List<WeatherGeneral> list;

    public WeatherList(List<WeatherGeneral> list) {
        this.list = list;
    }

    public WeatherList() {
    }

    public List<WeatherGeneral> getList() {
        return list;
    }

    public void setList(List<WeatherGeneral> list) {
        this.list = list;
    }
}
