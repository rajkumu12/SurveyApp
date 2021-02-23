package com.survey.surveyapp.ViewModel;

import android.app.Activity;
import android.util.Log;


import com.survey.surveyapp.Delegates.BaseDelegate;
import com.survey.surveyapp.Delegates.CreateIdDelegates;
import com.survey.surveyapp.Delegates.LoginDelegates;
import com.survey.surveyapp.Models.BaseModel;
import com.survey.surveyapp.Models.LoginModel;
import com.survey.surveyapp.Netwrks.APIClient;
import com.survey.surveyapp.Netwrks.APIInterface;
import com.survey.surveyapp.Netwrks.WSContants;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel{
    String TAG = "LoginViewModel";

//    ArticleRepository articleRepository;

    public APIInterface apiInterface = null;
    public Call<LoginModel> call = null;
    public Call<BaseModel> call1 = null;

    public LoginViewModel(BaseDelegate cb) {
        super(cb);
    }

    public LoginViewModel(Activity activity, BaseDelegate cb) {
        super(activity, cb);
        apiInterface = APIClient.getClient(activity).create(APIInterface.class);

    }



    public void login(String email,String password, String deviceToken){




        startProgressDialog();


        ArrayList<MultipartBody.Part> arrayListMash = new ArrayList<MultipartBody.Part>();
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.EMAIL, email));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.PASSWORD, password));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.REGISTER_LOGIN_DEVICE_TOKEN, deviceToken));

        call = apiInterface.register_login(arrayListMash);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                stopProgressDialog();
                ((LoginDelegates) cb).onSucess(response.body());
//                Log.e(TAG, "responseCode token: " + response.body().getStatus());
//                Log.e(TAG, "responseCode token: " + response.body().getMessage());
//                Log.e(TAG, "responseCode token: " + response.body().getDataModel().getId());
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                stopProgressDialog();
                ((LoginDelegates) cb).onError(t.getMessage());
            }
        });



//        call = apiInterface.login("mobile1", "mobile1");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                stopProgressDialog();
//                String responseCode = "";
//                try {
//                    if(response.body() != null) {
//                        responseCode = response.body().string();
//                        Log.e(TAG, "responseCode token: " + responseCode);
//                        ((LoginDelegate) cb).onSucess(responseCode);
//                    }else{
//                        ((LoginDelegate) cb).onError("Something went wrong!");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    ((LoginDelegate) cb).onError(""+e.getMessage());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                stopProgressDialog();
//            }
//        });


    }


    public void CreateId(String user_id, String fname, String lname, String email, String password, String confirm_password, String dob,String gender,
                         String designation,String address,String mobile,String profile_pic){
        startProgressDialog();

        ArrayList<MultipartBody.Part> arrayListMash = new ArrayList<MultipartBody.Part>();

        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.PROFILE_USER_ID, user_id));

        if(!profile_pic.equalsIgnoreCase("")){
            File file = new File(profile_pic);
            RequestBody requestFile =  RequestBody.create( MediaType.parse("image/*"), file );
            MultipartBody.Part body = MultipartBody.Part.createFormData(WSContants.PROFILE_PROFILE_PIC, file.getName(), requestFile);
            arrayListMash.add(body);
        }



        arrayListMash.add(MultipartBody.Part.createFormData("fname", fname));
        arrayListMash.add(MultipartBody.Part.createFormData("lname", lname));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.PROFILE_PROFILE_PIC, profile_pic));
        arrayListMash.add(MultipartBody.Part.createFormData("email", email));
        arrayListMash.add(MultipartBody.Part.createFormData("password", password));
        arrayListMash.add(MultipartBody.Part.createFormData("confirm_password", confirm_password));
        arrayListMash.add(MultipartBody.Part.createFormData("dob", dob));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.MOBILE, mobile));
        arrayListMash.add(MultipartBody.Part.createFormData("designation", designation));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.GENDER, gender));
        arrayListMash.add(MultipartBody.Part.createFormData("address", address));


        call1 = apiInterface.CreateId(arrayListMash);
        call1.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                stopProgressDialog();
                ((CreateIdDelegates) cb).onSucess(response.body());
                Log.e(TAG, "responseCode token: " + response.body().getSuccess());
//                Log.e(TAG, "responseCode token: " + response.body().getMessage());
//                Log.e(TAG, "responseCode token: " + response.body().getDataModel().getId());
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                stopProgressDialog();
                ((CreateIdDelegates) cb).onError(t.getMessage());
                Log.e(TAG, "onFailure token: " + t.getLocalizedMessage());
            }
        });

    }






    public void updateUser(String id, String full_name, String profile_pic, String about, String gender, String city, String phone,String designation,String mobile){
        startProgressDialog();

        ArrayList<MultipartBody.Part> arrayListMash = new ArrayList<MultipartBody.Part>();

        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.PROFILE_USER_ID, id));

        if(!profile_pic.equalsIgnoreCase("")){
            File file = new File(profile_pic);
            RequestBody requestFile =  RequestBody.create( MediaType.parse("image/*"), file );
            MultipartBody.Part body = MultipartBody.Part.createFormData(WSContants.PROFILE_PROFILE_PIC, file.getName(), requestFile);
            arrayListMash.add(body);
        }



        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.FULLNAME, full_name));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.PROFILE_PROFILE_PIC, profile_pic));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.PROFILE_ABOUT, about));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.MOBILE, mobile));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.PROFILE_CITY, city));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.GENDER, gender));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.PHONE, phone));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.DESIGNATION, designation));

        call = apiInterface.updateUser(arrayListMash);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                stopProgressDialog();
                ((LoginDelegates) cb).onSucess(response.body());
                Log.e(TAG, "responseCode token: " + response.body().getSuccess());
//                Log.e(TAG, "responseCode token: " + response.body().getMessage());
//                Log.e(TAG, "responseCode token: " + response.body().getDataModel().getId());
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                stopProgressDialog();
                ((LoginDelegates) cb).onError(t.getMessage());
                Log.e(TAG, "onFailure token: " + t.getLocalizedMessage());
            }
        });

    }




    Call<LoginModel> callAA = null;

    public void login2(String mobile, String deviceToken){

        startProgressDialog();

        ArrayList<MultipartBody.Part> arrayListMash = new ArrayList<MultipartBody.Part>();
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.REGISTER_LOGIN_MOBILE, mobile));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.REGISTER_LOGIN_DEVICE_TOKEN, deviceToken));

        callAA = apiInterface.register_login2(arrayListMash);
        callAA.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                stopProgressDialog();
               // ((LoginDelegate) cb).onSucess(response.body());
//                Log.e(TAG, "responseCode token: " + response.body().getStatus());
//                Log.e(TAG, "responseCode token: " + response.body().getMessage());
//                Log.e(TAG, "responseCode token: " + response.body().getDataModel().getId());
                Log.e(TAG, "responseCode token: " + response.body());
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                stopProgressDialog();
               // ((LoginDelegate) cb).onError(t.getMessage());
            }
        });



//        call = apiInterface.login("mobile1", "mobile1");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                stopProgressDialog();
//                String responseCode = "";
//                try {
//                    if(response.body() != null) {
//                        responseCode = response.body().string();
//                        Log.e(TAG, "responseCode token: " + responseCode);
//                        ((LoginDelegate) cb).onSucess(responseCode);
//                    }else{
//                        ((LoginDelegate) cb).onError("Something went wrong!");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    ((LoginDelegate) cb).onError(""+e.getMessage());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                stopProgressDialog();
//            }
//        });


    }





}
