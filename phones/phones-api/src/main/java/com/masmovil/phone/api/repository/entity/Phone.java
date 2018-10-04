package com.masmovil.phone.api.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PHONES")
@Data
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "PRICE")
    private BigDecimal price;

}