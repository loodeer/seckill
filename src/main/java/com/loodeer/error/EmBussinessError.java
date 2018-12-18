package com.loodeer.error;

public enum EmBussinessError implements CommonError {

    // 1 开头为通用性错误
    PARAM_INVALID(10001, "参数错误"),
    UNKNOW_ERROR(10002, "未知错误"),

    // 2 开头为用户错误
    USER_NOT_EXIT(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "登陆手机号或密码错误"),
    USER_NOT_LOGIN(20003, "用户还未登陆"),

    // 3 开头为订单操作错误
    STOCK_NOT_ENOUGH(30001, "库存不足");

    private int errCode;
    private String errMsg;

    private EmBussinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override public int getErrCode() {
        return this.errCode;
    }

    @Override public String getErrMsg() {
        return this.errMsg;
    }

    @Override public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
