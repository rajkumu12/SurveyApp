package com.survey.surveyapp.Ui.Activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.survey.surveyapp.Util.SavePref;
import com.survey.surveyapp.db.AppDatabase;

public class BaseActivity extends AppCompatActivity {
    public SavePref pref = new SavePref();
    public AppDatabase appDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        pref.SavePref(this);
        appDatabase = AppDatabase.getAppDatabase(this);
    }
}
