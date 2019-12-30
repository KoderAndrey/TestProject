package com.example.testproject.data.remote.api;

public class WeatherMain {
    private long humidity;
    private long pressure;

    public WeatherMain() {
    }

    public WeatherMain(long humidity, long pressure) {
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public long getPressure() {
        return pressure;
    }

    public void setPressure(long pressure) {
        this.pressure = pressure;
    }
}
