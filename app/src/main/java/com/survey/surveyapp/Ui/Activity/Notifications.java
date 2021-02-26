package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.survey.surveyapp.Adapters.DailyReportAdapter;
import com.survey.surveyapp.Adapters.NotificationsAdapters;
import com.survey.surveyapp.Delegates.Notifciationsdelegates;
import com.survey.surveyapp.Models.DailyReportModel;
import com.survey.surveyapp.Models.GetNotificationsModel;
import com.survey.surveyapp.Models.NotificationsModel;
import com.survey.surveyapp.R;
import com.survey.surveyapp.ViewModel.LoginViewModel;
import com.survey.surveyapp.ViewModel.NoticationsViewModel;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends BaseActivity implements Notifciationsdelegates {

    private List<GetNotificationsModel> arrayList;
    RecyclerView notification_list;
    ImageView imageView_back1_rep_notifications;
    private NoticationsViewModel viewModal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_notifications);


        viewModal=new NoticationsViewModel(this,this);
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

        viewModal.GetNotificationsdata(appDatabase.userDao().getLoginUser().getUid());
    }

    @Override
    public void onSucess(GetNotificationsModel loginModel) {

        if (loginModel.getSuccess().equals("true")){
            NotificationsAdapters topPicksAdapter = new NotificationsAdapters(Notifications.this, loginModel.getData());
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(Notifications.this);
            notification_list.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
            notification_list.setItemAnimator(new DefaultItemAnimator());
            notification_list.setAdapter(topPicksAdapter);
        }else {
            Toast.makeText(this, ""+loginModel.getSuccess(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onError(String error) {

    }
}