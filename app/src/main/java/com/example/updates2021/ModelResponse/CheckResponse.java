package com.example.updates2021.ModelResponse;

public class CheckResponse {
    String message, desc;

    public CheckResponse(String message, String desc) {
        this.message = message;
        this.desc = desc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
