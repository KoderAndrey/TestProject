package com.example.testproject.data.remote.api;

import java.util.List;

public class WeatherGeneral {
    private long dt;
    private WeatherMain main;
    private List<WeatherData> weather;

    public WeatherGeneral(long dt, WeatherMain main, List<WeatherData> weather) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
    }

    public WeatherGeneral() {
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }

    public void setWeather(List<WeatherData> weather) {
        this.weather = weather;
    }

    public List<WeatherData> getWeather() {
        return weather;
    }
}
