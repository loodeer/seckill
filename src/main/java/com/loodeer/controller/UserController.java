package com.loodeer.controller;

import com.loodeer.controller.viewObject.UserVO;
import com.loodeer.error.BussinessException;
import com.loodeer.error.EmBussinessError;
import com.loodeer.response.CommonResult;
import com.loodeer.service.UserService;
import com.loodeer.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author loodeer
 */
@Controller("user")
@RequestMapping("/user")
@CrossOrigin
public class UserController extends BaseController{

        @Resource
        private UserService userService;

        @Resource
        private HttpServletRequest httpServletRequest;

        @RequestMapping("/get")
        @ResponseBody
        public CommonResult getUser(@RequestParam(name="id") Integer id) throws BussinessException {
               UserModel userModel = userService.getUserById(id);
               UserVO userVO = convertFromModel(userModel);
               if (userVO == null) {
                       throw new BussinessException(EmBussinessError.USER_NOT_EXIT);
               }
               return CommonResult.create(userVO);
        }

        /**
         * 用户获取 otp 短信
         * @param telphone 手机号
         * @return CommonResult
         */
        @RequestMapping(value = "getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
        @ResponseBody
        public CommonResult getOtp(@RequestParam(name="telphone") String telphone) {
                // 1. 生成验证码
                Random random = new Random();
                int randomInt = random.nextInt(88888);
                randomInt += 10000;
                String otpCode = String.valueOf(randomInt);

                // 2. 服务端记录手机号和验证码关联
                httpServletRequest.getSession().setAttribute(telphone, otpCode);

                // 3. 发送短信给用户
                System.out.println("telphone:" + telphone + ";otpCode:" + otpCode);
                return CommonResult.create(null);
        }

        private UserVO convertFromModel(UserModel userModel) {
                if (userModel == null) {
                        return null;
                }
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(userModel, userVO);
                return userVO;
        }

}
