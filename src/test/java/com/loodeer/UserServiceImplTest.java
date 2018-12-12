package com.loodeer;

import com.loodeer.service.UserService;
import com.loodeer.service.model.UserModel;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller()
public class UserServiceImplTest {

        @Resource
        private UserService userService;

        @Test
        public void getUserById() {
                UserModel userModel = userService.getUserById(1);
                Assert.assertNotNull(userModel);
        }
}
