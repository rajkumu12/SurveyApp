package com.survey.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.survey.surveyapp.Models.Result_Push;
import com.survey.surveyapp.Ui.Activity.BaseActivity;
import com.survey.surveyapp.Ui.Activity.HomeActivity;
import com.survey.surveyapp.Ui.Activity.Login;
import com.survey.surveyapp.Util.Utility;

public class SplashActivity extends BaseActivity {
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1999;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        View decorView =getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Utility.checkAndRequestPermissions(SplashActivity.this, REQUEST_ID_MULTIPLE_PERMISSIONS)) {
                    getLogin();
                }else{
                }
            }
        }, 2000);
    }

    private void getLogin() {
        Result_Push loginDataModel = appDatabase.userDao().getLoginUser();
        if(loginDataModel == null){
            startActivity(new Intent(SplashActivity.this , Login.class));
            finish();
        }else{
            startActivity(new Intent(SplashActivity.this , HomeActivity.class));
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d("TAG", "Permission callback called-------");
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                if(Utility.checkAdditionPermissionsCheck(SplashActivity.this, permissions, grantResults, REQUEST_ID_MULTIPLE_PERMISSIONS)) {
                    getLogin();
                }
            }
        }
    }
}