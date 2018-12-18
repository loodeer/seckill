package com.loodeer.service;

import com.loodeer.error.BussinessException;
import com.loodeer.service.model.OrderModel;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderService {

    OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BussinessException;
}
