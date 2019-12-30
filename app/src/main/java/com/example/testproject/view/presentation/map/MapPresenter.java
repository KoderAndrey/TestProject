package com.example.testproject.view.presentation.map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.testproject.AppContext;
import com.example.testproject.data.local.RequstDao;
import com.example.testproject.data.local.RequstData;
import com.example.testproject.data.mapping.Mapper;
import com.example.testproject.data.remote.WeatherRepository;
import com.example.testproject.data.remote.api.WeatherList;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MapPresenter extends MvpPresenter<MapView> {
    private static final String UNKNOW = "Unknow";

    private WeatherRepository mRepository;
    private RequstDao mRequstDao;
    private Geocoder mGeocoder;

    @Inject
    MapPresenter(WeatherRepository repository, RequstDao requstDao, @AppContext Context context) {
        mRepository = repository;
        mRequstDao = requstDao;
        mGeocoder = new Geocoder(context, Locale.getDefault());
    }

    void onMapClicked(LatLng latLng) {
        getViewState().onStartLoad();
        mRepository
                .getWeather(latLng.latitude, latLng.longitude)
                .flatMap((Function<WeatherList, SingleSource<WeatherList>>) weatherList ->
                        mRequstDao.insert(getRequstData(latLng)).toSingleDefault(weatherList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listWeather -> getViewState().getWeather(Mapper.getDisplayDataList(listWeather)),
                        throwable -> getViewState().noError(throwable));
    }

    private RequstData getRequstData(LatLng latLng) {
        RequstData requstData = new RequstData();
        requstData.setLat(latLng.latitude);
        requstData.setLongt(latLng.longitude);
        requstData.setAddress(getAddressFromLocation(latLng.latitude, latLng.longitude));
        requstData.setTime(System.currentTimeMillis());
        return requstData;
    }

    private String getAddressFromLocation(double lat, double longit) {
        try {
            List<Address> addresses = mGeocoder.getFromLocation(lat, longit, 1);
            String locality = (addresses.get(0).getLocality() == null) ? UNKNOW : addresses.get(0).getLocality();
            String countryName = (addresses.get(0).getCountryName() == null) ? UNKNOW : addresses.get(0).getCountryName();
            return locality + ", " + countryName + ".";
        } catch (Exception e) {
            e.printStackTrace();
            return UNKNOW + ", " + UNKNOW + ".";
        }

    }
}
