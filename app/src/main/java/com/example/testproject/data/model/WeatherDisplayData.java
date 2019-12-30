package com.example.testproject.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherDisplayData implements Parcelable {
    private long humidity;
    private long pressure;
    private long date;
    private String description;

    public WeatherDisplayData(long humidity, long pressure, long date, String description) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.date = date;
        this.description = description;
    }

    public WeatherDisplayData() {
    }

    protected WeatherDisplayData(Parcel in) {
        humidity = in.readLong();
        pressure = in.readLong();
        date = in.readLong();
        description = in.readString();
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "WeatherDisplayData{" +
                "humidity=" + humidity +
                ", pressure=" + pressure +
                ", date=" + date +
                ", description=" + description +
                "}" +
                '\n';

    }


    public static final Creator<WeatherDisplayData> CREATOR = new Creator<WeatherDisplayData>() {
        @Override
        public WeatherDisplayData createFromParcel(Parcel in) {
            return new WeatherDisplayData(in);
        }

        @Override
        public WeatherDisplayData[] newArray(int size) {
            return new WeatherDisplayData[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(humidity);
        dest.writeLong(pressure);
        dest.writeLong(date);
        dest.writeString(description);
    }
}
