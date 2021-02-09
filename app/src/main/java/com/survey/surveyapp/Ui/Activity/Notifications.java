package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.survey.surveyapp.Adapters.DailyReportAdapter;
import com.survey.surveyapp.Adapters.NotificationsAdapters;
import com.survey.surveyapp.Models.DailyReportModel;
import com.survey.surveyapp.Models.NotificationsModel;
import com.survey.surveyapp.R;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity {

    private List<NotificationsModel> arrayList;
    RecyclerView notification_list;
    ImageView imageView_back1_rep_notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_notifications);

        notification_list=findViewById(R.id.recy_notification);
        imageView_back1_rep_notifications=findViewById(R.id.back1_rep_not);
        imageView_back1_rep_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loaddata();
    }

    private void loaddata() {
        arrayList=new ArrayList<>();
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));
        arrayList.add(new NotificationsModel("khfsdajdshk"));

        NotificationsAdapters topPicksAdapter = new NotificationsAdapters(Notifications.this, arrayList);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(Notifications.this);
        notification_list.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        notification_list.setItemAnimator(new DefaultItemAnimator());
        notification_list.setAdapter(topPicksAdapter);


    }
}