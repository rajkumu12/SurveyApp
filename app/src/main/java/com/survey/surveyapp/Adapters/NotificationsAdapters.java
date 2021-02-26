package com.survey.surveyapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.survey.surveyapp.Models.DailyReportModel;
import com.survey.surveyapp.Models.Data;
import com.survey.surveyapp.Models.GetNotificationsModel;
import com.survey.surveyapp.Models.NotificationsModel;
import com.survey.surveyapp.R;

import java.util.List;

public  class NotificationsAdapters extends  RecyclerView.Adapter<NotificationsAdapters.ViewHolder> {

    private Context context;
    private List<Data> arrayList;

    public NotificationsAdapters(Context context, List<Data> arrayList) {
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
        Data data=arrayList.get(position);

        holder.tv_text.setText(data.getMessage());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_text=itemView.findViewById(R.id.image_message_notification);

        }
    }
}


