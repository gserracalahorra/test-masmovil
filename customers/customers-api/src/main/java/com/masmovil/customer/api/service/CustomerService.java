package com.masmovil.customer.api.service;

import com.masmovil.customer.api.repository.CustomerRepository;
import com.masmovil.customer.api.repository.entity.CustomerEntity;
import com.masmovil.customer.api.service.exception.CustomerNotFoundException;
import com.masmovil.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findById(Long id) throws CustomerNotFoundException {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);

        if (customerEntity.isPresent()) {
            return fromEntityToDomain(customerEntity.get());
        } else {
            throw new CustomerNotFoundException(id);
        }
    }

    /*
        Idealmente los conversores seran clases aparte
    */
    private Customer fromEntityToDomain(CustomerEntity entity) {
        Customer domain = new Customer();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setSurname(entity.getSurname());
        domain.setEmail(entity.getEmail());
        return domain;
    }

}