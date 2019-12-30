package com.example.testproject.view.presentation.requstlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.testproject.R;
import com.example.testproject.data.local.RequstData;
import com.example.testproject.view.presentation.requstlist.adapter.ListRequestsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;
import dagger.android.AndroidInjection;

public class ListRequestsActivity extends MvpAppCompatActivity implements ListRequestsView, ListRequestsAdapter.OnRequestClickListener {

    public static final int REQUEST_CODE = 11;
    public static final String LATITUDE = "lat";
    public static final String LONGTITUDE = "longt";

    @InjectPresenter
    ListRequestsPresenter mRequestsPresenter;
    @Inject
    Lazy<ListRequestsPresenter> mDaggerPresenter;

    @BindView(R.id.request_list)
    RecyclerView mRequstList;

    private ListRequestsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_requests);
        ButterKnife.bind(this);
        mAdapter = new ListRequestsAdapter(this);
        mRequstList.setAdapter(mAdapter);
        mRequestsPresenter.getListFromDatabase();
    }

    @Override
    public void getDataList(List<RequstData> list) {
        mAdapter.setWeatherItems(list);
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this, getString(R.string.error) + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @ProvidePresenter
    ListRequestsPresenter providePresenter() {
        return mDaggerPresenter.get();
    }

    @Override
    public void onClickRequest(double lat, double longt) {
        Intent intent = new Intent();
        intent.putExtra(LATITUDE, lat);
        intent.putExtra(LONGTITUDE, longt);
        setResult(RESULT_OK, intent);
        finish();
    }
}
