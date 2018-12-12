package com.loodeer.service.impl;

import com.loodeer.dao.UserDOMapper;
import com.loodeer.dao.UserPasswordDOMapper;
import com.loodeer.dataobject.UserDO;
import com.loodeer.dataobject.UserPasswordDO;
import com.loodeer.service.UserService;
import com.loodeer.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

        @Resource
        private UserDOMapper userDOMapper;

        @Resource
        private UserPasswordDOMapper userPasswordDOMapper;

        @Override
        public UserModel getUserById(Integer id) {
                UserDO userDo = userDOMapper.selectByPrimaryKey(id);
                UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUid(id);
                return convertFromDataObject(userDo, userPasswordDO);
        }


        private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
                if (userDO == null) {
                        return null;
                }
                UserModel userModel = new UserModel();
                BeanUtils.copyProperties(userDO, userModel);

                if (userPasswordDO != null) {
                        userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
                }

                return userModel;
        }
}
