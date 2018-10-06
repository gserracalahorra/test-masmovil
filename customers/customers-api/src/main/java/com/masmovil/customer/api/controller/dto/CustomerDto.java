package com.masmovil.customer.api.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private Long id;

    private String name;

    private String surname;

    private String email;

}