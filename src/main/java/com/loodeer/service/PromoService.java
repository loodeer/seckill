package com.loodeer.service;

import com.loodeer.error.BussinessException;
import com.loodeer.service.model.PromoModel;

/**
 * @author loodeer
 * @date 2018-12-19 00:48
 */
public interface PromoService {
    PromoModel createPromo(PromoModel promoModel) throws BussinessException;
}
