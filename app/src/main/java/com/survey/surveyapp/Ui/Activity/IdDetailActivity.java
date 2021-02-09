package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.survey.surveyapp.R;

public class IdDetailActivity extends AppCompatActivity {

    ImageView imageView_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_id_detail);

        imageView_back=findViewById(R.id.back1_rep_iddetail);


        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}