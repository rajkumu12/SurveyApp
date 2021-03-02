package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.survey.surveyapp.Adapters.DailyReportAdapter;
import com.survey.surveyapp.Delegates.ReportsDelegeates;
import com.survey.surveyapp.Models.DailyReportModel;
import com.survey.surveyapp.Models.ReportsModel;
import com.survey.surveyapp.R;
import com.survey.surveyapp.ViewModel.LoginViewModel;
import com.survey.surveyapp.ViewModel.ReportsViewModel;

import java.util.ArrayList;
import java.util.List;

public class DailyReportActivity extends BaseActivity implements ReportsDelegeates {

    private List<DailyReportModel> arrayList;
    RecyclerView dailyreport_list;
    ImageView imageView_back1_rep_daily;
    TextView id_header;
    private ReportsViewModel viewModal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_daily_report);

        dailyreport_list=findViewById(R.id.rec_report);
        imageView_back1_rep_daily=findViewById(R.id.back1_rep_daily);

        id_header=findViewById(R.id.tv_header);
        viewModal = new ReportsViewModel(this,this);
        Intent intent=getIntent();
        String key=intent.getStringExtra("key");

        if (key.equals("Daily Report")){
            id_header.setText(key);
        }else if (key.equals("Weekly Report")){
            id_header.setText(key);
        }else if (key.equals("Monthly Report")){
            id_header.setText(key);
        }else if (key.equals("Yearly Report")){
            id_header.setText(key);
        }


        imageView_back1_rep_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        String userid=appDatabase.userDao().getLoginUser().getUid();
        viewModal.getReportts(userid);
    }

   /* private void loaddata() {

        arrayList=new ArrayList<>();
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));
        arrayList.add(new DailyReportModel("khfsdajdshk"));

        DailyReportAdapter topPicksAdapter = new DailyReportAdapter(DailyReportActivity.this, arrayList);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(DailyReportActivity.this);
        dailyreport_list.setLayoutManager(layoutManager2);
                            *//*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*//*
        dailyreport_list.setItemAnimator(new DefaultItemAnimator());
        dailyreport_list.setAdapter(topPicksAdapter);

    }*/

    @Override
    public void onSucess(ReportsModel reportsModel) {

        DailyReportAdapter topPicksAdapter = new DailyReportAdapter(DailyReportActivity.this, reportsModel.getData());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(DailyReportActivity.this);
        dailyreport_list.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        dailyreport_list.setItemAnimator(new DefaultItemAnimator());
        dailyreport_list.setAdapter(topPicksAdapter);

    }

    @Override
    public void onError(String error) {

    }
}