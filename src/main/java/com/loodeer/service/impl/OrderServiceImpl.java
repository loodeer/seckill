package com.loodeer.service.impl;

import com.loodeer.controller.viewObject.OrderVO;
import com.loodeer.dao.ItemStockDOMapper;
import com.loodeer.dao.OrderDOMapper;
import com.loodeer.dao.SequenceDOMapper;
import com.loodeer.dataobject.OrderDO;
import com.loodeer.dataobject.SequenceDO;
import com.loodeer.error.BussinessException;
import com.loodeer.error.EmBussinessError;
import com.loodeer.service.ItemService;
import com.loodeer.service.OrderService;
import com.loodeer.service.UserService;
import com.loodeer.service.model.ItemModel;
import com.loodeer.service.model.OrderModel;
import com.loodeer.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author loodeer
 * @date 2018-12-18 23:04
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private ItemService itemService;

    @Resource
    private UserService userService;

    @Resource
    private OrderDOMapper orderDOMapper;

    @Resource
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BussinessException {
        // 1. 参数校验
        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null) {
            throw new BussinessException(EmBussinessError.PARAM_INVALID, "商品不存在");
        }
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BussinessException(EmBussinessError.PARAM_INVALID, "用户不存在");
        }
        if (amount < 0 || amount > 10) {
            throw new BussinessException(EmBussinessError.PARAM_INVALID, "购买数量不正确");
        }

        // 2. 落单减库存
        Boolean result = itemService.decreaseStock(itemId, amount);
        if (!result) {
            throw new BussinessException(EmBussinessError.STOCK_NOT_ENOUGH);
        }
        itemService.increaseSales(itemId, amount);
        // 3. 入库
        OrderModel orderModel = new OrderModel();
        orderModel.setId(generateOrderNo());
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setItemPrice(itemModel.getPrice());
        orderModel.setAmount(amount);
        orderModel.setOrderPrice(amount * itemModel.getPrice());

        OrderDO orderDO = convertFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);

        return orderModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private String generateOrderNo() {
        // 订单号 16 位
        StringBuilder stringBuilder = new StringBuilder();

        // 前 8 位为年月日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.BASIC_ISO_DATE);

        stringBuilder.append(nowDate);
        // 中间 6 位为自增序列
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        Integer sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequence + sequenceDO.getStep());
        sequenceDOMapper.updateSequenceByName(sequenceDO);

        if (sequence >= 1000000) {
            sequence = sequence - 1000000;
        }
        for (int i = 0; i < 6 - String.valueOf(sequence).length(); i++) {
            stringBuilder.append("0");
        }

        stringBuilder.append(sequence);

        // 后两位为分库分表位
        stringBuilder.append("00");
        return stringBuilder.toString();
    }

    private OrderDO convertFromOrderModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }

    public OrderVO convertOrderVOFromPromoModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderModel, orderVO);
        return orderVO;
    }
}
