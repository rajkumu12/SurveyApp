package com.survey.surveyapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.survey.surveyapp.Models.DailyReportModel;
import com.survey.surveyapp.Models.NotificationsModel;
import com.survey.surveyapp.R;

import java.util.List;

public  class NotificationsAdapters extends  RecyclerView.Adapter<NotificationsAdapters.ViewHolder> {

    private Context context;
    private List<NotificationsModel> arrayList;

    public NotificationsAdapters(Context context, List<NotificationsModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public NotificationsAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_item_view, parent, false);
        return new NotificationsAdapters.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationsAdapters.ViewHolder holder, final int position) {

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


