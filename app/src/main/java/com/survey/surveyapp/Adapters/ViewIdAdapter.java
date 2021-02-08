package com.survey.surveyapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.survey.surveyapp.Models.DailyReportModel;
import com.survey.surveyapp.Models.ViewIdModels;
import com.survey.surveyapp.R;

import java.util.List;

public  class ViewIdAdapter extends  RecyclerView.Adapter<ViewIdAdapter.ViewHolder> {

    private Context context;
    private List<ViewIdModels> arrayList;

    public ViewIdAdapter(Context context, List<ViewIdModels> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewIdAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_id_cards, parent, false);
        return new ViewIdAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewIdAdapter.ViewHolder holder, final int position) {

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

