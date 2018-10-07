package com.masmovil.order.api.client.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PhoneDtoStubFactory {

    public static List<PhoneDto> createValidPhoneList() {
        List<PhoneDto> phones = new ArrayList<>();
        phones.add(buildPhone(Long.valueOf(1), "Huawei 1", "Gama baja", "http://image-1", BigDecimal.valueOf(80.0)));
        phones.add(buildPhone(Long.valueOf(2), "Huawei 2", "Gama media", "http://image-2", BigDecimal.valueOf(180.0)));
        phones.add(buildPhone(Long.valueOf(3), "Huawei 3", "Gama alta", "http://image-3", BigDecimal.valueOf(280.0)));
        return phones;
    }

    public static PhoneDto buildPhone(Long id, String name, String description, String imagePath, BigDecimal price) {
        return new PhoneDto(id, name, description, imagePath, price);
    }

    public static List<PhoneDto> createEmptyList() {
        return new ArrayList<>();
    }

}