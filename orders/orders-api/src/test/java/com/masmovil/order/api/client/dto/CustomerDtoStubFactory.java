package com.masmovil.order.api.client.dto;

public class CustomerDtoStubFactory {

    public static CustomerDto createValid(Long id, String name, String surname, String email) {
        return new CustomerDto(id, name, surname, email);
    }

}