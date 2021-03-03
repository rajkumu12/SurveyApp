package com.survey.surveyapp.Ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.survey.surveyapp.Delegates.LoginDelegates;
import com.survey.surveyapp.Models.LoginModel;
import com.survey.surveyapp.R;
import com.survey.surveyapp.Util.ShowMsg;
import com.survey.surveyapp.ViewModel.LoginViewModel;

public class Login extends BaseActivity implements View.OnClickListener, LoginDelegates {

    EditText rt_email,et_password;
    TextView tv_login;
    private LoginViewModel viewModal;
    String devicetoken;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        viewModal = new LoginViewModel(this,this);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        devicetoken = task.getResult().getToken();
                        Log.d("lkdflkfkldf","d"+devicetoken);
                        // Log and toast
                    }
                });

        getViews();
        tv_login.setOnClickListener(this);
    }

    //region findViews
    private void getViews() {



        rt_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        tv_login=findViewById(R.id.tv_login);

    }
    //endregion


    //region click
    @Override
    public void onClick(View v) {
       int id=v.getId();
       if (id==R.id.tv_login){
           String email=rt_email.getText().toString().trim();
           String password=et_password.getText().toString().trim();
           if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){

               rt_email.setError("Invalid email");
           }else if (password.isEmpty() ){
               et_password.setError("Password can't be emapty");
           }else if (password.length()<8){
               et_password.setError("Password length should be minimum 8 digit");
           }else {
               viewModal.login(email,password,devicetoken);
           }

       }


    }

    @Override
    public void onSucess(LoginModel loginModel) {
        if (loginModel.getSuccess().equals("true")){
            Log.d("hfjsdhfjdsfjdsf","log"+loginModel.getMessage());
            Log.d("hfjsdhfjdsfjdsf","lofgfdgfdgg"+loginModel.getResult_push().getEmail());
            appDatabase.userDao().insertAll(loginModel.getResult_push());
            new ShowMsg().createToast(this , loginModel.getMessage());
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finishAffinity();
            finish();
        }else {
            new ShowMsg().createToast(this , "Login Failed:Email or password is wrong");
        }


    }

    @Override
    public void onError(String error) {
        Log.d("hfjsdhfjdsfjdsf","err"+error);
    }
    //endregion




}