package com.example.czz.coolquestion.bean;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SignupRes {

    /**
     * reason : 用户已存在
     * result : 注册失败
     */

    private String reason;
    private String result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
