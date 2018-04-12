package com.jcgroup.lidian.spring.pojo.enums;

/**
 * Created by haiyang.song on 2017/3/13.
 */
public enum ResultStatusCode {
    OK(0, "OK"),
    Url_Error(404,"请求地址不存在"),
    SYSTEM_ERROR(10000, "System error"),

    ;


    private int errcode;
    private String errmsg;

    private ResultStatusCode(int Errode, String ErrMsg) {
        this.errcode = Errode;
        this.errmsg = ErrMsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
