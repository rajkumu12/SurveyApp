package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.survey.surveyapp.R;

public class ProfileActivity extends AppCompatActivity {

    ImageView imageView_edit, imageView_ok, image_edit_detail, image_ok_detail,image_back;
    TextView textView_name, tv_contact, tv_city, tv_designation, tv_email, tv_typehere, tv_alt_contact;
    EditText et_name, et_contact, et_city, et_designation, et_email, et_typehere, et_alt_contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        imageView_edit = findViewById(R.id.edtit_pen_name);
        image_edit_detail = findViewById(R.id.image_editdetal_pen);
        image_back = findViewById(R.id.back1_rep_profile);
        imageView_ok = findViewById(R.id.image_name_ok_button);
        image_ok_detail = findViewById(R.id.ok_button_detail);


        textView_name = findViewById(R.id.tv_name);
        et_name = findViewById(R.id.et_name);


        //Detailkinfo views
        tv_contact = findViewById(R.id.tv_cont_detail);
        tv_city = findViewById(R.id.tv_city_detail);
        tv_designation = findViewById(R.id.tv_designation_detail);
        tv_email = findViewById(R.id.tv_email_detail);
        tv_typehere = findViewById(R.id.tv_typehere_detail);
        tv_alt_contact = findViewById(R.id.tv_alternatecontact_detail);
        ;

        et_contact = findViewById(R.id.et_cont_detail);
        et_city = findViewById(R.id.et_city_detail);
        et_designation = findViewById(R.id.et_designation_detail);
        et_email = findViewById(R.id.et_email_detail);
        et_typehere = findViewById(R.id.et_typehere_detail);
        et_alt_contact = findViewById(R.id.et_alternatecontact_detail);

        imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_name.setText(textView_name.getText().toString().trim());
                et_name.setVisibility(View.VISIBLE);
                textView_name.setVisibility(View.GONE);
                imageView_edit.setVisibility(View.GONE);
                imageView_ok.setVisibility(View.VISIBLE);
            }
        });

        imageView_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_name.setVisibility(View.GONE);
                textView_name.setVisibility(View.VISIBLE);
                imageView_edit.setVisibility(View.VISIBLE);
                imageView_ok.setVisibility(View.GONE);
            }
        });

        image_edit_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_ok_detail.setVisibility(View.VISIBLE);
                image_edit_detail.setVisibility(View.GONE);

                et_contact.setText(tv_contact.getText().toString().trim());
                et_city.setText(tv_city.getText().toString().trim());
                et_designation.setText(tv_designation.getText().toString().trim());
                et_email.setText(tv_email.getText().toString().trim());
                et_typehere.setText(tv_typehere.getText().toString().trim());
                et_alt_contact.setText(tv_alt_contact.getText().toString().trim());


                et_contact.setVisibility(View.VISIBLE);
                et_city.setVisibility(View.VISIBLE);
                et_designation.setVisibility(View.VISIBLE);
                et_email.setVisibility(View.VISIBLE);
                et_typehere.setVisibility(View.VISIBLE);
                et_alt_contact.setVisibility(View.VISIBLE);


                tv_contact.setVisibility(View.GONE);
                tv_city.setVisibility(View.GONE);
                tv_designation.setVisibility(View.GONE);
                tv_email.setVisibility(View.GONE);
                tv_typehere.setVisibility(View.GONE);
                tv_alt_contact.setVisibility(View.GONE);

            }
        });
        image_ok_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_ok_detail.setVisibility(View.GONE);
                image_edit_detail.setVisibility(View.VISIBLE);
                et_contact.setVisibility(View.GONE);
                et_city.setVisibility(View.GONE);
                et_designation.setVisibility(View.GONE);
                et_email.setVisibility(View.GONE);
                et_typehere.setVisibility(View.GONE);
                et_alt_contact.setVisibility(View.GONE);


                tv_contact.setVisibility(View.VISIBLE);
                tv_city.setVisibility(View.VISIBLE);
                tv_designation.setVisibility(View.VISIBLE);
                tv_email.setVisibility(View.VISIBLE);
                tv_typehere.setVisibility(View.VISIBLE);
                tv_alt_contact.setVisibility(View.VISIBLE);
            }
        });

        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}