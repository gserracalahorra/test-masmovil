package com.masmovil.phone.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    private Long id;

    private String imagePath;

    private String name;

    private String description;

    private BigDecimal price;

}