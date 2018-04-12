package com.jcgroup.lidian.spring.pojo.vo;

/**
 * 错误码
 * <p/>
 * Created by xutiantian on 16/10/7.
 */
public enum ErrorEnum {
    /**
     * 错误码分内外两种
     * 对内使用最细粒度错误吗，对外使用统一错误码
     * 对外统一使用本类型第一个错误码。
     */
    //1开头返回接收细节错误码,其它返回父错误码
    SYSTEM_ERROR(10000, 10000, "系统错误"),//
    ERROR_BIZ_FAIL(10000, 10001, "业务失败"),
    ERROR_BIZ_UNIQUE_REQ_ID(10000, 10002, "唯一性约束重复"),
    SERVICE_ERROR(10000, 90001, "服务异常"),

    //2开头为参数校验信息错误
    ERROR_PARAM(20000, 20000, "请求参数错误"),
    ERROR_PARAM_EMPTY(20000, 20001, "参数为空"),
    ERROR_PARAM_FORMAT(20000, 20002, "参数格式不正确"),
    ERROR_PARAM_MOBILE(20000, 20003, "请输入正确的手机号"),
    ERROR_PARAM_DEVICETYPE_NOT_EXITS(20000, 20004, "设备类型不存在"),


    //5开头为和用户信息相关错误
    ERROR_USER(50000, 50000, "用户信息错误"),
    ERROR_USER_NO_EXIST(50000, 50001, "用户不存在"),
    ERROR_USER_PHONE_NO_EXIST(50000, 50002, "用户手机号不存在"),
    ERROR_USER_PHONE_HAS_EXIST(50000, 50003, "用户手机号已注册"),
    ERROR_USER_HAS_FREEZE(50000, 50010, "您的账号将被冻结2小时"),
    ERROR_USER_ERRCOUNT_TOO_MANY(50000, 50011, "手机号或登录密码输错次数过多"),
    ERROR_USER_WRONG_PASSWD(50000, 50012, "手机号或登录密码不正确"),
    ERROR_USER_HAS_FREEZE_DAY(50000, 50013, "今日您的账号将被冻结"),

    ERROR_SMS_TYPE_NOT_SUPPORT(50000, 50100, "短信类型不支持"),
    ERROR_SMS_TIMES_OVER_TOP(50000, 50101, "获取验证码超过三次，不可获取验证码"),

    //6 OAUTH2鉴权错误信息
    ERROR_OAUTH2_FAIL(60000, 60000, "鉴权失败"),
    ERROR_OAUTH2_TOKEN_NOT_EXIST(60000, 60001, "鉴权token信息不存在"),
    ERROR_OAUTH2_REFRESH_FAIL(60000, 60002, "刷新token失败"),
    ERROR_OAUTH2_REFRESH_TOKEN_ILLEGAL(60000, 60003, "refresh token不合法"),
    ERROR_OAUTH2_REFRESH_TOKEN_EXPIRED(60000, 60004, "refresh token过期"),
    ERROR_CLIENT_NOT_REG(60000, 60005, "未注册的第三方应用"),
    ERROR_CLIENT_AUTH_FAIL(60000, 60006, "第三方应用认证失败"),
    ERROR_OAUTH2_TOKEN_TOKEN_ILLEGAL(60000, 60007, "access token不合法"),
    ERROR_OAUTH2_TOKEN_TOKEN_EXPIRED(60000, 60008, "access token过期"),
    //7搜索服务异常
    ERROR_OUT_SEARCH_FAIL(70000, 70001, "搜索服务异常"),

    // 89运营管理后台权限
    ERROR_AUTH_FAIL(80000, 89001, "鉴权失败"),
    ERROR_USER_NOT_LOGIN(80000, 89002, "用户未登录"),
    ERROR_USER_LOGIN_FAIL(80000, 89003, "用户名账号或密码错误"),

    ERROR_CHANNEL_ENABLE(81000,81001,"频道不存在或者未启用"),
    ERROR_CHANNEL_BANNER(81000,81002,"频道未启用banner"),

    ;


    private final int errorCode;
    private final int parentCode;
    private final String errorMessage;

    ErrorEnum(int parentCode, int errorCode, String errorMessage) {
        this.parentCode = parentCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ErrorEnum getErrorByCode(int code) {
        for (ErrorEnum errorEnum : values()) {
            if (errorEnum.getErrorCode() == code) {
                return errorEnum;
            }
        }
        return null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getParentCode() {
        if (String.valueOf(errorCode).startsWith("1")) {
            return errorCode;
        }

        return parentCode;
    }

    public ErrorEnum getOutError() {
        return getErrorByCode(getParentCode());
    }

    @Override
    public String toString() {
        return "{" + errorCode + ", " + errorMessage + "}";
    }
}
