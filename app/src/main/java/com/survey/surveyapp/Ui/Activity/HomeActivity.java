package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.survey.surveyapp.Ui.Fragments.HomeFragments;
import com.survey.surveyapp.R;
import com.survey.surveyapp.FullDrawerLayout;

public class HomeActivity extends AppCompatActivity  implements View.OnClickListener{
    FullDrawerLayout drawerLayout;
    ImageView imageView_hamburgericon,close_drawer;
    TextView tv_daily_report_nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer);
        imageView_hamburgericon = findViewById(R.id.hamburger);
        close_drawer =findViewById(R.id.close_drawer);
        tv_daily_report_nav =findViewById(R.id.tv_daily_rep_nav);


        imageView_hamburgericon.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if(!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(Gravity.START);
                else drawerLayout.closeDrawer(Gravity.START);
            }
        });


        close_drawer.setOnClickListener(this);
        tv_daily_report_nav.setOnClickListener(this);

        loadFragment(new HomeFragments());
    }

    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.close_drawer){
            if(!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(Gravity.START);
            else drawerLayout.closeDrawer(Gravity.START);
        }else if (id==R.id.tv_daily_rep_nav){
            startActivity(new Intent(HomeActivity.this,ReportActivity.class));
        }
    }
}