package com.example.testproject.view.presentation.requstlist;

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
import com.example.testproject.view.presentation.map.MapView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class ListRequestsPresenter extends MvpPresenter<ListRequestsView> {

    private RequstDao mRequstDao;

    @Inject
    ListRequestsPresenter(RequstDao requstDao, @AppContext Context context) {
        mRequstDao = requstDao;
    }

    void getListFromDatabase() {
        mRequstDao
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(requstData -> getViewState().getDataList(requstData),
                        throwable -> getViewState().noError(throwable));
    }
}
