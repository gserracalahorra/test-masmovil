package com.masmovil.order.api.client;

import com.masmovil.customer.domain.Customer;
import com.masmovil.order.api.client.dto.CustomerDto;
import com.masmovil.order.api.client.dto.CustomerDtoStubFactory;
import feign.FeignException;
import feign.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerClientWrapperUnitTest {

    private CustomerClientWrapper customerClientWrapper = new CustomerClientWrapper();

    @Mock
    private CustomerRestClient customerRestClient;

    @Before
    public void before () {
        ReflectionTestUtils.setField(customerClientWrapper, "customerRestClient", customerRestClient);
    }

    @Test
    public void findCustomerByIdTest() {
        CustomerDto customerDto = CustomerDtoStubFactory
                .createValid(Long.valueOf(1), "Guillem", "Serra Calahorra", "guillem_serra@hotmail.com");

        when(customerRestClient.findById(any())).thenReturn(customerDto);

        Customer result = customerClientWrapper.findById(Long.valueOf(1)).get();

        assertNotNull(result);

        assertEquals(customerDto.getId(), result.getId());
        assertEquals(customerDto.getName(), result.getName());
        assertEquals(customerDto.getSurname(), result.getSurname());
        assertEquals(customerDto.getEmail(), result.getEmail());
    }

    @Test
    public void findCustomerById_500NotFoundTest() {

        when(customerRestClient.findById(any()))
                .thenThrow(FeignException.errorStatus("1", Response.builder()
                                                                    .status(404)
                                                                    .reason("Not found")
                                                                    .headers(new HashMap<>())
                                                                    .build()));


        Optional<Customer> result = customerClientWrapper.findById(Long.valueOf(1));

        assertEquals(result, Optional.empty());

    }

    @Test(expected = FeignException.class)
    public void findCustomerById_404NotFoundTest() {

        when(customerRestClient.findById(any()))
                .thenThrow(FeignException.errorStatus("1", Response.builder()
                        .status(500)
                        .reason("Internal server error")
                        .headers(new HashMap<>())
                        .build()));


        customerClientWrapper.findById(Long.valueOf(1));
    }


}