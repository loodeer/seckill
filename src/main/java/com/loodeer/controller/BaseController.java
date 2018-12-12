package com.loodeer.controller;

import com.loodeer.error.BussinessException;
import com.loodeer.error.EmBussinessError;
import com.loodeer.response.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.OK)
        @ResponseBody
        public Object handleException(HttpServletRequest request, Exception ex) {
                Map<String, Object> responseData = new HashMap<>();
                if (ex instanceof BussinessException) {
                        BussinessException bussinessException = (BussinessException)ex;
                        responseData.put("errCode", bussinessException.getErrCode());
                        responseData.put("errMsg", bussinessException.getErrMsg());
                } else {
                        responseData.put("errCode", EmBussinessError.UNKNOW_ERROR.getErrCode());
                        responseData.put("errMsg", EmBussinessError.UNKNOW_ERROR.getErrMsg());
                }

                return CommonResult.create(responseData, "fail");
        }
}
