package com.loodeer.dao;

import com.loodeer.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(locations = {"classpath*:*"})
public class UserDOMapperTest {

    @Autowired
    UserDOMapper userDOMapper;
    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        UserDO userDO = userDOMapper.selectByPrimaryKey(8);
        assertNotNull(userDO);
    }
}
