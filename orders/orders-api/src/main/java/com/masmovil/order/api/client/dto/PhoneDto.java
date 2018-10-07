package com.masmovil.order.api.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {

    private Long id;

    private String imagePath;

    private String name;

    private String description;

    private BigDecimal price;

}