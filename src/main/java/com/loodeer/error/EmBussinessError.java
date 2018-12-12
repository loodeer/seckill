package com.loodeer.error;

public enum EmBussinessError implements CommonError {

        // 1 开头为用户错误
        USER_NOT_EXIT(10001, "用户不存在")
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
