package com.loodeer.service.impl;

import com.loodeer.controller.viewObject.UserVO;
import com.loodeer.dao.UserDOMapper;
import com.loodeer.dao.UserPasswordDOMapper;
import com.loodeer.dataobject.UserDO;
import com.loodeer.dataobject.UserPasswordDO;
import com.loodeer.error.BussinessException;
import com.loodeer.error.EmBussinessError;
import com.loodeer.service.UserService;
import com.loodeer.service.model.UserModel;
import com.loodeer.validator.ValidationResult;
import com.loodeer.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDOMapper userDOMapper;

    @Resource
    private UserPasswordDOMapper userPasswordDOMapper;

    @Resource
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDo = userDOMapper.selectByPrimaryKey(id);
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUid(id);
        return convertFromDataObject(userDo, userPasswordDO);
    }

    @Override
    @Transactional
    public void register(UserModel userModel) throws BussinessException {
        if (userModel == null) {
            throw new BussinessException(EmBussinessError.PARAM_INVALID);
        }
        //                if (StringUtils.isEmpty(userModel.getName())
        //                || userModel.getAge() == null
        //                || userModel.getGender() == null
        //                || StringUtils.isEmpty(userModel.getTelphone())) {
        //                        throw new BussinessException(EmBussinessError.PARAM_INVALID);
        //                }

        ValidationResult validationResult = validator.validate(userModel);
        if (validationResult.isHasErrors()) {
            throw new BussinessException(EmBussinessError.PARAM_INVALID, validationResult.getErrMsg());
        }

        UserDO userDO = convertFromModel(userModel);
        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException ex) {
            throw new BussinessException(EmBussinessError.PARAM_INVALID, "手机号已经注册");
        }

        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);

        return;
    }

    @Override public UserModel validLogin(String telphone, String encrptPassword) throws BussinessException {
        UserDO userDO = userDOMapper.selectByTelphone(telphone);
        if (userDO == null) {
            throw new BussinessException(EmBussinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUid(userDO.getId());

        UserModel userModel = convertFromDataObject(userDO, userPasswordDO);
        if (!StringUtils.equals(userModel.getEncrptPassword(), encrptPassword)) {
            throw new BussinessException(EmBussinessError.USER_LOGIN_FAIL);
        }
        return userModel;
    }

    private UserPasswordDO convertPasswordFromModel(UserModel userModel) throws BussinessException {
        if (userModel == null) {
            return null;
        }

        if (userModel.getEncrptPassword() == null) {
            throw new BussinessException(EmBussinessError.PARAM_INVALID, "密码不能为空");
        }

        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }

    private UserDO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
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

    public UserVO convertUserVOFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
