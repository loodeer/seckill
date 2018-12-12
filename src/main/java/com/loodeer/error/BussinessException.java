package com.loodeer.error;

// 包装器业务异常类实现

/**
 * @author loodeer
 * 包装器业务异常类
 */
public class BussinessException extends Exception implements CommonError {

        private CommonError commonError;

        public BussinessException(CommonError commonError) {
                super();
                this.commonError = commonError;
        }

        public BussinessException(CommonError commonError, String errMsg) {
                super();
                this.commonError = commonError;
                this.commonError.setErrMsg(errMsg);
        }

        @Override public int getErrCode() {
                return this.commonError.getErrCode();
        }

        @Override public String getErrMsg() {
                return this.commonError.getErrMsg();
        }

        @Override public CommonError setErrMsg(String errMsg) {
                this.commonError.setErrMsg(errMsg);
                return this.commonError;
        }
}
