package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.survey.surveyapp.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
    }
}