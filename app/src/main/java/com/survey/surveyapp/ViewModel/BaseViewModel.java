package com.survey.surveyapp.ViewModel;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.survey.surveyapp.Delegates.BaseDelegate;
import com.survey.surveyapp.Util.ShowMsg;


public class BaseViewModel extends ViewModel {
    protected BaseDelegate cb;
    protected Activity activity;

    public BaseViewModel(BaseDelegate cb) {
        this.cb = cb;
    }

    public BaseViewModel(Activity activity, BaseDelegate cb) {
        this.activity = activity;
        this.cb = cb;
    }




    public void startProgressDialog(){
//        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) activity
//                .findViewById(android.R.id.content)).getChildAt(0);
        //View rootView = activity.getWindow().getDecorView().getRootView();
        ShowMsg.startProgressDialog(activity);
    }

    public void stopProgressDialog(){
        ShowMsg.stopProgressDialog(activity);
    }



    public void startProgressBar(){
        ShowMsg.startProgressBar(activity);
    }

    public void stopProgressBar(){
        ShowMsg.stopProgressBar(activity);
     }


}
