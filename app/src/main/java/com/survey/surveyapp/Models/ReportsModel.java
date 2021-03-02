package com.survey.surveyapp.Models;

import java.util.ArrayList;

public class ReportsModel extends BaseModel {
    ArrayList<ReportData> data;

    public ArrayList<ReportData> getData() {
        return data;
    }

    public void setData(ArrayList<ReportData> data) {
        this.data = data;
    }
}
