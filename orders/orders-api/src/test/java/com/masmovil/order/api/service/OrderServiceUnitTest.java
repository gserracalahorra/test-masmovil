package com.masmovil.order.api.service;

import com.masmovil.order.domain.Order;
import com.masmovil.order.domain.OrderDomainStubFactory;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceUnitTest {

    private OrderService orderService = new OrderService();

    @Test
    public void testOrder() {
        Order order = OrderDomainStubFactory.buildValidOrder();

        orderService.order(order);

        assertEquals(BigDecimal.valueOf(540.0), order.getTotalPrice());
    }

}