package com.masmovil.order.api.client;

import com.masmovil.order.api.client.dto.PhoneDto;
import com.masmovil.phone.domain.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
    Envuelve el cliente REST de telefonos para devolver los DTO transformados a dominio
 */
@Component
public class PhoneClientWrapper {

    @Autowired
    private PhoneRestClient phoneRestClient;

    /*
        Obtiene el catalogo completo y filtra aquellos que hayan sido solicitados en la orden
     */
    public List<Phone> findInCataloge(List<Long> ids) {
        List<PhoneDto> phoneDtos = phoneRestClient.findCatalogue();

        phoneDtos = phoneDtos.stream().filter(phoneDto -> ids.contains(phoneDto.getId()))
                .collect(Collectors.toList());

        return phoneDtos.stream().map(dto -> fromDtoToDomain(dto)).collect(Collectors.toList());
    }

    private Phone fromDtoToDomain(PhoneDto dto) {
        Phone domain = new Phone();
        domain.setId(dto.getId());
        domain.setName(dto.getName());
        domain.setDescription(dto.getDescription());
        domain.setImagePath(dto.getImagePath());
        domain.setPrice(dto.getPrice());
        return domain;
    }

}