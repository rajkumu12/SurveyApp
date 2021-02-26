package com.survey.surveyapp.Delegates;

import com.survey.surveyapp.Models.GetNotificationsModel;
import com.survey.surveyapp.Models.LoginModel;
import com.survey.surveyapp.Models.NotificationModels;

public interface Notifciationsdelegates extends BaseDelegate {
    void onSucess(GetNotificationsModel loginModel);
    void onError(String error);
}
