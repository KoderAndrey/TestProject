package com.example.testproject.data.remote.api;

public class WeatherData {
    private String description;

    public WeatherData(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
