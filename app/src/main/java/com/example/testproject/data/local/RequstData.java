package com.example.testproject.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RequstData {
    private double lat;
    private double longt;
    private String address;
    private long time;

    @PrimaryKey(autoGenerate = true)
    private int number;

    public RequstData() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "RequstData{" +
                "lat=" + lat +
                ", longt=" + longt +
                ", address='" + address + '\'' +
                ", number=" + number +
                '}';
    }
}
