package com.loodeer.service.impl;

import com.loodeer.controller.viewObject.PromoVO;
import com.loodeer.dao.PromoDOMapper;
import com.loodeer.dataobject.PromoDO;
import com.loodeer.error.BussinessException;
import com.loodeer.error.EmBussinessError;
import com.loodeer.service.PromoService;
import com.loodeer.service.model.PromoModel;
import com.loodeer.validator.ValidationResult;
import com.loodeer.validator.ValidatorImpl;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);
        PromoModel promoModel = convertFromPromoDO(promoDO);
        if (promoModel == null){
            return null;
        }
        if (promoModel.getStartDate().isAfterNow()) {
            // 未开始
            promoModel.setStatus(1);
        } else if (promoModel.getEndDate().isBeforeNow()) {
            // 已结束
            promoModel.setStatus(3);
        } else {
            // 进行中
            promoModel.setStatus(2);
        }
        return promoModel;
    }

    private PromoModel convertFromPromoDO(PromoDO promoDO) {
        if (promoDO == null) {
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        promoModel.setStartDate(new DateTime(promoDO.getStartTime()));
        promoModel.setEndDate(new DateTime(promoDO.getEndTime()));
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

    public PromoVO convertPromoVOFromPromoModel(PromoModel promoModel) {
        if (promoModel == null) {
            return null;
        }
        PromoVO promoVO = new PromoVO();
        BeanUtils.copyProperties(promoModel, promoVO);
        promoVO.setStartDate(promoModel.getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        promoVO.setEndDate(promoModel.getEndDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        promoVO.setPromoItemPrice(BigDecimal.valueOf(promoModel.getPromoItemPrice()).divide(new BigDecimal(100)));
        return promoVO;
    }
}
