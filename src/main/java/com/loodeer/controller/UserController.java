package com.loodeer.controller;

import com.loodeer.controller.viewObject.UserVO;
import com.loodeer.service.UserService;
import com.loodeer.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author loodeer
 */
@Controller("user")
@RequestMapping("/user")
public class UserController {

        @Resource
        private UserService userService;

        @RequestMapping("/get")
        @ResponseBody
        public UserVO getUser(@RequestParam(name="id") Integer id) {
               UserModel userModel = userService.getUserById(id);
               return convertFromModel(userModel);
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
