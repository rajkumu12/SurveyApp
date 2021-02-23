package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.survey.surveyapp.ColorTransparentUtils;
import com.survey.surveyapp.Ui.Fragments.HomeFragments;
import com.survey.surveyapp.R;
import com.survey.surveyapp.FullDrawerLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends  BaseActivity  implements View.OnClickListener{
    FullDrawerLayout drawerLayout;
    CircleImageView img_profile;
    ImageView imageView_hamburgericon,close_drawer;
    TextView tv_home,tv_daily_report_nav,tv_survey,tv_notifications_nav,tv_setting,tv_name_nav,tv_designation;
    TextView tv_name_head;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer);
        imageView_hamburgericon = findViewById(R.id.hamburger);
        close_drawer =findViewById(R.id.close_drawer);
        tv_home=findViewById(R.id.tv_home_nav);
        tv_daily_report_nav =findViewById(R.id.tv_daily_rep_nav);
        tv_survey =findViewById(R.id.tv_survey_nav);
        tv_notifications_nav =findViewById(R.id.tv_notifications_nav);
        tv_setting =findViewById(R.id.tv_setting_nav);
        img_profile =findViewById(R.id.img_profile);
        tv_name_head =findViewById(R.id.tv_name_head);
        tv_name_nav =findViewById(R.id.name);
        tv_designation =findViewById(R.id.tv_designation);


        tv_name_head.setText("Hey "+appDatabase.userDao().getLoginUser().getFullname());

        Glide.with(this)
                .load(appDatabase.userDao().getLoginUser().getProfile_pic())
                .centerCrop()
                .placeholder(R.drawable.ic_user)
                .into(img_profile);


        Log.d("hfdhsflkjdhfj","fdff"+appDatabase.userDao().getLoginUser().getProfile_pic());
        imageView_hamburgericon.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if(!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(Gravity.START);
                else drawerLayout.closeDrawer(Gravity.START);
            }
        });


        tv_name_nav.setText(appDatabase.userDao().getLoginUser().getFullname());
        tv_designation.setText(appDatabase.userDao().getLoginUser().getDesignation());


        close_drawer.setOnClickListener(this);
        tv_home.setOnClickListener(this);
        tv_daily_report_nav.setOnClickListener(this);
        tv_survey.setOnClickListener(this);
        tv_notifications_nav.setOnClickListener(this);
        tv_setting.setOnClickListener(this);
        img_profile.setOnClickListener(this);

        loadFragment(new HomeFragments());

        activeTab(tv_home,tv_daily_report_nav,tv_survey,tv_notifications_nav,tv_setting);

    }

    @SuppressLint("ResourceAsColor")
    private void activeTab(TextView active, TextView tv_daily_report_nav,
                           TextView tv_survey, TextView tv_notifications_nav, TextView tv_setting) {

        active.setTextColor(Color.WHITE);
        tv_daily_report_nav.setTextColor(Color.parseColor("#80FFFFFF"));
        tv_survey.setTextColor(Color.parseColor("#80FFFFFF"));
        tv_notifications_nav.setTextColor(Color.parseColor("#80FFFFFF"));
        tv_setting.setTextColor(Color.parseColor("#80FFFFFF"));


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
        }else if (id==R.id.tv_home_nav){
            loadFragment(new HomeFragments());
            activeTab(tv_home,tv_daily_report_nav,tv_survey,tv_notifications_nav,tv_setting);
        }else if (id==R.id.tv_daily_rep_nav){
            startActivity(new Intent(HomeActivity.this,ReportActivity.class));
            activeTab(tv_daily_report_nav,tv_home,tv_survey,tv_notifications_nav,tv_setting);
        }else if (id==R.id.tv_survey_nav){
            activeTab(tv_survey,tv_daily_report_nav,tv_home,tv_notifications_nav,tv_setting);
        } else if (id==R.id.tv_notifications_nav){
            startActivity(new Intent(HomeActivity.this,Notifications.class));
            activeTab(tv_notifications_nav,tv_survey,tv_daily_report_nav,tv_home,tv_setting);
        }else if (id==R.id.tv_setting_nav){
            startActivity(new Intent(HomeActivity.this,Notifications.class));
            activeTab(tv_setting,tv_notifications_nav,tv_survey,tv_daily_report_nav,tv_home);
        }else if (id==R.id.img_profile){
            startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
        }
    }
}