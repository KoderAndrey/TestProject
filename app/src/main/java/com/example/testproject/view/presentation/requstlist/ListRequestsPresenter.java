package com.example.testproject.view.presentation.requstlist;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.testproject.AppContext;
import com.example.testproject.data.local.RequstDao;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
                        throwable -> getViewState().onError(throwable));
    }
}
