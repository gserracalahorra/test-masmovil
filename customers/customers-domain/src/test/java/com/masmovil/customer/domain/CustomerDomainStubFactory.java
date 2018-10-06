package com.masmovil.customer.domain;

public class CustomerDomainStubFactory {

    public static Customer buildCustomer(Long id, String name, String surname, String email) {
        return new Customer(id, name, surname, email);
    }

}