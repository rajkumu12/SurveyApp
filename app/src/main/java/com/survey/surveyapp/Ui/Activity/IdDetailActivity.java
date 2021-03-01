package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.survey.surveyapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class IdDetailActivity extends AppCompatActivity {

    String id,user_id,fname,lanme,email,dob,gender,mobile,designation,address,profile_pic;
    ImageView imageView_back,imag4e_edit,image_ok;
    CircleImageView image_profile;
    TextView tv_cont,tv_add,tv_desi,tv_email,tv_gender,tv_name,tv_id;
    EditText et_cont,et_add,et_desi,et_email,et_gender,et_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_id_detail);

        imageView_back=findViewById(R.id.back1_rep_iddetail);
        imag4e_edit=findViewById(R.id.image_edit_id);
        image_ok=findViewById(R.id.image_ok_id);
        image_profile=findViewById(R.id.user_profile);

        tv_name=findViewById(R.id.tv_name_id);
        tv_cont=findViewById(R.id.tv_cont_id);
        tv_add=findViewById(R.id.tv_add_id);
        tv_desi=findViewById(R.id.tv_desig_id);
        tv_email=findViewById(R.id.tv_email_id);
        tv_gender=findViewById(R.id.tv_gender_id);
        tv_id=findViewById(R.id.tv_id);

        et_name=findViewById(R.id.et_name_id);
        et_cont=findViewById(R.id.et_cont_id);
        et_add=findViewById(R.id.et_add_id);
        et_desi=findViewById(R.id.et_desig_id);
        et_email=findViewById(R.id.et_email_id);
        et_gender=findViewById(R.id.et_gender_id);


        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        user_id=intent.getStringExtra("user_id");
        fname=intent.getStringExtra("fname");
        lanme=intent.getStringExtra("lanme");
        email=intent.getStringExtra("email");
        dob=intent.getStringExtra("dob");
        gender=intent.getStringExtra("gender");
        mobile=intent.getStringExtra("mobile");
        designation=intent.getStringExtra("designation");
        address=intent.getStringExtra("address");
        profile_pic=intent.getStringExtra("profile_pic");

        Log.d("zxfdzsfslkjkljkhhhhhh","gfdhfy"+mobile);


        tv_id.setText("ID -"+id);
        tv_name.setText(fname+" "+lanme);
        et_cont.setText(mobile);
        tv_add.setText(address);
        tv_email.setText(email);
        tv_desi.setText(designation);
        tv_gender.setText(gender);

        Glide.with(this)
                .load(profile_pic)
                .centerCrop()
                .placeholder(R.drawable.ic_user)
                .into(image_profile);

        imag4e_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imag4e_edit.setVisibility(View.GONE);
                image_ok.setVisibility(View.VISIBLE);
                tv_name.setVisibility(View.GONE);
                tv_cont.setVisibility(View.GONE);
                tv_add.setVisibility(View.GONE);
                tv_desi.setVisibility(View.GONE);
                tv_email.setVisibility(View.GONE);
                tv_gender.setVisibility(View.GONE);


                et_name.setVisibility(View.VISIBLE);
                et_cont.setVisibility(View.VISIBLE);
                et_add.setVisibility(View.VISIBLE);
                et_desi.setVisibility(View.VISIBLE);
                et_email.setVisibility(View.VISIBLE);
                et_gender.setVisibility(View.VISIBLE);


                et_name.setText(tv_name.getText().toString().trim());
                et_cont.setText(tv_cont.getText().toString().trim());
                et_add.setText(tv_add.getText().toString().trim());
                et_desi.setText(tv_desi.getText().toString().trim());
                et_email.setText(tv_email.getText().toString().trim());
                et_gender.setText(tv_gender.getText().toString().trim());
            }
        });

        image_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imag4e_edit.setVisibility(View.VISIBLE);
                image_ok.setVisibility(View.GONE);

                tv_cont.setVisibility(View.VISIBLE);
                tv_add.setVisibility(View.VISIBLE);
                tv_desi.setVisibility(View.VISIBLE);
                tv_email.setVisibility(View.VISIBLE);
                tv_gender.setVisibility(View.VISIBLE);
                tv_name.setVisibility(View.VISIBLE);


                et_cont.setVisibility(View.GONE);
                et_add.setVisibility(View.GONE);
                et_desi.setVisibility(View.GONE);
                et_email.setVisibility(View.GONE);
                et_gender.setVisibility(View.GONE);
                et_name.setVisibility(View.GONE);
            }
        });

        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}