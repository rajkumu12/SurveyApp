package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.survey.surveyapp.Delegates.ReportsDelegeates;
import com.survey.surveyapp.Models.BaseModel;
import com.survey.surveyapp.Models.ReportsModel;
import com.survey.surveyapp.R;

public class ReportActivity extends BaseActivity {

    ImageView imageView_rep_1;
    LinearLayout lly_report,lly_weekly,lly_monthly,lly_yearly;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_report);

        imageView_rep_1=findViewById(R.id.back1_rep);
        lly_report=findViewById(R.id.lly_daily);
        lly_weekly=findViewById(R.id.lly_weekly);
        lly_monthly=findViewById(R.id.lly_monthly);
        lly_yearly=findViewById(R.id.lly_yearly);


        imageView_rep_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lly_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReportActivity.this,DailyReportActivity.class);
                intent.putExtra("key","Daily Report");
                startActivity(intent);
            }
        });

        lly_weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReportActivity.this,DailyReportActivity.class);
                intent.putExtra("key","Weekly Report");
                startActivity(intent);
            }
        });

        lly_monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReportActivity.this,DailyReportActivity.class);
                intent.putExtra("key","Monthly Report");
                startActivity(intent);
            }
        });

        lly_yearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReportActivity.this,DailyReportActivity.class);
                intent.putExtra("key","Yearly Report");
                startActivity(intent);
            }
        });

    }


}