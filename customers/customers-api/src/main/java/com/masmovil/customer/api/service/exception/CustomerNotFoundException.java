package com.masmovil.customer.api.service.exception;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(Long id) {
        super(String.format("Customer not found with id = %s", id));
    }

}