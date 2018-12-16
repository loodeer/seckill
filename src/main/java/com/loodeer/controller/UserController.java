package com.loodeer.controller;

import com.alibaba.druid.util.StringUtils;
import com.loodeer.controller.viewObject.UserVO;
import com.loodeer.error.BussinessException;
import com.loodeer.error.EmBussinessError;
import com.loodeer.response.CommonResult;
import com.loodeer.service.UserService;
import com.loodeer.service.model.UserModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author loodeer
 */
@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials="true", allowedHeaders = "*")
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

        @RequestMapping(value = "login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
        @ResponseBody
        public CommonResult login(@RequestParam(name = "telphone") String telphone,
                @RequestParam(name = "password") String password)
                throws BussinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
                // 1. 入参校验
                if (telphone == null || "".equals(telphone)) {
                        throw new BussinessException(EmBussinessError.PARAM_INVALID, "电话号码不能为空");
                }
                if (password == null || "".equals(password)) {
                        throw new BussinessException(EmBussinessError.PARAM_INVALID, "密码不能为空");
                }
                // 2. 登陆验证
                UserModel userModel = userService.validLogin(telphone, EncodeByMd5(password));
                // 3. 登陆凭证存到 session 内
                this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
                this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
                return CommonResult.create(null);
        }

        @RequestMapping(value = "register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
        @ResponseBody
        public CommonResult register(@RequestParam(name = "telphone") String telphone,
                @RequestParam(name = "otpCode") String otpCode,
                @RequestParam(name = "name") String name,
                @RequestParam(name = "gender") Integer gender,
                @RequestParam(name = "age") Integer age,
                @RequestParam(name = "password") String password)
                throws BussinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

                // 1. 验证手机号与验证码配对
                String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
                if (!StringUtils.equals(inSessionOtpCode, otpCode)) {
                        throw new BussinessException(EmBussinessError.PARAM_INVALID, "短信验证码错误");
                }

                // 2. 用户注册
                UserModel userModel = new UserModel();
                userModel.setName(name);
                userModel.setGender(gender);
                userModel.setAge(age);
                userModel.setTelphone(telphone);
                userModel.setRegisterMode(1);
                userModel.setEncrptPassword(EncodeByMd5(password));

                userService.register(userModel);
                return CommonResult.create(null);
        }

        private String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                BASE64Encoder base64Encoder = new BASE64Encoder();
                String newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
                return newStr;
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
