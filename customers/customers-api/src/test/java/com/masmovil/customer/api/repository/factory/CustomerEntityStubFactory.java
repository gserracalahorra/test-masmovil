package com.masmovil.customer.api.repository.factory;

import com.masmovil.customer.api.repository.entity.CustomerEntity;

public class CustomerEntityStubFactory {

    public static CustomerEntity buildCustomer(Long id, String name, String surname, String email) {
        return new CustomerEntity(id, name, surname, email);
    }

}