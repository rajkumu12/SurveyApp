package com.survey.surveyapp.Models;

import java.io.Serializable;

public class BaseModel implements Serializable {
    String success;
    String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
