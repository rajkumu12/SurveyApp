package com.survey.surveyapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.survey.surveyapp.Models.DailyReportModel;
import com.survey.surveyapp.R;

import java.util.List;

public  class DailyReportAdapter extends  RecyclerView.Adapter<DailyReportAdapter.ViewHolder> {

    private Context context;
    private List<DailyReportModel> arrayList;

    public DailyReportAdapter(Context context, List<DailyReportModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DailyReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dailyreportview, parent, false);
        return new DailyReportAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DailyReportAdapter.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}


