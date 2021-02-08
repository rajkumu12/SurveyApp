package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.survey.surveyapp.Adapters.DailyReportAdapter;
import com.survey.surveyapp.Adapters.ViewIdAdapter;
import com.survey.surveyapp.Models.DailyReportModel;
import com.survey.surveyapp.Models.ViewIdModels;
import com.survey.surveyapp.R;

import java.util.ArrayList;
import java.util.List;

public class ViewIdCardActivity extends AppCompatActivity {
    private List<ViewIdModels> arrayList;
    RecyclerView recy_view_id;
    ImageView imageView_back1_rep_v_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        loaddata();
    }

    private void loaddata() {


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
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recy_view_id.setItemAnimator(new DefaultItemAnimator());
        recy_view_id.setAdapter(topPicksAdapter);


    }
}