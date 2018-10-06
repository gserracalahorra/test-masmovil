package com.masmovil.customer.api.controller;

import com.masmovil.customer.api.controller.dto.CustomerDto;
import com.masmovil.customer.api.controller.exception.ResourceNotFoundException;
import com.masmovil.customer.api.service.CustomerService;
import com.masmovil.customer.api.service.exception.CustomerNotFoundException;
import com.masmovil.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
    public CustomerDto findById(@PathVariable("id") Long id) {
        try {
            Customer customerDomain = customerService.findById(id);
            return fromDomainToDto(customerDomain);
        } catch (CustomerNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    /*
        Idealmente los conversores seran clases aparte
    */
    private CustomerDto fromDomainToDto(Customer domain) {
        CustomerDto dto = new CustomerDto();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setSurname(domain.getSurname());
        dto.setEmail(domain.getEmail());
        return dto;
    }

}