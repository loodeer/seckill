package com.loodeer.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;

/**
 * @author loodeer
 * @date 2018-12-20 01:14
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class PromoServiceImplTest {

    @Resource
    private PromoServiceImpl promoService;

    @Test
    public void getPromoByItemIdTest() {
        promoService.getPromoByItemId(10);
    }
}
