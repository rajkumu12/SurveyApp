package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.survey.surveyapp.Adapters.DailyReportAdapter;
import com.survey.surveyapp.Adapters.ViewIdAdapter;
import com.survey.surveyapp.Delegates.GetIdDelegates;
import com.survey.surveyapp.Models.DailyReportModel;
import com.survey.surveyapp.Models.GetIdCardModel;
import com.survey.surveyapp.Models.ViewIdModels;
import com.survey.surveyapp.R;
import com.survey.surveyapp.Util.ShowMsg;
import com.survey.surveyapp.ViewModel.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewIdCardActivity extends BaseActivity implements GetIdDelegates {
    private List<ViewIdModels> arrayList;
    RecyclerView recy_view_id;
    ImageView imageView_back1_rep_v_id;
    private LoginViewModel viewModal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_id_card);

        recy_view_id = findViewById(R.id.rec_viewid_card);
        imageView_back1_rep_v_id = findViewById(R.id.back1_view_id);


        imageView_back1_rep_v_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        viewModal = new LoginViewModel(this, this);
        viewModal.getDataIdCard(appDatabase.userDao().getLoginUser().getUid());
        Log.d("lkjlfkdjflkdjfkld","jkjk"+appDatabase.userDao().getLoginUser().getUid());
        /*loaddata();*/
    }

   /* @Override
    protected void onPostResume() {
        super.onPostResume();

    }*/

   /* private void loaddata() {


        arrayList = new ArrayList<>();
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));
        arrayList.add(new ViewIdModels("khfsdajdshk"));

        ViewIdAdapter topPicksAdapter = new ViewIdAdapter(ViewIdCardActivity.this, arrayList);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(ViewIdCardActivity.this);
        recy_view_id.setLayoutManager(layoutManager2);
                            *//*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*//*
        recy_view_id.setItemAnimator(new DefaultItemAnimator());
        recy_view_id.setAdapter(topPicksAdapter);


    }*/

    @Override
    public void onSucess(GetIdCardModel loginModel) {

        if(loginModel.getSuccess().equals("true")){
            //appDatabase.userDao().insertAll(loginModel.getDataModel());
//            new ShowMsg().createToast(activity , loginModel.getMessage());
//            Intent intent = new Intent(getActivity() , HomeActivity.class);
//            startActivity(intent);
//            activity.finishAffinity();
//            activity.finish();


            ViewIdAdapter topPicksAdapter = new ViewIdAdapter(ViewIdCardActivity.this, loginModel.getResult_push());
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(ViewIdCardActivity.this);
            recy_view_id.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
            recy_view_id.setItemAnimator(new DefaultItemAnimator());
            recy_view_id.setAdapter(topPicksAdapter);
            new ShowMsg().createToast(this , loginModel.getMessage());
        }else {
            new ShowMsg().createDialog(this , loginModel.getMessage());
        }
    }

    @Override
    public void onError(String error) {

    }
}