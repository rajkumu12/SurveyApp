package com.survey.surveyapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.survey.surveyapp.Models.DailyReportModel;
import com.survey.surveyapp.Models.ReportData;
import com.survey.surveyapp.R;
import com.survey.surveyapp.Ui.Activity.ReportsFormActivity;

import java.util.List;

public  class DailyReportAdapter extends  RecyclerView.Adapter<DailyReportAdapter.ViewHolder> {

    private Context context;
    private List<ReportData> arrayList;

    public DailyReportAdapter(Context context, List<ReportData> arrayList) {
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

        ReportData reportData=arrayList.get(position);

        holder.tv_report.setText(reportData.getName());


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ReportsFormActivity.class);
                intent.putExtra("url",reportData.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;

        TextView tv_report;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout=itemView.findViewById(R.id.rly);
            tv_report=itemView.findViewById(R.id.tv_report);

        }
    }
}


