package com.masmovil.phone.api.controller;

import com.masmovil.phone.api.controller.dto.PhoneDto;
import com.masmovil.phone.api.controller.exception.NoContentException;
import com.masmovil.phone.domain.Phone;
import com.masmovil.phone.api.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(path = "/catalogue", method = RequestMethod.GET)
    public List<PhoneDto> findCatalogue() {
        List<Phone> phoneDomainList = phoneService.findCatalogue();

        if (phoneDomainList.isEmpty()) {
            throw new NoContentException();
        }

        return phoneDomainList.stream().map(domain -> fromDomainToDto(domain)).collect(Collectors.toList());
    }

    /*
        Idealmente los conversores seran clases aparte
     */
    private PhoneDto fromDomainToDto(Phone domain) {
        PhoneDto dto = new PhoneDto();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());
        dto.setImagePath(domain.getImagePath());
        dto.setPrice(domain.getPrice());
        return dto;
    }

}