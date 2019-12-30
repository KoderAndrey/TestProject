package com.example.testproject.view.presentation.map.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testproject.R;
import com.example.testproject.data.model.WeatherDisplayData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.testproject.data.util.DateUtil.formatLongDateMultipliedBThousandy;


public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<WeatherDisplayData> mDisplayDataList;

    public WeatherAdapter() {
        mDisplayDataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeatherViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        holder.bind(mDisplayDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDisplayDataList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date_content)
        TextView mDateContent;
        @BindView(R.id.humidity_content)
        TextView mHumidityContentt;
        @BindView(R.id.pressure_content)
        TextView mPressureContent;
        @BindView(R.id.weather_content)
        TextView mWeatherContent;

        WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(WeatherDisplayData data) {
            mDateContent.setText(formatLongDateMultipliedBThousandy(data.getDate()));
            mHumidityContentt.setText(String.valueOf(data.getHumidity()));
            mPressureContent.setText(String.valueOf(data.getPressure()));
            mWeatherContent.setText(data.getDescription());
        }
    }

    public void setWeatherItems(List<WeatherDisplayData> displayData) {
        mDisplayDataList.clear();
        mDisplayDataList.addAll(displayData);
        notifyDataSetChanged();
    }
}