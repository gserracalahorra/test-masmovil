package com.masmovil.phone.api.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PhoneDto {

    private Long id;

    private String imagePath;

    private String name;

    private String description;

    private BigDecimal price;

}