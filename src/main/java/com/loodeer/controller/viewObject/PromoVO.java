package com.loodeer.controller.viewObject;

import java.util.Date;

/**
 * @author loodeer
 * @date 2018-12-19 01:06
 */
public class PromoVO {
    private Integer id;
    // 秒杀活动名称
    private String promoName;

    private Date startDate;

    private Date endDate;

    private Integer itemId;

    private Integer promoItemPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(Integer promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }
}
