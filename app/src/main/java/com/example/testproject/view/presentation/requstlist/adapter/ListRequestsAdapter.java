package com.example.testproject.view.presentation.requstlist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testproject.R;
import com.example.testproject.data.local.RequstData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.testproject.data.util.DateUtil.formatLongDate;

public class ListRequestsAdapter extends RecyclerView.Adapter<ListRequestsAdapter.ListRequestsHolder> {

    private List<RequstData> mRequstData;
    private OnRequestClickListener mListener;

    public ListRequestsAdapter(OnRequestClickListener listener) {
        mRequstData = new ArrayList<>();
        mListener = listener;
    }

    @NonNull
    @Override
    public ListRequestsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListRequestsHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListRequestsHolder holder, int position) {
        holder.bind(mRequstData.get(position));
    }

    @Override
    public int getItemCount() {
        return mRequstData.size();
    }

    class ListRequestsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.number_content)
        TextView numberContent;
        @BindView(R.id.date_content)
        TextView dateContent;
        @BindView(R.id.location_content)
        TextView locationContent;

        ListRequestsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(RequstData data) {
            numberContent.setText(String.valueOf(data.getNumber()));
            dateContent.setText(formatLongDate(data.getTime()));
            locationContent.setText(data.getAddress());
            itemView.setOnClickListener(view -> mListener.onClickRequest(data.getLat(), data.getLongt()));
        }
    }

    public void setWeatherItems(List<RequstData> requstData) {
        mRequstData.clear();
        mRequstData.addAll(requstData);
        notifyDataSetChanged();
    }

    public interface OnRequestClickListener {
        void onClickRequest(double lat, double longt);
    }
}