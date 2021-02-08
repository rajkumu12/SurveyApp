package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.survey.surveyapp.R;

public class WorkerIdCardActivity extends AppCompatActivity {

    LinearLayout lly_create,lly_view;
    ImageView image_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_worker_id_card);

        lly_create=findViewById(R.id.lly_create);
        image_back=findViewById(R.id.back1);
        lly_view=findViewById(R.id.lly_view);
        lly_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkerIdCardActivity.this,CreateIDCardActivity.class));
            }
        });


        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lly_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkerIdCardActivity.this,ViewIdCardActivity.class));
            }
        });
    }
}