package com.masmovil.phone.api.service;

import com.masmovil.phone.api.repository.PhoneRepository;
import com.masmovil.phone.domain.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public List<Phone> getCatalogue() {
        List<com.masmovil.phone.api.repository.entity.Phone> phoneEntityList = phoneRepository.findAll();
        return phoneEntityList.stream().map(entity -> fromEntityToDomain(entity)).collect(Collectors.toList());
    }

    private Phone fromEntityToDomain(com.masmovil.phone.api.repository.entity.Phone entity) {
        Phone phone = new Phone();
        phone.setId(entity.getId());
        phone.setName(entity.getName());
        phone.setDescription(entity.getDescription());
        phone.setImagePath(entity.getImagePath());
        phone.setPrice(entity.getPrice());
        return phone;
    }

}