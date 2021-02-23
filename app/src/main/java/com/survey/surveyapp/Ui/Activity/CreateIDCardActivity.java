package com.survey.surveyapp.Ui.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.survey.surveyapp.Delegates.CreateIdDelegates;
import com.survey.surveyapp.Models.BaseModel;
import com.survey.surveyapp.Models.LoginModel;
import com.survey.surveyapp.Models.Result_Push;
import com.survey.surveyapp.R;
import com.survey.surveyapp.Util.ShowMsg;
import com.survey.surveyapp.ViewModel.LoginViewModel;

import java.io.IOException;

public class CreateIDCardActivity extends BaseActivity implements CreateIdDelegates {

    ImageView img_back, img_select;
    Spinner spnrmonth, spnrgender;
    EditText et_name1, et_name2, et_username, et_password, et_confirm_password, et_day, et_year, et_mobile, et_designation, et_profile, et_address;
    CheckBox ch_agree;
    TextView tv_create;

    String profilepic;
    private LoginViewModel viewModal;
    private static final int MY_GALLERY_PERMISSION_CODE = 101;
    public static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_i_d_card);

        img_back = findViewById(R.id.back);

        et_name1 = findViewById(R.id.et_name1);
        et_name2 = findViewById(R.id.et_name2);
        et_username = findViewById(R.id.et_email_user);
        et_password = findViewById(R.id.et_id_password);
        et_confirm_password = findViewById(R.id.et_cofirmpassword);
        et_day = findViewById(R.id.et_day);
        et_year = findViewById(R.id.et_year);
        et_mobile = findViewById(R.id.et_id_mobile);
        et_designation = findViewById(R.id.et_id_designation);
        et_profile = findViewById(R.id.et_image_pr);
        et_address = findViewById(R.id.et_id_address);
        ;
        spnrmonth = findViewById(R.id.spinner_id_month);
        ;
        spnrgender = findViewById(R.id.spnr_gender);
        ;


        img_select = findViewById(R.id.image_id_select);
        ;
        ch_agree = findViewById(R.id.ch_id_agree);
        ;
        tv_create = findViewById(R.id.tv_create);
        ;

        viewModal = new LoginViewModel(this, this);

        img_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });


        tv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name1.getText().toString().trim();
                String name2 = et_name2.getText().toString().trim();
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String confirm_password = et_confirm_password.getText().toString().trim();
                String day = et_day.getText().toString().trim();
                String year = et_year.getText().toString().trim();
                String mobile = et_mobile.getText().toString().trim();
                String designation = et_designation.getText().toString().trim();
                String address = et_address.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(CreateIDCardActivity.this, "Enter first name", Toast.LENGTH_SHORT).show();
                } else if (name2.isEmpty()) {
                    Toast.makeText(CreateIDCardActivity.this, "Enter last name", Toast.LENGTH_SHORT).show();
                } else if (username.isEmpty()) {
                    Toast.makeText(CreateIDCardActivity.this, "Enter fusername", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(CreateIDCardActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirm_password)) {
                    Toast.makeText(CreateIDCardActivity.this, "Password not matched", Toast.LENGTH_SHORT).show();
                } else if (day.isEmpty()) {
                    Toast.makeText(CreateIDCardActivity.this, "Enter day of birth", Toast.LENGTH_SHORT).show();
                } else if (year.isEmpty()) {
                    Toast.makeText(CreateIDCardActivity.this, "Enter year of birth", Toast.LENGTH_SHORT).show();
                } else if (mobile.isEmpty()) {
                    Toast.makeText(CreateIDCardActivity.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                } else if (designation.isEmpty()) {
                    Toast.makeText(CreateIDCardActivity.this, "Enter designation", Toast.LENGTH_SHORT).show();
                } else if (address.isEmpty()) {
                    Toast.makeText(CreateIDCardActivity.this, "Enter address", Toast.LENGTH_SHORT).show();
                } else if (!ch_agree.isChecked()) {
                    Toast.makeText(CreateIDCardActivity.this, "Accept terms & condition", Toast.LENGTH_SHORT).show();
                } /*else if (profilepic.isEmpty()) {
                    Toast.makeText(CreateIDCardActivity.this, "Please choose profile", Toast.LENGTH_SHORT).show();
                }*/ else {


                    String dob = spnrmonth.getSelectedItem().toString() + "/" + day + "/" + year;
                    String gender = spnrgender.getSelectedItem().toString().trim();
                    Result_Push user = appDatabase.userDao().getLoginUser();

                    viewModal.CreateId(user.getUid(), name, name2, username, password,
                            confirm_password, dob, gender, designation, address, mobile, "");


                }


            }
        });


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onSucess(BaseModel loginModel) {
            if(loginModel.getSuccess().equals("true")){
                //appDatabase.userDao().insertAll(loginModel.getDataModel());
//            new ShowMsg().createToast(activity , loginModel.getMessage());
//            Intent intent = new Intent(getActivity() , HomeActivity.class);
//            startActivity(intent);
//            activity.finishAffinity();
//            activity.finish();
                Log.d("fldfjhldsf","qwsdeasdas");
                new ShowMsg().createToast(this , loginModel.getMessage());
                finish();
            }else {
                new ShowMsg().createDialog(this , loginModel.getMessage());
            }
    }

    @Override
    public void onError(String error) {

    }


    private void selectImage() {
        try {
            PackageManager pm = getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, MY_CAMERA_PERMISSION_CODE);
                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, MY_GALLERY_PERMISSION_CODE);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.e(TAG, "editTextName "+imagePath);
        switch (requestCode) {
            case MY_CAMERA_PERMISSION_CODE:
                if (resultCode == Activity.RESULT_OK) {

//                    if (data != null) {
//                        bm = (Bitmap)data.getExtras().get("data");
                    /*Glide.with(this).load(profilepic).placeholder(R.drawable.ic_user).into(image_profile);*/
//                        System.out.println("Path"+data.getExtras().get("data"));
//                        String[] projection = { MediaStore.Images.Media.DATA };
//                        Cursor cursor = getActivity(). managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
//                        int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                        cursor.moveToLast();
//                        imagePath = cursor.getString(column_index_data);
////                        File uploadFileName = new File(imagePath);
//                        Log.e(TAG , "filefile11 "+imagePath);
//
                  /*  Result_Push user = appDatabase.userDao().getLoginUser();
                    if(user != null){
                        viewModal.updateUser(user.getUid(),
                                "",
                                ""+imagePath ,
                                "" ,
                                "" ,
                                "" ,
                                "" ,
                                "",
                                "");
                    }*/
                    profilepic = String.valueOf(data.getData());
                    et_profile.setText(profilepic);
                }
                break;


            case MY_GALLERY_PERMISSION_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Bitmap bm = null;
                    try {
                        bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        profilepic = String.valueOf(selectedImage);
                        cursor.close();
//                        File uploadFileName = new File(picturePath);

                        Log.e("TAG", "filefile22 " + profilepic);


                        Result_Push user = appDatabase.userDao().getLoginUser();
                        if (user != null) {
                            //  viewModal.update(user.getId(), user.getFull_name());
                            // viewModal.update(user.getMobile(), user.getToken());
                            profilepic = String.valueOf(data.getData());
                            et_profile.setText(profilepic);
                            //  viewModal.update(user.getId(), user.getFull_name());
                         /*   viewModal.updateUser(user.getUid(),
                                    "",
                                    ""+imagePath ,
                                    "" ,
                                    "" ,
                                    "" ,
                                    "" ,
                                    "",
                                    "");*/
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                break;
        }
    }
}