package com.loodeer.controller;

import com.loodeer.controller.viewObject.PromoVO;
import com.loodeer.dao.PromoDOMapper;
import com.loodeer.error.BussinessException;
import com.loodeer.response.CommonResult;
import com.loodeer.service.PromoService;
import com.loodeer.service.impl.PromoServiceImpl;
import com.loodeer.service.model.PromoModel;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author loodeer
 * @date 2018-12-19 00:58
 */
@Controller("promo")
@RequestMapping("/promo")
@CrossOrigin(origins = { "*" }, allowCredentials = "true")
public class PromoController extends BaseController {

    @Resource
    private PromoServiceImpl promoService;

    @RequestMapping(value = "/create", method = { RequestMethod.POST }, consumes = { CONTENT_TYPE_FORMED })
    @ResponseBody
    public CommonResult create(@Param("promoName") String promoName,
            @Param("itemId") Integer itemId,
            @Param("promoItemPrice") Integer promoItemPrice,
            @Param("startTime") DateTime startTime,
            @Param("endTime") DateTime endTime) throws BussinessException {

        PromoModel promoModel = new PromoModel();
        promoModel.setPromoName(promoName);
        promoModel.setStartDate(startTime);
        promoModel.setEndDate(endTime);
        promoModel.setItemId(itemId);
        promoModel.setPromoItemPrice(promoItemPrice);

        PromoModel promoModel1 = promoService.createPromo(promoModel);
        PromoVO promoVO = promoService.convertPromoVOFromPromoModel(promoModel1);
        return CommonResult.create(promoVO);
    }

}
