package com.masmovil.customer.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    private Long id;

    private String name;

    private String surname;

    private String email;

}