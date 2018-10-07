package com.masmovil.order.api.controller;

import com.masmovil.customer.domain.Customer;
import com.masmovil.customer.domain.CustomerDomainStubFactory;
import com.masmovil.order.api.client.CustomerClientWrapper;
import com.masmovil.order.api.client.PhoneClientWrapper;
import com.masmovil.order.api.controller.command.OrderCommand;
import com.masmovil.order.api.controller.exception.BadRequestException;
import com.masmovil.order.api.service.OrderService;
import com.masmovil.phone.domain.Phone;
import com.masmovil.phone.domain.factory.PhoneDomainStubFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerUnitTest {

    private OrderController orderController = new OrderController();

    @Mock
    private CustomerClientWrapper customerClientWrapper;

    @Mock
    private PhoneClientWrapper phoneClientWrapper;

    @Mock
    private OrderService orderService;

    @Before
    public void before() {
        ReflectionTestUtils.setField(orderController, "customerClientWrapper", customerClientWrapper);
        ReflectionTestUtils.setField(orderController, "phoneClientWrapper", phoneClientWrapper);
        ReflectionTestUtils.setField(orderController, "orderService", orderService);
    }

    @Test
    public void successOrderTest() {
        OrderCommand orderCommand = buildOrderCommand(Long.valueOf(1), List.of(Long.valueOf(1), Long.valueOf(2)));

        Customer customer = CustomerDomainStubFactory
                .buildCustomer(Long.valueOf(1), "Guillem", "Serra Calahorra", "guillem_serra@hotmail.com");
        when(customerClientWrapper.findById(any())).thenReturn(Optional.of(customer));

        List<Phone> phones = PhoneDomainStubFactory.createValidPhoneList();
        when(phoneClientWrapper.findInCataloge(any())).thenReturn(phones);

        orderController.order(orderCommand);
    }

    @Test(expected = BadRequestException.class)
    public void noCustomerFailTest() {
        OrderCommand orderCommand = buildOrderCommand(Long.valueOf(1), List.of(Long.valueOf(1), Long.valueOf(2)));

        when(customerClientWrapper.findById(any())).thenReturn(Optional.empty());

        orderController.order(orderCommand);
    }

    @Test(expected = BadRequestException.class)
    public void noPhoneRetrievedFailTest() {
        OrderCommand orderCommand = buildOrderCommand(Long.valueOf(1), List.of(Long.valueOf(1), Long.valueOf(2)));

        Customer customer = CustomerDomainStubFactory
                .buildCustomer(Long.valueOf(1), "Guillem", "Serra Calahorra", "guillem_serra@hotmail.com");
        when(customerClientWrapper.findById(any())).thenReturn(Optional.of(customer));

        when(phoneClientWrapper.findInCataloge(any())).thenReturn(new ArrayList<>());

        orderController.order(orderCommand);
    }

    private OrderCommand buildOrderCommand(Long customerId, List<Long> ids) {
        OrderCommand orderCommand = new OrderCommand();
        orderCommand.setCustomerId(customerId);
        orderCommand.setPhoneIds(ids);
        return orderCommand;
    }

}