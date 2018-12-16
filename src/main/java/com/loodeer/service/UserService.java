package com.loodeer.service;

import com.loodeer.error.BussinessException;
import com.loodeer.service.model.UserModel;

public interface UserService {
        UserModel getUserById(Integer id);

        void register(UserModel userModel) throws BussinessException;

        UserModel validLogin(String telphone, String encrptPassword) throws BussinessException;
}
