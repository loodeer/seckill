package com.loodeer.service.model;

import org.joda.time.DateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author loodeer
 * @date 2018-12-19 00:41
 */
public class PromoModel {
    private Integer id;
    // 秒杀活动名称
    @NotBlank
    private String promoName;

    @NotNull
    private DateTime startDate;

    @NotNull
    private DateTime endDate;

    @NotNull
    @Min(value = 1, message = "商品 id 错误")
    private Integer itemId;

    @NotNull
    @Min(value = 100, message = "秒杀价必须大于 1 元")
    private Integer promoItemPrice;

    // 秒杀活动状态，1未开始，2进行中，3已结束。
    private Integer status;

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

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
