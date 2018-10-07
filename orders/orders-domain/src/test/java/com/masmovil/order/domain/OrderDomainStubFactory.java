package com.masmovil.order.domain;

import com.masmovil.customer.domain.Customer;
import com.masmovil.customer.domain.CustomerDomainStubFactory;
import com.masmovil.phone.domain.Phone;
import com.masmovil.phone.domain.factory.PhoneDomainStubFactory;

import java.util.List;

public class OrderDomainStubFactory {

    public static Order buildValidOrder() {
        Customer customer = CustomerDomainStubFactory
                .buildCustomer(Long.valueOf(1), "Guillem", "Serra Calahorra", "guillem_serra@hotmail.com");
        List<Phone> phones = PhoneDomainStubFactory.createValidPhoneList();
        return new Order(customer, phones);
    }

}