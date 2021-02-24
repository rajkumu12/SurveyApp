package com.survey.surveyapp.Delegates;

import com.survey.surveyapp.Models.GetIdCardModel;
import com.survey.surveyapp.Models.LoginModel;

public interface GetIdDelegates extends BaseDelegate {
    void onSucess(GetIdCardModel loginModel);
    void onError(String error);
}
