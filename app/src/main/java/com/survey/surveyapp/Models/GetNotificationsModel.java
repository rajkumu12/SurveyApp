package com.survey.surveyapp.Models;

import java.util.ArrayList;

public class GetNotificationsModel extends BaseModel{

    ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }
}
