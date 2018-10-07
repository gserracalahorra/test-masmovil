package com.masmovil.order.api.client;

import com.masmovil.customer.domain.Customer;
import com.masmovil.order.api.client.dto.CustomerDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
    Envuelve el cliente REST de clientes para devolver los DTO transformados a dominio
 */
@Component
public class CustomerClientWrapper {

    @Autowired
    private CustomerRestClient customerRestClient;

    public Optional<Customer> findById(Long id) {
        try {
            CustomerDto customerDto = customerRestClient.findById(id);
            return Optional.of(fromDtoToDomain(customerDto));
        } catch (FeignException e) {
            if (e.getMessage().contains("404")) {
                return Optional.empty();
            }
            else {
                throw e;
            }
        }
    }

    private Customer fromDtoToDomain(CustomerDto dto) {
        Customer domain = new Customer();
        domain.setId(dto.getId());
        domain.setName(dto.getName());
        domain.setSurname(dto.getSurname());
        domain.setEmail(dto.getEmail());
        return domain;
    }

}
