package com.example.testproject.view.presentation.requstlist;

import com.arellomobile.mvp.MvpView;
import com.example.testproject.data.local.RequstData;

import java.util.List;

public interface ListRequestsView extends MvpView {
    void getDataList(List<RequstData> list);
    void noError(Throwable throwable);
}
