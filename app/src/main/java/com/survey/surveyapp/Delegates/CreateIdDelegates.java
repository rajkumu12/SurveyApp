package com.survey.surveyapp.Delegates;

import com.survey.surveyapp.Models.BaseModel;
import com.survey.surveyapp.Models.LoginModel;

public interface CreateIdDelegates extends BaseDelegate {
    void onSucess(BaseModel loginModel);
    void onError(String error);
}
