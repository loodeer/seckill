package com.loodeer.service.impl;

import com.loodeer.dao.PromoDOMapper;
import com.loodeer.dataobject.PromoDO;
import com.loodeer.error.BussinessException;
import com.loodeer.error.EmBussinessError;
import com.loodeer.service.PromoService;
import com.loodeer.service.model.PromoModel;
import com.loodeer.validator.ValidationResult;
import com.loodeer.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author loodeer
 * @date 2018-12-19 00:49
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Resource
    private ValidatorImpl validator;

    @Resource
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel createPromo(PromoModel promoModel) throws BussinessException {

        ValidationResult validationResult = validator.validate(promoModel);
        if (validationResult.isHasErrors()) {
            throw new BussinessException(EmBussinessError.PARAM_INVALID, validationResult.getErrMsg());
        }
        PromoDO promoDO = convertFromPromoModel(promoModel);
        promoDOMapper.insertSelective(promoDO);
        promoModel.setId(promoDO.getId());
        return promoModel;
    }

    private PromoDO convertFromPromoModel(PromoModel promoModel) {
        if (promoModel == null) {
            return null;
        }
        PromoDO promoDO = new PromoDO();
        BeanUtils.copyProperties(promoModel, promoDO);
        promoDO.setStartTime(promoModel.getStartDate().toDate());
        promoDO.setEndTime(promoModel.getEndDate().toDate());
        return promoDO;
    }
}
