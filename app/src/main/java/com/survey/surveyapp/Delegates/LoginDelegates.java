package com.survey.surveyapp.Delegates;

import com.survey.surveyapp.Models.LoginModel;

public interface LoginDelegates extends BaseDelegate {
    void onSucess(LoginModel loginModel);
    void onError(String error);
}
