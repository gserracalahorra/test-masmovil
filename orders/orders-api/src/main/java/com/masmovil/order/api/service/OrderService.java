package com.masmovil.order.api.service;

import com.masmovil.order.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    public static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public void order(Order order) {
        log.info(String.format("Calculating price for order [%s]", order.getId()));
        order.calculateTotalPrice();
        log.info(order.toString());
    }

}