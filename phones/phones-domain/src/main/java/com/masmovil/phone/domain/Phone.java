package com.masmovil.phone.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Phone {

    private Long id;

    private String imagePath;

    private String name;

    private String description;

    private BigDecimal price;

}