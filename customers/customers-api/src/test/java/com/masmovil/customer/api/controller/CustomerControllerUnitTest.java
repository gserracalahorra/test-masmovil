package com.masmovil.customer.api.controller;

import com.masmovil.customer.api.controller.dto.CustomerDto;
import com.masmovil.customer.api.controller.exception.ResourceNotFoundException;
import com.masmovil.customer.api.service.CustomerService;
import com.masmovil.customer.api.service.exception.CustomerNotFoundException;
import com.masmovil.customer.domain.Customer;
import com.masmovil.customer.domain.CustomerDomainStubFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerUnitTest {

    private CustomerController customerController = new CustomerController();

    @Mock
    private CustomerService customerService;

    @Before
    public void before()
    {
        ReflectionTestUtils.setField(customerController, "customerService", customerService);
    }

    @Test
    public void findCustomerByIdTest() throws CustomerNotFoundException {
        Customer customer = CustomerDomainStubFactory
                .buildCustomer(Long.valueOf(1), "Guillem", "Serra Calahorra", "guillem_serra@hotmail.com");

        when(customerService.findById(any())).thenReturn(customer);

        CustomerDto result = customerController.findById(Long.valueOf(1));

        assertEquals(Long.valueOf(1), result.getId());
        assertEquals("Guillem", result.getName());
        assertEquals("Serra Calahorra", result.getSurname());
        assertEquals("guillem_serra@hotmail.com", result.getEmail());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findCustomerById_NotFoundTest() throws CustomerNotFoundException {

        when(customerService.findById(any())).thenThrow(new CustomerNotFoundException(Long.valueOf(1)));

        customerController.findById(Long.valueOf(1));
    }

}