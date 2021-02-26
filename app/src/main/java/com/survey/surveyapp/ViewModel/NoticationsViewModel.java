package com.survey.surveyapp.ViewModel;

import android.app.Activity;

import com.survey.surveyapp.Delegates.BaseDelegate;
import com.survey.surveyapp.Delegates.LoginDelegates;
import com.survey.surveyapp.Delegates.Notifciationsdelegates;
import com.survey.surveyapp.Models.GetNotificationsModel;
import com.survey.surveyapp.Models.LoginModel;
import com.survey.surveyapp.Models.NotificationModels;
import com.survey.surveyapp.Netwrks.APIClient;
import com.survey.surveyapp.Netwrks.APIInterface;
import com.survey.surveyapp.Netwrks.WSContants;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoticationsViewModel extends BaseViewModel {

    public APIInterface apiInterface = null;

    public Call<GetNotificationsModel> call = null;


    public NoticationsViewModel(Activity activity,BaseDelegate cb) {
        super(cb);
        apiInterface = APIClient.getClient(activity).create(APIInterface .class);
    }





    public  void  GetNotificationsdata(String userid){


        ArrayList<MultipartBody.Part> arrayListMash = new ArrayList<MultipartBody.Part>();
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.PROFILE_USER_ID, userid));
        call = apiInterface.getNotification(arrayListMash);
        call.enqueue(new Callback<GetNotificationsModel>() {
            @Override
            public void onResponse(Call<GetNotificationsModel> call, Response<GetNotificationsModel> response) {
                stopProgressDialog();
                ((Notifciationsdelegates) cb).onSucess(response.body());
//                Log.e(TAG, "responseCode token: " + response.body().getStatus());
//                Log.e(TAG, "responseCode token: " + response.body().getMessage());
//                Log.e(TAG, "responseCode token: " + response.body().getDataModel().getId());
            }

            @Override
            public void onFailure(Call<GetNotificationsModel> call, Throwable t) {

                ((Notifciationsdelegates) cb).onError(t.getMessage());
            }
        });

    }

}
