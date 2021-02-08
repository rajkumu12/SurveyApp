package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.survey.surveyapp.R;

public class ReportActivity extends AppCompatActivity {

    ImageView imageView_rep_1;
    LinearLayout lly_report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_report);

        imageView_rep_1=findViewById(R.id.back1_rep);
        lly_report=findViewById(R.id.lly_daily);


        imageView_rep_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lly_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReportActivity.this,DailyReportActivity.class));
            }
        });

    }
}