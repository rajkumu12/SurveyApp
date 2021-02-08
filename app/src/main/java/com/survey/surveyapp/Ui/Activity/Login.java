package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.survey.surveyapp.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText rt_email,et_password;
    TextView tv_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        getViews();


        tv_login.setOnClickListener(this);
    }

    //region findViews
    private void getViews() {
        tv_login=findViewById(R.id.tv_login);

    }
    //endregion


    //region click
    @Override
    public void onClick(View v) {
       int id=v.getId();
       if (id==R.id.tv_login){
           startActivity(new Intent(Login.this, HomeActivity.class));
           finish();
       }


    }
    //endregion




}