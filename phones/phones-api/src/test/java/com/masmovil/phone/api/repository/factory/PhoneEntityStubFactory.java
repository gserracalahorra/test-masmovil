package com.masmovil.phone.api.repository.factory;

import com.masmovil.phone.api.repository.entity.PhoneEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PhoneEntityStubFactory {

    public static List<PhoneEntity> createValidPhoneList() {
        List<PhoneEntity> phones = new ArrayList<>();
        phones.add(buildPhone(Long.valueOf(1), "Huawei 1", "Gama baja", "http://image-1", BigDecimal.valueOf(80.0)));
        phones.add(buildPhone(Long.valueOf(2), "Huawei 2", "Gama media", "http://image-2", BigDecimal.valueOf(180.0)));
        phones.add(buildPhone(Long.valueOf(3), "Huawei 3", "Gama alta", "http://image-3", BigDecimal.valueOf(280.0)));
        return phones;
    }

    public static List<PhoneEntity> createEmptyPhoneList() {
        return new ArrayList<>();
    }

    public static PhoneEntity buildPhone(Long id, String name, String description, String imagePath, BigDecimal price) {
        return new PhoneEntity(id, name, description, imagePath, price);
    }

}