package com.loodeer.error;

public enum EmBussinessError implements CommonError {

        // 1 开头为通用性错误
        PARAM_INVALID(10001, "参数错误"),
        UNKNOW_ERROR(10002, "未知错误"),

        // 2 开头为用户错误
        USER_NOT_EXIT(20001, "用户不存在")
        ;

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
