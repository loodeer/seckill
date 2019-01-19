package com.loodeer.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author loodeer
 * @date 2018-12-20 01:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PromoServiceImplTest {

    @Resource
    private PromoServiceImpl promoService;

    @Test
    public void getPromoByItemIdTest() {
        promoService.getPromoByItemId(10);
    }
}
