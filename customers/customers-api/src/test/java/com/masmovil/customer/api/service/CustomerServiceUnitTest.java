package com.masmovil.customer.api.service;

import com.masmovil.customer.api.repository.CustomerRepository;
import com.masmovil.customer.api.repository.entity.CustomerEntity;
import com.masmovil.customer.api.repository.factory.CustomerEntityStubFactory;
import com.masmovil.customer.api.service.exception.CustomerNotFoundException;
import com.masmovil.customer.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceUnitTest {

    private CustomerService customerService = new CustomerService();

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void before() {
        ReflectionTestUtils.setField(customerService, "customerRepository", customerRepository);
    }

    @Test
    public void findCustomerByIdTest() throws CustomerNotFoundException {
        CustomerEntity entity = CustomerEntityStubFactory
                .buildCustomer(Long.valueOf(1), "Guillem", "Serra Calahorra", "guillem_serra@hotmail.com");

        when(customerRepository.findById(any())).thenReturn(java.util.Optional.of(entity));

        Customer result = customerService.findById(Long.valueOf(1));

        assertEquals(Long.valueOf(1), result.getId());
        assertEquals("Guillem", result.getName());
        assertEquals("Serra Calahorra", result.getSurname());
        assertEquals("guillem_serra@hotmail.com", result.getEmail());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void findCustomerById_NotFoundTest() throws CustomerNotFoundException {
        when(customerRepository.findById(any())).thenReturn(java.util.Optional.empty());
        customerService.findById(Long.valueOf(1));
    }
}