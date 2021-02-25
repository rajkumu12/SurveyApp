package com.survey.surveyapp.Ui.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.survey.surveyapp.Delegates.LoginDelegates;
import com.survey.surveyapp.Models.LoginModel;
import com.survey.surveyapp.Models.Result_Push;
import com.survey.surveyapp.R;
import com.survey.surveyapp.Ui.Fragments.BottomSheetFragment;
import com.survey.surveyapp.Util.ShowMsg;
import com.survey.surveyapp.ViewModel.LoginViewModel;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseActivity implements LoginDelegates {

    CircleImageView image_profile;
    ImageView imageView_edit, imageView_ok, image_edit_detail, image_ok_detail,image_back,image_pic_edit;
    TextView textView_name, tv_contact, tv_city, tv_designation, tv_email, tv_typehere, tv_alt_contact;
    EditText et_name, et_contact, et_city, et_designation, et_email, et_typehere, et_alt_contact;
    private static final int MY_GALLERY_PERMISSION_CODE = 101;
    public static final int MY_CAMERA_PERMISSION_CODE = 100;

    private LoginViewModel viewModal;
    String imagePath = "";
    private Uri image_uri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        viewModal = new LoginViewModel(this,this);
        imageView_edit = findViewById(R.id.edtit_pen_name);
        image_edit_detail = findViewById(R.id.image_editdetal_pen);
        image_back = findViewById(R.id.back1_rep_profile);
        imageView_ok = findViewById(R.id.image_name_ok_button);
        image_ok_detail = findViewById(R.id.ok_button_detail);
        image_profile = findViewById(R.id.image_profile_p);
        image_pic_edit = findViewById(R.id.edit_pen_pic);


        textView_name = findViewById(R.id.tv_name);
        et_name = findViewById(R.id.et_name);




        //Detailkinfo views
        tv_contact = findViewById(R.id.tv_cont_detail);
        tv_city = findViewById(R.id.tv_city_detail);
        tv_designation = findViewById(R.id.tv_designation_detail);
        tv_email = findViewById(R.id.tv_email_detail);
        tv_typehere = findViewById(R.id.tv_typehere_detail);
        tv_alt_contact = findViewById(R.id.tv_alternatecontact_detail);
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(this);



        //show data
        Glide.with(this)
                .load(appDatabase.userDao().getLoginUser().getProfile_pic())
                .centerCrop()
                .placeholder(R.drawable.ic_user)
                .into(image_profile);
        textView_name.setText(appDatabase.userDao().getLoginUser().getFullname());
        tv_contact.setText(appDatabase.userDao().getLoginUser().getMobile());
        tv_city.setText(appDatabase.userDao().getLoginUser().getCity());
        tv_designation.setText(appDatabase.userDao().getLoginUser().getDesignation());
        tv_email.setText(appDatabase.userDao().getLoginUser().getEmail());
        tv_typehere.setText(appDatabase.userDao().getLoginUser().getAbout_us());
        tv_alt_contact.setText(appDatabase.userDao().getLoginUser().getPhone());




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

                Result_Push user = appDatabase.userDao().getLoginUser();
                if(user != null){
                    //  viewModal.update(user.getId(), user.getFull_name());
                    // viewModal.update(user.getMobile(), user.getToken());
                    Log.e("sfdstgfsgfdsgdfsgt", "editTextName "+user.getUid());
                    //  viewModal.update(user.getId(), user.getFull_name());
                    viewModal.updateUser(user.getUid(),
                            et_name.getText().toString().trim(),
                            "" ,
                            "" ,
                            "" ,
                            "" ,
                            "" ,
                            "",
                            "");
                }

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
                Result_Push user = appDatabase.userDao().getLoginUser();
                if(user != null){
                    //  viewModal.update(user.getId(), user.getFull_name());
                    // viewModal.update(user.getMobile(), user.getToken());
                    Log.e("sfdstgfsgfdsgdfsgt", "editTextName "+user.getUid());
                    //  viewModal.update(user.getId(), user.getFull_name());
                    viewModal.updateUser(user.getUid(),
                            "",
                            "" ,
                            et_typehere.getText().toString().trim(),

                            "" ,
                            et_city.getText().toString().trim() ,
                            et_alt_contact.getText().toString().trim() ,
                            et_designation.getText().toString().trim(),
                            et_contact.getText().toString().trim());
                }




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

        image_pic_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });




    }

    private void selectImage() {
        try {
            PackageManager pm = getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery","Cancel"};
               AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            String fileName = System.currentTimeMillis()+".jpg";
                            ContentValues values = new ContentValues();
                            values.put(MediaStore.Images.Media.TITLE, fileName);
                            image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
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
        switch(requestCode) {
            case MY_CAMERA_PERMISSION_CODE:
                if(resultCode == Activity.RESULT_OK){

//                    if (data != null) {
//                        bm = (Bitmap)data.getExtras().get("data");
                    imagePath = getPath(image_uri);
                    Glide.with(this).load(imagePath).placeholder(R.drawable.ic_user).into(image_profile);
//                        System.out.println("Path"+data.getExtras().get("data"));
//                        String[] projection = { MediaStore.Images.Media.DATA };
//                        Cursor cursor = getActivity(). managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
//                        int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                        cursor.moveToLast();
//                        imagePath = cursor.getString(column_index_data);
////                        File uploadFileName = new File(imagePath);
//                        Log.e(TAG , "filefile11 "+imagePath);
//
                    Result_Push user = appDatabase.userDao().getLoginUser();
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
                    }
                }
                break;


            case MY_GALLERY_PERMISSION_CODE:
                if(resultCode == Activity.RESULT_OK){
                    Bitmap bm = null;
                    try {
                        bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = { MediaStore.Images.Media.DATA };
                        Cursor cursor = this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imagePath = cursor.getString(columnIndex);
                        cursor.close();
//                        File uploadFileName = new File(picturePath);

                        Log.e("TAG" , "filefile22 "+imagePath);


                        Result_Push user = appDatabase.userDao().getLoginUser();
                        if(user != null){
                            //  viewModal.update(user.getId(), user.getFull_name());
                            // viewModal.update(user.getMobile(), user.getToken());
                            Log.e("TAG", "editTextName "+et_name.getText().toString());
                            //  viewModal.update(user.getId(), user.getFull_name());
                            viewModal.updateUser(user.getUid(),
                                    "",
                                    ""+imagePath ,
                                    "" ,
                                    "" ,
                                    "" ,
                                    "" ,
                                    "",
                                    "");
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Glide.with(this).load(bm).placeholder(R.drawable.ic_user).into(image_profile);
                }
                break;
        }
    }

    @Override
    public void onSucess(LoginModel loginModel) {
        if(loginModel.getSuccess().equals("true")){
            //appDatabase.userDao().insertAll(loginModel.getDataModel());
//            new ShowMsg().createToast(activity , loginModel.getMessage());
//            Intent intent = new Intent(getActivity() , HomeActivity.class);
//            startActivity(intent);
//            activity.finishAffinity();
//            activity.finish();


            if(loginModel.getResult_push() != null){
                appDatabase.userDao().updateLoginUser(
                        Integer.parseInt(loginModel.getResult_push().getUid()),
                        loginModel.getResult_push().getFullname(),
                        loginModel.getResult_push().getMobile(),
                        loginModel.getResult_push().getEmail(),
                        loginModel.getResult_push().getProfile_pic(),
                        loginModel.getResult_push().getAbout_us(),
                        loginModel.getResult_push().getGender(),
                        loginModel.getResult_push().getCity(),
                        loginModel.getResult_push().getPhone(),
                        loginModel.getResult_push().getDesignation()
                );
                resetData();
            }
            new ShowMsg().createToast(this , loginModel.getMessage());
        }else {
            new ShowMsg().createDialog(this , loginModel.getMessage());
        }
    }

    @Override
    public void onError(String error) {

    }


    private void resetData() {
        Result_Push user = appDatabase.userDao().getLoginUser();
        if(user != null){

            Log.d("ppppppppppppp","vfx"+user.getProfile_pic());
            Glide.with(this).load(user.getProfile_pic()).placeholder(R.drawable.ic_user).into(image_profile);
            textView_name.setText(user.getFullname());
            tv_contact.setText(user.getMobile());
            tv_city.setText(user.getCity());
            tv_designation.setText(user.getDesignation());
            tv_email.setText(user.getEmail());
            tv_typehere.setText(user.getAbout_us());
            tv_alt_contact.setText(user.getPhone());
          /*  editTextContact.setText(user.getMobile());
            editTextCity.setText(user.getCity());
            editTextClass.setText(user.getClass_level());
            editTextEmail.setText(user.getEmail());
            editTextAboutUs.setText(user.getAbout());
            updateProfile();*/

        }
    }

    private String getPath(Uri selectedImaeUri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };

        Cursor cursor = managedQuery(selectedImaeUri, projection, null, null,
                null);

        if (cursor != null)
        {
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            return cursor.getString(columnIndex);
        }

        return selectedImaeUri.getPath();
    }

}