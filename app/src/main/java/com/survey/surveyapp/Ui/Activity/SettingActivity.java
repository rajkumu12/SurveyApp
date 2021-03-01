package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.survey.surveyapp.R;

public class SettingActivity extends BaseActivity {

    TextView tv_logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_setting);

        tv_logout=findViewById(R.id.logout);

        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDatabase.clearAllTables();
                startActivity(new Intent(SettingActivity.this,Login.class));
                finish();
            }
        });


    }
}