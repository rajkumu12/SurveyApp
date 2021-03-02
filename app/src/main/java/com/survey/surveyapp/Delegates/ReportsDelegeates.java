package com.survey.surveyapp.Delegates;

import com.survey.surveyapp.Models.GetNotificationsModel;
import com.survey.surveyapp.Models.ReportsModel;

public interface ReportsDelegeates extends BaseDelegate{
    void onSucess(ReportsModel reportsModel);
    void onError(String error);
}
