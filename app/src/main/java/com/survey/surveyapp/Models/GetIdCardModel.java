package com.survey.surveyapp.Models;

import java.util.ArrayList;
import java.util.List;

public class GetIdCardModel extends BaseModel{
    ArrayList<IdCardDataModel> result_push;

    public ArrayList<IdCardDataModel> getResult_push() {
        return result_push;
    }

    public void setResult_push(ArrayList<IdCardDataModel> result_push) {
        this.result_push = result_push;
    }
}
