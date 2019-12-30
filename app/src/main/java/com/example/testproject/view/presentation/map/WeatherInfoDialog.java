package com.example.testproject.view.presentation.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testproject.R;
import com.example.testproject.data.model.WeatherDisplayData;
import com.example.testproject.view.presentation.map.adapter.WeatherAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerDialogFragment;

public class WeatherInfoDialog extends DaggerDialogFragment {

    private static final String EXTRA_INFO_WEATHER = "weather_model_info";

    private Unbinder mUnbinder;
    private static WeatherInfoDialog mFragment;
    private WeatherAdapter mWeatherAdapter;
    private ArrayList<WeatherDisplayData> mDisplayDataList;

    @BindView(R.id.list_weather)
    RecyclerView mRecyclerViewWeather;

    public static WeatherInfoDialog getInstance(ArrayList<WeatherDisplayData> displayDataList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(EXTRA_INFO_WEATHER, displayDataList);
        if (mFragment == null) {
            mFragment = new WeatherInfoDialog();
        }
        mFragment.setArguments(args);
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_info_dialog, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mWeatherAdapter = new WeatherAdapter();
        mRecyclerViewWeather.setAdapter(mWeatherAdapter);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            mDisplayDataList = getArguments().getParcelableArrayList(EXTRA_INFO_WEATHER);
            mWeatherAdapter.setWeatherItems(mDisplayDataList);
        }
    }

    @OnClick(R.id.close_button)
    void close() {
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

}