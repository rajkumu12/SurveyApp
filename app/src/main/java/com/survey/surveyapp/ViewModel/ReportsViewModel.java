package com.survey.surveyapp.ViewModel;

import android.app.Activity;

import com.survey.surveyapp.Delegates.BaseDelegate;
import com.survey.surveyapp.Delegates.Notifciationsdelegates;
import com.survey.surveyapp.Delegates.ReportsDelegeates;
import com.survey.surveyapp.Models.GetNotificationsModel;
import com.survey.surveyapp.Models.ReportsModel;
import com.survey.surveyapp.Netwrks.APIClient;
import com.survey.surveyapp.Netwrks.APIInterface;
import com.survey.surveyapp.Netwrks.WSContants;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportsViewModel extends BaseViewModel{

    public APIInterface apiInterface = null;

    public Call<ReportsModel> call = null;


    public ReportsViewModel(Activity activity,BaseDelegate cb) {
        super(cb);
        apiInterface = APIClient.getClient(activity).create(APIInterface.class);
    }

    public  void  getReportts(String userid){

        ArrayList<MultipartBody.Part> arrayListMash = new ArrayList<MultipartBody.Part>();
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.PROFILE_USER_ID, userid));
        call = apiInterface.getReportd(arrayListMash);
        call.enqueue(new Callback<ReportsModel>() {
            @Override
            public void onResponse(Call<ReportsModel> call, Response<ReportsModel> response) {
                stopProgressDialog();
                ((ReportsDelegeates) cb).onSucess(response.body());
//                Log.e(TAG, "responseCode token: " + response.body().getStatus());
//                Log.e(TAG, "responseCode token: " + response.body().getMessage());
//                Log.e(TAG, "responseCode token: " + response.body().getDataModel().getId());
            }

            @Override
            public void onFailure(Call<ReportsModel> call, Throwable t) {

                ((ReportsDelegeates) cb).onError(t.getMessage());
            }
        });

    }
}
